package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.CartDto;
import github.com.dusansisarica.videogameshop.dto.UserDto;
import github.com.dusansisarica.videogameshop.model.Cart;
import github.com.dusansisarica.videogameshop.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartDtoMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public CartDtoMapper(ModelMapper modelMapper) {
        CartDtoMapper.modelMapper = modelMapper;
    }

    public static Cart fromDTOtoModel(CartDto dto) {
        return new Cart(CartItemDtoMapper.fromDTOtoModelList(dto.getCartItems()));
    }

    public static Cart fromDTOtoModelSave(CartDto dto) {
        return new Cart(dto.ID, CartItemDtoMapper.fromDTOtoModelList(dto.getCartItems()));
    }


    public static CartDto fromModeltoDTO(Cart model) {
        return new CartDto(CartItemDtoMapper.fromModeltoDTOList(model.getCartItems()), model.getTotalPrice());
    }

    public static CartDto fromModeltoDTOSave(Cart model) {
        return new CartDto(model.getID(), CartItemDtoMapper.fromModeltoDTOList(model.getCartItems()));
    }
}
