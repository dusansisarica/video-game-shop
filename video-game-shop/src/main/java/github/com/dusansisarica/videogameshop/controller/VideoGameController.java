package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.RegistrationDto;
import github.com.dusansisarica.videogameshop.dto.UserDto;
import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import github.com.dusansisarica.videogameshop.service.VideoGameService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.List;

@RestController
@RequestMapping(value = "api/games")
public class VideoGameController {

    @Autowired
    private VideoGameService videoGameService;

    @GetMapping()
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<List<VideoGameDto>> findAll(){
        return new ResponseEntity<>(videoGameService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<VideoGameDto> save(@RequestBody VideoGameDto videoGameDto, HttpServletRequest request) {
        return new ResponseEntity<>(videoGameService.save(videoGameDto), HttpStatus.CREATED);
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
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<VideoGameDto> findById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(videoGameService.findById(id), HttpStatus.OK);
    }

}
