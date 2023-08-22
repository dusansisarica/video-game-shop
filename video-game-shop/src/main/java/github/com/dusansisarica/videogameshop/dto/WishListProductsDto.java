package github.com.dusansisarica.videogameshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WishListProductsDto {
    private Integer id;
    private VideoGameDto product;
}
