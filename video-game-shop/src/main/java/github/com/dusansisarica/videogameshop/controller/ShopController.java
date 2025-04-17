package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.*;
import github.com.dusansisarica.videogameshop.security.util.JwtTokenUtil;
import github.com.dusansisarica.videogameshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "api/shop")
@CrossOrigin
public class ShopController {

    @Autowired
    private ShopService shopService;
    private final JwtTokenUtil tokenUtils;

    public ShopController(JwtTokenUtil tokenHelper) {
        this.tokenUtils = tokenHelper;
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN') || hasRole('MANAGER')")
    public ResponseEntity<ShopPagination> findAll(@RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "") String searchQuery) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        paginationDto.searchQuery = searchQuery;
        return new ResponseEntity<>(shopService.getAll(paginationDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ShopDto> save(@RequestBody ShopDto shopDto, HttpServletRequest request) {
        return new ResponseEntity<>(shopService.save(shopDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN') || hasRole('MANAGER')")
    public ResponseEntity<ShopDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(shopService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/game/{id}")
//    @PreAuthorize("hasRole('USER')")
//    @PreAuthorize("permitAll()")
    public ResponseEntity<ShopPagination> findShopsForGame(@PathVariable("id") Integer id, @RequestParam(defaultValue = "1") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(shopService.findShopsByItemId(id, paginationDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/game")
    public ResponseEntity<List<GameQuantityDto>> addGame(@RequestBody GameQuantityDto gameDto, HttpServletRequest request) {
        return new ResponseEntity<>(shopService.addGame(gameDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("")
    public ResponseEntity<ShopPagination> updateShop(@RequestBody ShopDto shopDto, @RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(shopService.updateShop(shopDto, paginationDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ShopPagination> deleteShop(@PathVariable("id") Integer id, @RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(shopService.deleteShop(id, paginationDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('STAFF')")
    @GetMapping("items")
    public ResponseEntity<GameQuantityPagination> getItemsForShop(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
                                                                  @RequestParam(defaultValue = "10") int size,
                                                                  @RequestParam(defaultValue = "") String searchQuery) {
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
        PaginationDto paginationDto = new PaginationDto(page, size);
        paginationDto.searchQuery = searchQuery;
        return new ResponseEntity<>(shopService.findItemsForShop(email, paginationDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('STAFF')")
    @GetMapping("orders")
    public ResponseEntity<List<ItemsForInventoryDto>> getOrdersForShop(HttpServletRequest request) {
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
//        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(shopService.getOrdersForShop(email), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('STAFF')")
    @PutMapping("orders/accept/{id}")
    public ResponseEntity<List<ItemsForInventoryDto>> acceptOrder(@PathVariable("id") Integer id, HttpServletRequest request) {
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
//        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(shopService.acceptOrder(email, id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('STAFF')")
    @PutMapping("orders/decline/{id}")
    public ResponseEntity<List<ItemsForInventoryDto>> declineOrder(@PathVariable("id") Integer id, HttpServletRequest request) {
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
//        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(shopService.declineOrder(email, id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('STAFF')")
    @PutMapping("orders/ship/{id}")
    public ResponseEntity<List<ItemsForInventoryDto>> shipOrder(@PathVariable("id") Integer id, HttpServletRequest request) {
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
//        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(shopService.shipOrder(email, id), HttpStatus.OK);
    }

    @GetMapping("games/{id}")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN') || hasRole('MANAGER')")
    public ResponseEntity<GameQuantityPagination> findGamesForShop(@RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                   @PathVariable("id") Integer id) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(shopService.getGamesForShop(id, paginationDto), HttpStatus.OK);
    }


}
