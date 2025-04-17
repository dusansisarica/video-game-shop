package github.com.dusansisarica.videogameshop.model;

import github.com.dusansisarica.videogameshop.enums.CartItemStatus;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private VideoGame game;

    @Column
    private Integer quantity;

//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

    @Enumerated(EnumType.STRING)
    private CartItemStatus status;

    public CartItem() {
    }

    public CartItem(VideoGame game, Integer quantity) {
        this.game = game;
        this.quantity = quantity;
    }

    public CartItem(Cart cart, VideoGame game, Integer quantity) {
        this.cart = cart;
        this.game = game;
        this.quantity = quantity;
    }

    public CartItem(Integer ID, Cart cart, VideoGame game, Integer quantity) {
        this.ID = ID;
        this.cart = cart;
        this.game = game;
        this.quantity = quantity;
    }
}
