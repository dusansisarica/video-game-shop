package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.PurchasedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedItemRepository extends JpaRepository<PurchasedItem, Integer> {
}
