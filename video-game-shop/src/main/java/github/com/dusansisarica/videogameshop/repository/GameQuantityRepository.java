package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.GameQuantity;
import github.com.dusansisarica.videogameshop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameQuantityRepository extends JpaRepository<GameQuantity, Integer> {
    @Query("SELECT DISTINCT gq.shop FROM GameQuantity gq WHERE gq.gameId = :gameId")
    List<Shop> findShopIdsByGameId(Integer gameId);

    List<GameQuantity> findAllByShopId(Integer id);
}

