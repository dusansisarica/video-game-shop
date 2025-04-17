package github.com.dusansisarica.videogameshop.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
    private float totalPrice;

    public Cart(Integer ID, User user, List<CartItem> cartItems) {
        this.ID = ID;
        this.user = user;
        this.cartItems = cartItems;
    }

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Cart(Integer ID, List<CartItem> cartItems) {
        this.ID = ID;
        this.cartItems = cartItems;
    }

    public Cart() {
    }
}
