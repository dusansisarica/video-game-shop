package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.GameQuantityDto;
import github.com.dusansisarica.videogameshop.dto.ShopDto;
import github.com.dusansisarica.videogameshop.dto.UserDto;
import github.com.dusansisarica.videogameshop.model.GameQuantity;
import github.com.dusansisarica.videogameshop.model.Shop;
import github.com.dusansisarica.videogameshop.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShopDtoMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public ShopDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Shop fromDTOtoModel(ShopDto dto) {
        return new Shop(dto.name, AddressDtoMapper.fromDTOtoModel(dto.address), GameQuantityDtoMapper.fromDTOtoModelList(dto.items));
    }

    public static ShopDto fromModeltoDTO(Shop model) {
        return modelMapper.map(model, ShopDto.class);
    }

    public static List<ShopDto> fromModeltoDTOList(List<Shop> modelList){
        List<ShopDto> dto = new ArrayList<>();
        for(Shop shop : modelList){
            ShopDto shopDto = new ShopDto(shop.getId(), shop.getName(), null, AddressDtoMapper.fromModeltoDTO(shop.getAddress()));
            dto.add(shopDto);
        }
        return dto;
    }
}
