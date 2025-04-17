package github.com.dusansisarica.videogameshop.controller;

import github.com.dusansisarica.videogameshop.dto.*;
import github.com.dusansisarica.videogameshop.security.util.JwtTokenUtil;
import github.com.dusansisarica.videogameshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "api/review")
@CrossOrigin
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    private final JwtTokenUtil tokenUtils;

    public ReviewController(JwtTokenUtil tokenHelper) {
        this.tokenUtils = tokenHelper;
    }


    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReviewDto> add(@RequestBody ReviewDto dto, HttpServletRequest request) {
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
        return new ResponseEntity<>(reviewService.add(email, dto), HttpStatus.CREATED);
    }

//    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    @GetMapping("/game/{id}")
    public ResponseEntity<List<ReviewDto>> getAllForGameApproved(@PathVariable("id") Integer id, HttpServletRequest request) {
        return new ResponseEntity<>(reviewService.getAllForGameApproved(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<ReviewPagination> getAllForUser(HttpServletRequest request, @RequestParam(defaultValue = "1") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        String email = tokenUtils.getEmailFromToken(tokenUtils.getToken(request));
        return new ResponseEntity<>(reviewService.getAllUsersReviews(email, paginationDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/approve/{id}")
    public ResponseEntity<List<ReviewDto>> approveReview(@PathVariable("id") Integer id, HttpServletRequest request) {
        return new ResponseEntity<>(reviewService.approveReview(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/deny/{id}")
    public ResponseEntity<List<ReviewDto>> denyReview(@PathVariable("id") Integer id, HttpServletRequest request) {
        return new ResponseEntity<>(reviewService.denyReview(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ReviewDto>> deleteReview(@PathVariable("id") Integer id, HttpServletRequest request) {
        return new ResponseEntity<>(reviewService.deleteReview(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping()
    public ResponseEntity<ReviewPagination> getAllForApproval(@RequestParam(defaultValue = "1") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        return new ResponseEntity<>(reviewService.getReviewsForApprove(paginationDto), HttpStatus.OK);
    }

}
