package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.UserDetailDto;
import github.com.dusansisarica.videogameshop.dto.UserDto;
import github.com.dusansisarica.videogameshop.model.User;
import github.com.dusansisarica.videogameshop.security.auth.AuthRequest;
import github.com.dusansisarica.videogameshop.security.auth.UserTokenState;
import github.com.dusansisarica.videogameshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDto> changeDetails(@RequestBody @Valid UserDetailDto userDetail) {
        return new ResponseEntity<>(userService.changeDetails(userDetail), HttpStatus.OK);
    }
}
