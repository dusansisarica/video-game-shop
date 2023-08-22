package github.com.dusansisarica.videogameshop.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GameQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private Integer gameId;
    
    @Column
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Shop.class)
    @JoinColumn(name="shop_id", nullable=false)
    private Shop shop;

    public GameQuantity() {
    }

    public GameQuantity(Integer id, Integer gameId, Integer quantity, Shop shop) {
        this.id = id;
        this.gameId = gameId;
        this.quantity = quantity;
        this.shop = shop;
    }
}
