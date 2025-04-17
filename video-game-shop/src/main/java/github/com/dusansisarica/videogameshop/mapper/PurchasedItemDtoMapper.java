package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.PurchasedItemDto;
import github.com.dusansisarica.videogameshop.dto.ShopDto;
import github.com.dusansisarica.videogameshop.model.PurchasedItem;
import github.com.dusansisarica.videogameshop.model.Shop;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PurchasedItemDtoMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public PurchasedItemDtoMapper(ModelMapper modelMapper) {
        PurchasedItemDtoMapper.modelMapper = modelMapper;
    }

    public static PurchasedItem fromDTOtoModel(PurchasedItemDto dto) {
        return new PurchasedItem(dto.id, CartItemDtoMapper.fromDTOtoModel(dto.cartItem));
    }

    public static PurchasedItemDto fromModeltoDTO(PurchasedItem model) {
        return new PurchasedItemDto(model.getId(), CartItemDtoMapper.fromModeltoDTO(model.getCartItem()));
    }

    public static List<PurchasedItemDto> fromModeltoDTOList(List<PurchasedItem> modelList) {
        List<PurchasedItemDto> dto = new ArrayList<>();
        for (PurchasedItem item : modelList) {
            PurchasedItemDto purchasedItemDto = fromModeltoDTO(item);
            dto.add(purchasedItemDto);
        }
        return dto;
    }

    public static List<PurchasedItem> fromDTOtoModelList(List<PurchasedItemDto> dtoList) {
        List<PurchasedItem> list = new ArrayList<>();
        for (PurchasedItemDto item : dtoList) {
            PurchasedItem purchasedItemDto = fromDTOtoModel(item);
            list.add(purchasedItemDto);
        }
        return list;
    }

}
