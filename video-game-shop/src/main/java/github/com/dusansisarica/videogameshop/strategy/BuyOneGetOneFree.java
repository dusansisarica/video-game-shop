package github.com.dusansisarica.videogameshop.strategy;

import github.com.dusansisarica.videogameshop.dto.PriceDiscountDto;
import github.com.dusansisarica.videogameshop.dto.PriceDto;
import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.enums.ActionType;
import github.com.dusansisarica.videogameshop.service.PriceService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class BuyOneGetOneFree implements DiscountStrategy {

    private final PriceService priceService;
    @Autowired
    public BuyOneGetOneFree(PriceService priceService){
        this.priceService = priceService;
    }
    @Override
    public PriceDiscountDto calculatePriceWithDiscount(PriceDiscountDto priceDiscountDto, float totalPrice) {
        int counter = 0;
        if (priceDiscountDto.flagBuyOneGetOneFree){
            return priceDiscountDto;
        }
        for (VideoGameDto game : priceDiscountDto.games){
                //PROVERITI DA NIJE SLUCAJNO NULL
                if (priceService.getActivePrice(game.getID()).getDiscount() != null && priceService.getActivePrice(game.getID()).getDiscount().getActionType() == ActionType.BUY_ONE_GET_ONE_FREE){
                    counter++;
                }
        }
        if (counter >= 2) {
            priceDiscountDto.flagBuyOneGetOneFree = true;
            priceDiscountDto.totalPrice = calculateDiscount(priceDiscountDto.games, totalPrice);
            return priceDiscountDto;
        }
        return priceDiscountDto;
    }

    public float calculateDiscount(List<VideoGameDto> games, float totalPrice){
        //VideoGameDto cheapestGame = findCheapestGame(games);
        List<VideoGameDto> discountedGames = changePriceDiscount(games, findCheapestGame(games));
        for (VideoGameDto game : discountedGames){
            System.out.println("--------------------------IGRA---------------------" + game.getTitle());
            System.out.println(priceService.getActivePrice(game.getID()).getPrice());
            System.out.println("---------------------------------------------------");
        }
        //float price = (float)priceService.getActivePrice(cheapestGame.getID()).getPrice();
        return sumPrices(discountedGames);
    }

    public int findCheapestGame(List<VideoGameDto> games){
        VideoGameDto cheapestGame = null;
        int index = 0;
        for (VideoGameDto game : games){
                if (priceService.getActivePrice(game.getID()).getDiscount() != null && priceService.getActivePrice(game.getID()).getDiscount().getActionType()== ActionType.BUY_ONE_GET_ONE_FREE){
                    if (cheapestGame == null || priceService.getActivePrice(game.getID()).getPrice() < priceService.getActivePrice(cheapestGame.getID()).getPrice()){
                        cheapestGame = game;
                    }
                }
                index++;
        }

        return games.indexOf(cheapestGame);
    }

    public List<VideoGameDto> changePriceDiscount(List<VideoGameDto> games, int index){
        List<VideoGameDto> discountedGames = games;
//        for (VideoGameDto game : games){
//            if (game.equals(cheapestGame)){
//                priceService.changeActivePrice(game.getID(), 0.0f);
//            }
//            discountedGames.add(game);
//        }
        VideoGameDto game = discountedGames.get(index);
        PriceDto price = new PriceDto();
        price.setPrice(0.0f);
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
}
