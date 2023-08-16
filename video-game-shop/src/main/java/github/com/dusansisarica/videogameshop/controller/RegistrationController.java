package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.RegistrationDto;
import github.com.dusansisarica.videogameshop.dto.UserDto;
import github.com.dusansisarica.videogameshop.model.User;
import github.com.dusansisarica.videogameshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.time.Clock;

@RestController
@RequestMapping(value = "api/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<User> saveUser(@RequestBody RegistrationDto registrationDto, HttpServletRequest request) throws UnsupportedEncodingException, InterruptedException {
        if (!registrationDto.passwordFirst.equals(registrationDto.passwordSecond))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (userService.emailExists(registrationDto.email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.save(registrationDto), HttpStatus.CREATED);
    }
}
