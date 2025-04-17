package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.ActionDto;
import github.com.dusansisarica.videogameshop.dto.DiscountDto;
import github.com.dusansisarica.videogameshop.model.Discount;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionDtoMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public ActionDtoMapper(ModelMapper modelMapper) {
        ActionDtoMapper.modelMapper = modelMapper;
    }

    public static Discount fromDTOtoModel(DiscountDto dto) {
        return new Discount(dto.id, dto.name, dto.actionType, dto.discountValue, dto.startDate, dto.endDate);
    }

    public static ActionDto fromModeltoDto(Discount model) {
        return new ActionDto(model.getId(), model.getName(), model.getActionType(), model.getDiscountValue(), model.getStartDate(), model.getEndDate());
    }
    public static DiscountDto fromModeltoDtoDiscount(Discount model) {
        return new DiscountDto(model.getId(), model.getName(), model.getActionType(), model.getDiscountValue(), model.getStartDate(), model.getEndDate());
    }




}
