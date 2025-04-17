package github.com.dusansisarica.videogameshop.strategy;

import github.com.dusansisarica.videogameshop.enums.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscountFactory {
    private final BuyOneGetOneFree buyOneGetOneFree;
    private final PercentageDiscount percentageDiscount;
    private final QuantityDiscount quantityDiscount;

    @Autowired
    public DiscountFactory(BuyOneGetOneFree buyOneGetOneFree,
                           PercentageDiscount percentageDiscount,
                           QuantityDiscount quantityDiscount) {
        this.buyOneGetOneFree = buyOneGetOneFree;
        this.percentageDiscount = percentageDiscount;
        this.quantityDiscount = quantityDiscount;
    }

    public DiscountStrategy getDiscountStrategy(ActionType actionType) {
        switch (actionType) {
            case BUY_ONE_GET_ONE_FREE:
                return buyOneGetOneFree;
            case PERCENTAGE_DISCOUNT:
                return percentageDiscount;
            case QUANTITY_DISCOUNT:
                //return quantityDiscount;
            default:
                throw new IllegalArgumentException("Unknown discount type");
        }
    }

}
