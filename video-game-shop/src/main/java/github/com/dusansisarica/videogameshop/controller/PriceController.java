package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.PriceDto;
import github.com.dusansisarica.videogameshop.model.Price;
import github.com.dusansisarica.videogameshop.security.util.JwtTokenUtil;
import github.com.dusansisarica.videogameshop.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "api/price")
@CrossOrigin
public class PriceController {
    @Autowired
    private PriceService priceService;
    private final JwtTokenUtil tokenUtils;
    public PriceController(JwtTokenUtil tokenHelper) {
        this.tokenUtils = tokenHelper;
    }

    @PostMapping("/{gameId}")
    public ResponseEntity<PriceDto> makePrice(@PathVariable Integer gameId,@RequestBody PriceDto priceDto) {
        PriceDto newPrice = priceService.makePrice(gameId, priceDto);
        return ResponseEntity.ok(newPrice);
    }
}
