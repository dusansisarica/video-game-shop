package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.*;
import github.com.dusansisarica.videogameshop.enums.Genre;
import github.com.dusansisarica.videogameshop.enums.Platform;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import github.com.dusansisarica.videogameshop.security.util.JwtTokenUtil;
import github.com.dusansisarica.videogameshop.service.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTML;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/games")
@CrossOrigin
public class VideoGameController {

    @Autowired
    private VideoGameService videoGameService;
    private final JwtTokenUtil tokenUtils;
//
    public VideoGameController(JwtTokenUtil tokenHelper) {this.tokenUtils = tokenHelper;}
    @GetMapping()
    public ResponseEntity<VideoGamePagination> findAll(@RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(required = false) List<String> genres,
                                                       @RequestParam(required = false) List<String> platforms,
                                                       @RequestParam(required = false, defaultValue = "") String searchQuery,
                                                       @RequestParam(required = false, defaultValue = "asc") String sortBy,
                                                       @RequestParam(required = false, defaultValue = "asc") String sortDirection)
    {
//        Set<Genre> genreSet;
//        Set<Platform> platformList;
//        if (genres.size() != 0) {
//            genreSet = genres.stream().map(Genre::valueOf).collect(Collectors.toSet());
//        } else {
//            genreSet = null;
//        }
//        if (platforms.size() != 0 ){
//            platformList = platforms.stream().map(Platform::valueOf).collect(Collectors.toSet());
//        }
//        else{
//            platformList = null;
//        }
        List<Platform> platformEnums = platforms != null ? platforms.stream()
                .map(Platform::valueOf)
                .collect(Collectors.toList()) : null;

        List<Genre> genreEnums = genres != null ? genres.stream()
                .map(Genre::valueOf)
                .collect(Collectors.toList()) : null;

        PaginationDto paginationDto = new PaginationDto(page, size, platformEnums, genreEnums, searchQuery, sortBy, sortDirection);
        return new ResponseEntity<>(videoGameService.findAll(paginationDto), HttpStatus.OK);
    }

    @GetMapping("/featured")
    public ResponseEntity<List<VideoGameDto>> findFeatured(@RequestParam(defaultValue = "1") int page,
                                                           @RequestParam(defaultValue = "9") int size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(videoGameService.findFeatured(paginationDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<VideoGameDto> save(@RequestBody VideoGameDto videoGameDto, HttpServletRequest request) {
        return new ResponseEntity<>(videoGameService.save(videoGameDto), HttpStatus.CREATED);
    }

    @PostMapping("/recommended")
    public List<VideoGameDto> getRecommendations(@RequestBody List<Integer> recentIds, HttpServletRequest request) {
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
        return videoGameService.generateRecommendations(recentIds, email);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<VideoGameDto> update(@RequestBody VideoGameDto videoGameDto, HttpServletRequest request) {
        return new ResponseEntity<>(videoGameService.update(videoGameDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<VideoGameDto>> deleteById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(videoGameService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<VideoGameDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(videoGameService.findById(id), HttpStatus.OK);
    }

    @GetMapping("quantity/{id}")
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<Integer> getTotalQuantity(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(videoGameService.getTotalQuantity(id), HttpStatus.OK);
    }


    @GetMapping("genres")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<String>> getGenres() {
        return new ResponseEntity<>(videoGameService.getGenreNames(), HttpStatus.OK);
    }

    @GetMapping("platforms")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<String>> getPlatforms() {
        return new ResponseEntity<>(videoGameService.getPlatformNames(), HttpStatus.OK);
    }


}
