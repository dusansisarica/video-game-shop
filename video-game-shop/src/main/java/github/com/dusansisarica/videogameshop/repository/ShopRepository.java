package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

    //@Query("SELECT DISTINCT s FROM Shop s JOIN s.items i WHERE i.id = :itemId")
    //List<Shop> findShopsByItemId(Integer itemId);
}
