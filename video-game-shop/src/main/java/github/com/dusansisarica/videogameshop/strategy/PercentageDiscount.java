package github.com.dusansisarica.videogameshop.strategy;

import github.com.dusansisarica.videogameshop.dto.PriceDiscountDto;
import github.com.dusansisarica.videogameshop.dto.PriceDto;
import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.enums.ActionType;
import github.com.dusansisarica.videogameshop.mapper.PriceDtoMapper;
import github.com.dusansisarica.videogameshop.service.PriceService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class PercentageDiscount implements DiscountStrategy {
    @Autowired
    private PriceService priceService;
    @Autowired
    private PriceDtoMapper priceDtoMapper;

    @Override
    public PriceDiscountDto calculatePriceWithDiscount(PriceDiscountDto priceDiscountDto, float totalPrice) {
        if (priceDiscountDto.flagPercentageDiscount){
            return priceDiscountDto;
        }
        List<VideoGameDto> games = priceDiscountDto.games;
        for (VideoGameDto game : priceDiscountDto.games){
            //PROVERITI DA NIJE SLUCAJNO NULL
            if (priceService.getActivePrice(game.getID()).getDiscount() != null && priceService.getActivePrice(game.getID()).getDiscount().getActionType() == ActionType.PERCENTAGE_DISCOUNT){
                games = calculateDiscount(games, games.indexOf(game));
            }
        }
        priceDiscountDto.flagPercentageDiscount = true;
        priceDiscountDto.setGames(games);
        priceDiscountDto.setTotalPrice(sumPrices(priceDiscountDto.games));
        return priceDiscountDto;
    }

    private List<VideoGameDto> calculateDiscount(List<VideoGameDto> games, int index) {
        List<VideoGameDto> discountedGames = games;
        VideoGameDto game = discountedGames.get(index);
        PriceDto price = priceDtoMapper.fromModeltoDto(priceService.getActivePrice(game.getID()));
        price.setPrice(Math.round((price.price - price.price * (price.action.discountValue/100)) * 100.0) / 100.0);
        game.setPrice(price);
        return discountedGames;
    }

    public float sumPrices(List<VideoGameDto> games) {
        float totalPrice = 0.0f;
        for(VideoGameDto game : games) {
            totalPrice += game.price.price;
        }
        return totalPrice;
    }


//    private double percentage;
//
//    public PercentageDiscount(double percentage1){
//        this.percentage = percentage1;
//    }

//    @Override
//    public float applyDiscount(List<VideoGame> game) {
////        float totalPrice = game.getPrice
//        return 0;
//    }
}
