package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.GameQuantity;
import github.com.dusansisarica.videogameshop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameQuantityRepository extends JpaRepository<GameQuantity, Integer> {
    @Query("SELECT DISTINCT gq.shop FROM GameQuantity gq WHERE gq.gameID = :gameId")
    List<Shop> findShopIdsByGameID(Integer gameId);

    List<GameQuantity> findAllByShopId(Integer id);


    GameQuantity findByShopIdAndGameID(Integer shopId, Integer gameId);

    GameQuantity findFirstByGameIDAndQuantityGreaterThanEqualOrderByQuantityDesc(Integer gameId, Integer quantity);

    GameQuantity findFirstByGameIDOrderByQuantityDesc(Integer gameId);

    @Query("SELECT gq FROM GameQuantity gq WHERE gq.videoGame.ID = :gameId AND gq.shop.id NOT IN :declinedShopIds AND gq.shop.deleted = false ORDER BY (gq.quantity - gq.reserved) DESC")
    List<GameQuantity> findFirstByVideoGameIDOrderByQuantityMinusReservedDesc(@Param("gameId") Integer gameId,
                                                                              @Param("declinedShopIds") List<Integer> declinedShopIds);

    @Query("SELECT gq FROM GameQuantity gq " +
            "WHERE gq.videoGame.ID = :gameId " +
            "AND (gq.quantity - gq.reserved) >= :quantity " +
            "AND gq.shop.id NOT IN :declinedShopIds " +
            "AND gq.shop.deleted = false " +
            "ORDER BY (gq.quantity - gq.reserved) DESC")
    List<GameQuantity> findFirstByGameIDAndQuantityMinusReservedGreaterThanEqualOrderByQuantityMinusReservedDesc(@Param("gameId") Integer gameId,
                                                                                                                 @Param("quantity") Integer quantity,
                                                                                                                 @Param("declinedShopIds") List<Integer> declinedShopIds
    );


    @Query("SELECT SUM(gq.quantity) FROM GameQuantity gq WHERE gq.gameID = :gameId")
    Integer getTotalQuantityByGameID(Integer gameId);

}

