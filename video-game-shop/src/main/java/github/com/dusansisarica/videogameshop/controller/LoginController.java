package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.LoginDto;
import github.com.dusansisarica.videogameshop.dto.RegistrationDto;
import github.com.dusansisarica.videogameshop.model.Role;
import github.com.dusansisarica.videogameshop.model.User;
import github.com.dusansisarica.videogameshop.security.auth.AuthRequest;
import github.com.dusansisarica.videogameshop.security.auth.AuthResponse;
import github.com.dusansisarica.videogameshop.security.auth.UserTokenState;
import github.com.dusansisarica.videogameshop.security.util.JwtTokenUtil;
import github.com.dusansisarica.videogameshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/auth/login")
public class LoginController {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;

    @PostMapping()
    public ResponseEntity<UserTokenState> login(@RequestBody @Valid AuthRequest request) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        User user = (User) authentication.getPrincipal();
        if (!user.isVerified() && user.isDeleted()) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        String jwt = jwtUtil.generateToken(user.getEmail());
        int expiresIn = jwtUtil.getExpiredIn();
        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, user.getRoles()));
    }
}
