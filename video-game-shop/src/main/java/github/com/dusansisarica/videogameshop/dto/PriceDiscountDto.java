package github.com.dusansisarica.videogameshop.dto;

import github.com.dusansisarica.videogameshop.model.VideoGame;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceDiscountDto {
    public List<VideoGameDto> games;
    public boolean flagBuyOneGetOneFree = false;
    public boolean flagPercentageDiscount = false;
    public float totalPrice;
}
