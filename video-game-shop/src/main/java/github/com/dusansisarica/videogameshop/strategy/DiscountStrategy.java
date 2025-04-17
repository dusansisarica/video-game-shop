package github.com.dusansisarica.videogameshop.strategy;

import github.com.dusansisarica.videogameshop.dto.PriceDiscountDto;
import github.com.dusansisarica.videogameshop.model.VideoGame;

import java.util.List;

public interface DiscountStrategy {
    PriceDiscountDto calculatePriceWithDiscount(PriceDiscountDto priceDiscountDto, float totalPrice);
}
