package github.com.dusansisarica.videogameshop.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class GameQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int gameID;
    @Column
    private Integer quantity;

    @Column
    private Integer reserved;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Shop.class)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "video_game_id")
    private VideoGame videoGame;

    public GameQuantity() {
    }

    public GameQuantity(Integer id, int gameId, Integer quantity, Shop shop, int reserved) {
        this.id = id;
        this.gameID = gameId;
        this.quantity = quantity;
        this.shop = shop;
        this.reserved = reserved;
    }
}
