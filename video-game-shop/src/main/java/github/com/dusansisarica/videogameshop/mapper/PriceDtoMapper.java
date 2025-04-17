package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.ActionDto;
import github.com.dusansisarica.videogameshop.dto.DiscountDto;
import github.com.dusansisarica.videogameshop.dto.PriceDto;
import github.com.dusansisarica.videogameshop.model.Price;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PriceDtoMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public PriceDtoMapper(ModelMapper modelMapper) {
        PriceDtoMapper.modelMapper = modelMapper;
    }

    public static Price fromDTOtoModel(PriceDto dto) {
        return new Price(dto.id,dto.price, dto.startDate, dto.endDate, ActionDtoMapper.fromDTOtoModel(dto.action));
    }

    public static PriceDto fromModeltoDto(Price model) {
        DiscountDto action = new DiscountDto();
        if (model.getDiscount() != null) {
            action = ActionDtoMapper.fromModeltoDtoDiscount(model.getDiscount());
        }
        return new PriceDto(model.getId(), model.getPrice(), model.getStartDate(), model.getEndDate(), action);
    }

    public static List<PriceDto> fromModelToDtoList(List<Price> modelList){
        List<PriceDto> dto = new ArrayList<>();
        for(Price p : modelList) {
            dto.add(fromModeltoDto(p));
        }
        return dto;
    }

}
