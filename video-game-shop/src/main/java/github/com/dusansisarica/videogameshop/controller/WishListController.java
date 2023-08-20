package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.dto.WishListDto;
import github.com.dusansisarica.videogameshop.security.util.JwtTokenUtil;
import github.com.dusansisarica.videogameshop.service.WishListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/wish-list")
public class WishListController {
    @Autowired
    private WishListService wishListService;

    private JwtTokenUtil jwtTokenUtil;

    public WishListController(JwtTokenUtil tokenHelper) {
        this.jwtTokenUtil = tokenHelper;
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<WishListDto> save(@RequestBody WishListDto wishListDto, HttpServletRequest request) {
        return new ResponseEntity<>(wishListService.save(wishListDto, jwtTokenUtil.getEmailFromToken(jwtTokenUtil.getToken(request))), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping()
    public ResponseEntity<List<WishListDto>> getAllForUser(HttpServletRequest request) {
        return new ResponseEntity<>(wishListService.getAllForUser(jwtTokenUtil.getEmailFromToken(jwtTokenUtil.getToken(request))), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<WishListDto>> deleteById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(wishListService.deleteById(id), HttpStatus.OK);
    }
}
