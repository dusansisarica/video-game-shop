package github.com.dusansisarica.videogameshop.controller;

import com.itextpdf.text.DocumentException;
import github.com.dusansisarica.videogameshop.dto.CartDto;
import github.com.dusansisarica.videogameshop.dto.CartItemDto;
import github.com.dusansisarica.videogameshop.dto.OrderDto;
import github.com.dusansisarica.videogameshop.dto.PaginationDto;
import github.com.dusansisarica.videogameshop.model.Order;
import github.com.dusansisarica.videogameshop.security.util.JwtTokenUtil;
import github.com.dusansisarica.videogameshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "api/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    private final JwtTokenUtil tokenUtils;

    public OrderController(JwtTokenUtil tokenHelper) {
        this.tokenUtils = tokenHelper;
    }

    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<OrderDto> makeOrder(HttpServletRequest request) {
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
        return new ResponseEntity<>(orderService.makeOrder(email), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<OrderDto>> getUserOrders(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
        return new ResponseEntity<>(orderService.getUserOrders(email, paginationDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER') || hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAll(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(orderService.getAll(paginationDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/ship")
    public ResponseEntity<List<OrderDto>> shipOrder(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "10") int size, @RequestBody OrderDto orderDto) throws MessagingException, DocumentException, IOException {
        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(orderService.shipOrder(orderDto, paginationDto), HttpStatus.OK);
    }
}
