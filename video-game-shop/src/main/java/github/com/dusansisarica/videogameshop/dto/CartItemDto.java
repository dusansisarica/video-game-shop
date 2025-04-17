package github.com.dusansisarica.videogameshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    public Integer ID;
    public VideoGameDto game;
    public CartDto cart;
    public Integer quantity;

    public CartItemDto(Integer ID, VideoGameDto game, CartDto cart, Integer quantity) {
        this.ID = ID;
        this.game = game;
        this.cart = cart;
        this.quantity = quantity;
    }

    public CartItemDto(VideoGameDto game, Integer quantity) {
        this.game = game;
        this.quantity = quantity;
    }


    public CartItemDto() {
    }

    public CartItemDto(Integer id, VideoGameDto game, Integer quantity) {
        this.ID = id;
        this.game = game;
        this.quantity = quantity;
    }

    public CartItemDto(VideoGameDto game, CartDto cart, Integer quantity) {
        this.game = game;
        this.cart = cart;
        this.quantity = quantity;
    }
}
