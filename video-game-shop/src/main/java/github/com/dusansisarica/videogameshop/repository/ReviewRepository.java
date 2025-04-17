package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByGameID(Integer id);

    List<Review> findAllByUserEmail(String email);

    List<Review> findAllByDeletedFalse();

    List<Review> findAllByDeletedFalseAndApprovedTrue();

    List<Review> findAllByGameIDAndDeletedFalseAndApprovedTrue(Integer id);

    List<Review> findAllByApprovedFalseAndDeniedFalse();


}
