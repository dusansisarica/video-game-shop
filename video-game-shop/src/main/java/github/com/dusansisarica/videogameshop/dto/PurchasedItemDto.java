package github.com.dusansisarica.videogameshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedItemDto {
    public Integer id;
    public CartItemDto cartItem;
}
