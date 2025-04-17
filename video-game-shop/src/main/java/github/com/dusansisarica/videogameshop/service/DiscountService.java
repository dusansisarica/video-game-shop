package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.DiscountDto;
import github.com.dusansisarica.videogameshop.dto.PriceDto;
import github.com.dusansisarica.videogameshop.mapper.ActionDtoMapper;
import github.com.dusansisarica.videogameshop.model.Discount;
import github.com.dusansisarica.videogameshop.model.Price;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import github.com.dusansisarica.videogameshop.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    @Autowired
    private VideoGameService videoGameService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private ActionDtoMapper actionDtoMapper;
    @Autowired
    private DiscountRepository discountRepository;

    public DiscountDto addDiscount(Integer id, DiscountDto discountDto){
        VideoGame game = videoGameService.findByIdModel(id);
        Price currentPrice = priceService.getActivePrice(id);
        if (currentPrice != null) {
            Discount discount = discountRepository.save(actionDtoMapper.fromDTOtoModel(discountDto));
            currentPrice.setDiscount(discount);
            videoGameService.saveModel(game);
            return discountDto;
        }
        return discountDto;
    }

}
