package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.CartDto;
import github.com.dusansisarica.videogameshop.dto.CartItemDto;
import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.security.util.JwtTokenUtil;
import github.com.dusansisarica.videogameshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "api/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;
    private final JwtTokenUtil tokenUtils;

    public CartController(JwtTokenUtil tokenHelper) {
        this.tokenUtils = tokenHelper;
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<CartDto> addItem(@RequestBody CartItemDto cartItemDto, HttpServletRequest request) {
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
        return new ResponseEntity<>(cartService.addItem(cartItemDto, email), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<CartDto> getCart(HttpServletRequest request) {
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
        return new ResponseEntity<>(cartService.getCartDto(email), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CartDto> deleteById(@PathVariable("id") Integer id, HttpServletRequest request) {
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
        return new ResponseEntity<>(cartService.removeItem(id, email), HttpStatus.OK);
    }
}
