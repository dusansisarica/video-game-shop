package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.DiscountDto;
import github.com.dusansisarica.videogameshop.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/discount")
@CrossOrigin
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @PostMapping("/{gameId}")
    public ResponseEntity<DiscountDto> addDiscount(@PathVariable Integer gameId, @RequestBody DiscountDto newDiscount) {
        return new ResponseEntity<>(discountService.addDiscount(gameId, newDiscount), HttpStatus.CREATED);
    }
}
