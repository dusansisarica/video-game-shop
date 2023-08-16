package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.RegistrationDto;
import github.com.dusansisarica.videogameshop.dto.UserDto;
import github.com.dusansisarica.videogameshop.model.User;
import github.com.dusansisarica.videogameshop.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.Clock;

@RestController
@RequestMapping(value = "api/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<User> saveUser(@RequestBody RegistrationDto registrationDto, HttpServletRequest request) throws UnsupportedEncodingException, InterruptedException, MessagingException {
        if (!registrationDto.passwordFirst.equals(registrationDto.passwordSecond))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (userService.emailExists(registrationDto.email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.save(registrationDto, getSiteURL(request)), HttpStatus.CREATED);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }


}
