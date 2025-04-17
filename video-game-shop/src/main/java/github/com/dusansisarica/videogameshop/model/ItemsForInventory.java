package github.com.dusansisarica.videogameshop.model;

import github.com.dusansisarica.videogameshop.enums.InventoryStatus;
import github.com.dusansisarica.videogameshop.enums.OrderStatus;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemsForInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "video_game_id")
    private VideoGame videoGame;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;

    @ElementCollection
    @CollectionTable(name = "declined_shops", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "shop_id")
    private List<Integer> declinedShops = new ArrayList<>();

    public ItemsForInventory(VideoGame videoGame, int quantity) {
        this.videoGame = videoGame;
        this.quantity = quantity;
    }
}
