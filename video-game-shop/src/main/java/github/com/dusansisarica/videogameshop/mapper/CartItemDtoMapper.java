package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.CartDto;
import github.com.dusansisarica.videogameshop.dto.CartItemDto;
import github.com.dusansisarica.videogameshop.dto.GameQuantityDto;
import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.model.CartItem;
import github.com.dusansisarica.videogameshop.model.GameQuantity;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemDtoMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public CartItemDtoMapper(ModelMapper modelMapper) {
        CartItemDtoMapper.modelMapper = modelMapper;
    }

    public static CartItem fromDTOtoModel(CartItemDto dto) {
        return new CartItem(VideoGameDtoMapper.fromDTOtoModel(dto.game), dto.quantity);
    }

    public static CartItem fromDTOtoModelSave(CartItemDto dto) {
        return new CartItem(CartDtoMapper.fromDTOtoModelSave(dto.getCart()), VideoGameDtoMapper.fromDTOtoModel(dto.game), dto.quantity);
    }


    public static CartItemDto fromModeltoDTO(CartItem model) {
        return new CartItemDto(model.getID(), VideoGameDtoMapper.fromModeltoDTO(model.getGame()), model.getQuantity());
    }

    public static List<CartItemDto> fromModeltoDTOList(List<CartItem> modelList) {
        List<CartItemDto> dto = new ArrayList<>();
        for (CartItem item : modelList) {
            dto.add(fromModeltoDTO(item));
        }
        return dto;
    }

    public static List<CartItem> fromDTOtoModelList(List<CartItemDto> modelList) {
        List<CartItem> dto = new ArrayList<>();
        for (CartItemDto item : modelList) {
            dto.add(fromDTOtoModel(item));
        }
        return dto;
    }
}
