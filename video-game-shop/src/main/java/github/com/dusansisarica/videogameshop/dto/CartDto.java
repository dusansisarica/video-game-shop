package github.com.dusansisarica.videogameshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDto {
    public Integer ID;
    public List<CartItemDto> cartItems;
    public float totalPrice;

    public CartDto(Integer ID, List<CartItemDto> cartItems) {
        this.ID = ID;
        this.cartItems = cartItems;
    }

    public CartDto(List<CartItemDto> cartItems, float totalPrice) {
        this.ID = ID;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }


    public CartDto() {
    }

    public CartDto(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }
}
