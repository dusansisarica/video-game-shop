package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserEmail(String userEmail);
}
