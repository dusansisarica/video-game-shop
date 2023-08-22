package github.com.dusansisarica.videogameshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameQuantityDto {
    public Integer id;
    public Integer gameId;
    public Integer quantity;
    public Integer shopId;
    public VideoGameDto game;

    public GameQuantityDto(Integer id, Integer gameId, Integer quantity, Integer shop) {
        this.id = id;
        this.gameId = gameId;
        this.quantity = quantity;
        this.shopId = shop;
    }

    public GameQuantityDto(){}

    public GameQuantityDto(Integer id, Integer gameId, Integer quantity, Integer shopId, VideoGameDto game) {
        this.id = id;
        this.gameId = gameId;
        this.quantity = quantity;
        this.shopId = shopId;
        this.game = game;
    }
}
