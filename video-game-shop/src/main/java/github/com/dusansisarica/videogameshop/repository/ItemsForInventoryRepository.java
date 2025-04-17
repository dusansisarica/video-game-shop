package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.enums.InventoryStatus;
import github.com.dusansisarica.videogameshop.model.ItemsForInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemsForInventoryRepository extends JpaRepository<ItemsForInventory, Integer> {

    @Query("SELECT i FROM ItemsForInventory i WHERE i.shop.id = :shopId AND i.inventoryStatus = :status")
    List<ItemsForInventory> findAllByShopIdAndInventoryStatus(@Param("shopId") Integer shopId, @Param("status") InventoryStatus status);

    @Query("SELECT i FROM ItemsForInventory i WHERE i.shop.id = :shopId AND i.inventoryStatus IN (:statuses)")
    List<ItemsForInventory> findAllByShopIdAndInventoryStatus(@Param("shopId") Integer shopId, @Param("statuses") List<InventoryStatus> statuses);

}
