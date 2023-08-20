package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WishListRepository extends JpaRepository<WishList, Integer> {
    List<WishList> findAllByUserId(Integer ID);
    List<WishList> findAllByUserIdAndDeletedFalse(Integer ID);
}
