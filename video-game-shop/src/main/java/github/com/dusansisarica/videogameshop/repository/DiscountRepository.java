package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}
