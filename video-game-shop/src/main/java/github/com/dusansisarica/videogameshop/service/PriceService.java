package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.PriceDiscountDto;
import github.com.dusansisarica.videogameshop.dto.PriceDto;
import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.enums.ActionType;
import github.com.dusansisarica.videogameshop.mapper.ActionDtoMapper;
import github.com.dusansisarica.videogameshop.mapper.DiscountDtoMapper;
import github.com.dusansisarica.videogameshop.mapper.PriceDtoMapper;
import github.com.dusansisarica.videogameshop.model.Discount;
import github.com.dusansisarica.videogameshop.model.Price;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import github.com.dusansisarica.videogameshop.repository.DiscountRepository;
import github.com.dusansisarica.videogameshop.repository.PriceRepository;
import github.com.dusansisarica.videogameshop.repository.VideoGameRepository;
import github.com.dusansisarica.videogameshop.strategy.DiscountFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private VideoGameRepository videoGameRepository;
    @Autowired
    private PriceDtoMapper priceDtoMapper;
    @Autowired
    @Lazy
    private DiscountFactory discountFactory;

    @Autowired
    private ActionDtoMapper discountDtoMapper;
    @Autowired
    private DiscountRepository discountRepository;

    @Transactional
    public PriceDto makePrice(Integer gameId, PriceDto priceDto) {
        VideoGame game = videoGameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        Price priceList = new Price();
        priceList.setPrice(priceDto.price);
        priceList.setStartDate(priceDto.startDate);
        priceList.setEndDate(priceDto.endDate);
        priceList.setGame(game);

        if (priceDto.getAction() != null && !"NO_DISCOUNT".equals(priceDto.getAction().name)) {
            Discount discount = discountDtoMapper.fromDTOtoModel(priceDto.getAction());
            discount = discountRepository.save(discount); // Прво сачувајте Discount
            priceList.setDiscount(discount);
        }

        addPrice(game, priceList);

        Price savedPrice = priceRepository.save(priceList);
        return priceDtoMapper.fromModeltoDto(savedPrice);
    }
    public Price getActivePrice(Integer gameId) {
        LocalDate today = LocalDate.now();
        return priceRepository.findByGameIDAndStartDateBeforeAndEndDateAfter(gameId,today, today)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No active price found for the given game."));
    }

    public void changeActivePrice(Integer gameId, float newPrice) {
        LocalDate today = LocalDate.now();
        priceRepository.findByGameIDAndStartDateBeforeAndEndDateAfter(gameId,today, today)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No active price found for the given game."))
                .setPrice(newPrice);
    }


    public float calculateTotalPrice(PriceDiscountDto priceDiscountDto){
        float totalPrice = 0.0f;
        for (VideoGameDto game : priceDiscountDto.games){
            totalPrice += getActivePrice(game.getID()).getPrice();
        }
        return calculateDiscountedPrice(priceDiscountDto, totalPrice);
    }

    public float calculateDiscountedPrice(PriceDiscountDto priceDiscountDto, float totalPrice){
        PriceDiscountDto newPriceDiscount = priceDiscountDto;
        newPriceDiscount.setTotalPrice(totalPrice);
        for (VideoGameDto game : priceDiscountDto.games){
            Discount discount = getActivePrice(game.getID()).getDiscount();
            if (discount != null){
                newPriceDiscount = discountFactory.getDiscountStrategy(discount.getActionType()).calculatePriceWithDiscount(newPriceDiscount, newPriceDiscount.totalPrice);
            }
        }
        return newPriceDiscount.getTotalPrice();
    }

    public PriceDto calculatePriceForGame(VideoGame game){
        Price price = getActivePrice(game.getID());
        if (price.getDiscount() != null && price.getDiscount().isDiscountActive() && price.getDiscount().getActionType() == ActionType.PERCENTAGE_DISCOUNT){
            double percentage = price.getDiscount().getDiscountValue();
            PriceDto priceDto = priceDtoMapper.fromModeltoDto(price);
            priceDto.setPrice(Math.round((price.getPrice() -price.getPrice() * (percentage/100)) * 100.0) / 100.0);
            return priceDto;
        }
        else if (price.getDiscount() != null && !price.getDiscount().isDiscountActive()){
            price.setDiscount(null);
            return priceDtoMapper.fromModeltoDto(price);
        }
        else {
            return priceDtoMapper.fromModeltoDto(price);
        }
    }

    public void addPrice(VideoGame game, Price newPrice) {
        // Proveriti da li nova cena preklapa sa postojećim
        for (Price existingPrice : game.getPrice()) {
            if (isOverlapping(existingPrice, newPrice)) {
                throw new IllegalArgumentException("Nova cena se preklapa sa postojećim cenama.");
            }
        }
        game.getPrice().add(newPrice);
    }

    private boolean isOverlapping(Price p1, Price p2) {
        return !(p1.getEndDate().isBefore(p2.getStartDate()) || p1.getStartDate().isAfter(p2.getEndDate()));
    }


}
