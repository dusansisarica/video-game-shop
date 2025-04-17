package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.CartDto;
import github.com.dusansisarica.videogameshop.dto.CentralInventoryDto;
import github.com.dusansisarica.videogameshop.dto.CentralInventoryPagination;
import github.com.dusansisarica.videogameshop.dto.PaginationDto;
import github.com.dusansisarica.videogameshop.service.CentralInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "api/central-inventory")
@CrossOrigin
public class CentralInventoryController {
    @Autowired
    private CentralInventoryService centralInventoryService;

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<CentralInventoryPagination> getAll(@RequestParam(defaultValue = "1") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(centralInventoryService.getAll(paginationDto), HttpStatus.OK);
    }

}
