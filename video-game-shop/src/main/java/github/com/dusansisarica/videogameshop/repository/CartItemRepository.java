package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findAllByCartID(int id);
}
