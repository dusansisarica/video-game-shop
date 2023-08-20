package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.UserDetailDto;
import github.com.dusansisarica.videogameshop.dto.UserDto;
import github.com.dusansisarica.videogameshop.model.User;
import github.com.dusansisarica.videogameshop.security.auth.AuthRequest;
import github.com.dusansisarica.videogameshop.security.auth.UserTokenState;
import github.com.dusansisarica.videogameshop.security.util.JwtTokenUtil;
import github.com.dusansisarica.videogameshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    private JwtTokenUtil tokenUtils;

    public UserController(JwtTokenUtil tokenHelper) {
        this.tokenUtils = tokenHelper;
    }


    @PutMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDto> changeDetails(@RequestBody @Valid UserDetailDto userDetail) {
        return new ResponseEntity<>(userService.changeDetails(userDetail), HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    public ResponseEntity<UserDetailDto> getUser(HttpServletRequest request) {
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
        return new ResponseEntity<>(userService.getUserDetails(email), HttpStatus.OK);
    }
}
