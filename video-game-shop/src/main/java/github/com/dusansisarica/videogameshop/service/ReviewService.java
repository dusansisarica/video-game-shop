package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.PaginationDto;
import github.com.dusansisarica.videogameshop.dto.ReviewDto;
import github.com.dusansisarica.videogameshop.dto.ReviewPagination;
import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.mapper.ReviewDtoMapper;
import github.com.dusansisarica.videogameshop.model.*;
import github.com.dusansisarica.videogameshop.repository.ReviewRepository;
import github.com.dusansisarica.videogameshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewDtoMapper reviewDtoMapper;
    @Autowired
    private UserRepository userRepository;

    public ReviewDto add(String email, ReviewDto dto) {
        if (checkIfUserBoughtTheGame(email, dto)) {
            Review review = ReviewDtoMapper.fromDTOtoModel(dto);
            review.setUser(userRepository.findByEmail(email));
            review.setApproved(false);
            review.setDeleted(false);
            review.setDenied(false);
            reviewRepository.save(review);
            return dto;
        }
        return null;
    }

    private boolean checkIfUserBoughtTheGame(String email, ReviewDto dto) {
        User user = userRepository.findByEmail(email);
        return user.getOrders().stream()
                .flatMap(order -> order.getPurchasedItems().stream())
                .map(PurchasedItem::getCartItem)
                .map(CartItem::getGame)
                .anyMatch(game -> game.getID().equals(dto.getGame().ID));
    }

    public List<ReviewDto> getAllForGame(Integer id) {
        return ReviewDtoMapper.fromModeltoDTOList(reviewRepository.findAllByGameID(id));
    }

    public List<ReviewDto> getAllForGameApproved(Integer id) {
        return ReviewDtoMapper.fromModeltoDTOList(reviewRepository.findAllByGameIDAndDeletedFalseAndApprovedTrue(id));
    }


    public ReviewPagination getAllUsersReviews(String email, PaginationDto paginationDto) {
        return getAllWithPagination(reviewRepository.findAllByUserEmail(email), paginationDto);
    }

    public ReviewPagination getAllWithPagination(List<Review> reviews, PaginationDto paginationDto) {
        int startIndex = (paginationDto.pageNumber - 1) * paginationDto.pageSize;
        int endIndex = startIndex + paginationDto.pageSize;
        if (endIndex > reviews.size()) endIndex = reviews.size();
        if (startIndex > reviews.size()) reviews = new ArrayList<>();
        List<ReviewDto> reviewDtos = ReviewDtoMapper.fromModeltoDTOList(reviews);
        return new ReviewPagination(reviewDtos.subList(startIndex, endIndex), reviewDtos.size());
    }


    public List<ReviewDto> deleteReview(Integer id) {
        Review review = reviewRepository.findById(id).orElse(null);
        review.setDeleted(true);
        reviewRepository.save(review);
        return ReviewDtoMapper.fromModeltoDTOList(reviewRepository.findAllByDeletedFalseAndApprovedTrue());
    }

    public List<ReviewDto> approveReview(Integer id) {
        Review review = reviewRepository.findById(id).orElse(null);
        review.setApproved(true);
        reviewRepository.save(review);
        return ReviewDtoMapper.fromModeltoDTOList(reviewRepository.findAllByDeletedFalseAndApprovedTrue());
    }

    public ReviewPagination getReviewsForApprove(PaginationDto paginationDto) {
        return getAllWithPagination(reviewRepository.findAllByApprovedFalseAndDeniedFalse(), paginationDto);
    }

    public List<ReviewDto> denyReview(Integer id) {
        Review review = reviewRepository.findById(id).orElse(null);
        review.setDenied(true);
        reviewRepository.save(review);
        return ReviewDtoMapper.fromModeltoDTOList(reviewRepository.findAllByApprovedFalseAndDeniedFalse());
    }


}
