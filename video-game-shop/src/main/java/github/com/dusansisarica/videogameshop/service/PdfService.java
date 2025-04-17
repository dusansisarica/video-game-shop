package github.com.dusansisarica.videogameshop.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import github.com.dusansisarica.videogameshop.dto.PriceDiscountDto;
import github.com.dusansisarica.videogameshop.mapper.VideoGameDtoMapper;
import github.com.dusansisarica.videogameshop.model.Order;
import github.com.dusansisarica.videogameshop.model.PurchasedItem;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private VideoGameDtoMapper videoGameDtoMapper;

    public byte[] generatePdf(Order order) throws DocumentException, IOException, MessagingException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();

        // Add content to the PDF here, such as order details
        document.add(new Paragraph("Detalji o porudžbini"));
        document.add(new Paragraph("Broj porudžbine: " + order.getId()));
        document.add(new Paragraph("Datum porudžbine: " + order.getOrderDate()));
        document.add(new Paragraph("Adresa porudžbine: " + order.getUser().getAddress().getAddress() + "," + order.getUser().getAddress().getCity().getCityName() + "," + order.getUser().getAddress().getCity().getCountry().getName()));


        // Create a table for displaying order items
        PdfPTable table = new PdfPTable(4); // 4 columns for item details
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Define table headers
        PdfPCell cell1 = new PdfPCell(new Phrase("Proizvod"));
        PdfPCell cell2 = new PdfPCell(new Phrase("Kolicina"));
        PdfPCell cell3 = new PdfPCell(new Phrase("Cena po proizvodu"));
        PdfPCell cell4 = new PdfPCell(new Phrase("Ukupna cena"));

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);

        double totalPrice = 0;
        List<VideoGame> games = new ArrayList<>();

        // Iterate through order items and add them to the table
        for (PurchasedItem item : order.getPurchasedItems()) {
            table.addCell(item.getCartItem().getGame().getTitle());
            table.addCell(String.valueOf(item.getCartItem().getQuantity()));
            table.addCell(String.valueOf(item.getCartItem().getGame().getPrice().get(item.getCartItem().getGame().getPrice().size()-1).getPrice()) + " rsd");
            table.addCell(String.valueOf(item.getCartItem().getQuantity() * item.getCartItem().getGame().getPrice().get(item.getCartItem().getGame().getPrice().size()-1).getPrice()) + " rsd");
//            totalPrice += item.getCartItem().getQuantity() * item.getCartItem().getGame().getPrice().get(item.getCartItem().getGame().getPrice().size()-1).getPrice();
            games.add(item.getCartItem().getGame());
        }
        PriceDiscountDto priceDiscountDto = new PriceDiscountDto();
        priceDiscountDto.setGames(videoGameDtoMapper.fromModeltoDTOList(games));
        totalPrice = priceService.calculateTotalPrice(priceDiscountDto);
        document.add(new Paragraph("Ukupno: " + Math.round(totalPrice * 100.0) / 100.0 + " rsd"));


        document.add(table); // Add the table to the PDF

        document.close();
        String email = order.getUser().getEmail();
        String subject = "Informacije o porudžbini";
        String text = "Vaša pošiljka je poslata";
        String fileName = "Porudžbina";

        emailService.sendEmailWithAttachment(email, subject, text, byteArrayOutputStream.toByteArray(), fileName);

        return byteArrayOutputStream.toByteArray();
    }

}
