package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.AddressDto;
import github.com.dusansisarica.videogameshop.dto.UserDto;
import github.com.dusansisarica.videogameshop.model.Address;
import github.com.dusansisarica.videogameshop.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public AddressDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Address fromDTOtoModel(AddressDto dto) {
        return modelMapper.map(dto, Address.class);
    }

    public static AddressDto fromModeltoDTO(Address model) {
        return modelMapper.map(model, AddressDto.class);
    }
}
