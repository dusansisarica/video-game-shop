package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.GameQuantityDto;
import github.com.dusansisarica.videogameshop.dto.ShopDto;
import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.service.ShopService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping()
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<List<ShopDto>> findAll(){
        return new ResponseEntity<>(shopService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ShopDto> save(@RequestBody ShopDto shopDto, HttpServletRequest request) {
        return new ResponseEntity<>(shopService.save(shopDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<ShopDto> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(shopService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/game/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ShopDto>> findShops(@PathVariable("id") Integer id){
        return new ResponseEntity<>(shopService.findShopsByItemId(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/game")
    public ResponseEntity<List<GameQuantityDto>> addGame(@RequestBody GameQuantityDto gameDto, HttpServletRequest request) {
        return new ResponseEntity<>(shopService.addGame(gameDto), HttpStatus.CREATED);
    }

}
