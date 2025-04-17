package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.*;
import github.com.dusansisarica.videogameshop.model.User;
import github.com.dusansisarica.videogameshop.security.auth.AuthRequest;
import github.com.dusansisarica.videogameshop.security.auth.UserTokenState;
import github.com.dusansisarica.videogameshop.security.util.JwtTokenUtil;
import github.com.dusansisarica.videogameshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    private final JwtTokenUtil tokenUtils;

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

    @PutMapping("/employ")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<UserDto> employUser(@RequestBody @Valid EmployeeDto employeeDto) {
        return new ResponseEntity<>(userService.employUser(employeeDto), HttpStatus.OK);
    }

    @PutMapping("unemploy")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<UserDto> unemployUser(@RequestBody @Valid EmployeeDto employeeDto) {
        return new ResponseEntity<>(userService.unemployUser(employeeDto), HttpStatus.OK);
    }

    @PutMapping("delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserPagination> deleteUser(@PathVariable("id") Integer id, @RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(userService.deleteUser(id, paginationDto), HttpStatus.OK);
    }


    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserPagination> getAllUsers(@RequestParam(defaultValue = "1") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(defaultValue = "") String searchQuery) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        paginationDto.searchQuery = searchQuery;
        return new ResponseEntity<>(userService.findAll(paginationDto), HttpStatus.OK);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<UserPagination> getAllRoleUser(@RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "10") int size,
                                                         @RequestParam(defaultValue = "") String searchQuery ) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        paginationDto.searchQuery = searchQuery;
        return new ResponseEntity<>(userService.findAllUsers(paginationDto), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> registerManager(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.registerManager(userDto), HttpStatus.OK);
    }
}
