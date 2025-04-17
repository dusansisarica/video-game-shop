package github.com.dusansisarica.videogameshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "cart_item_id")
    private CartItem cartItem;

    public PurchasedItem(Integer id, CartItem cartItem) {
        this.id = id;
        this.cartItem = cartItem;
    }
}
