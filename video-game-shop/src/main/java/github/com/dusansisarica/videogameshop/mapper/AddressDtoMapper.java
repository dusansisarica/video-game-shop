package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.AddressDto;
import github.com.dusansisarica.videogameshop.dto.CityDto;
import github.com.dusansisarica.videogameshop.dto.CountryDto;
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
        AddressDtoMapper.modelMapper = modelMapper;
    }

    public static Address fromDTOtoModel(AddressDto dto) {
        return modelMapper.map(dto, Address.class);
    }

    public static AddressDto fromModeltoDTO(Address model) {

        return new AddressDto(model.getAddress(), new CityDto(model.getCity().getId(), new CountryDto(model.getCity().getCountry().getId(),model.getCity().getCountry().getName()), model.getCity().getCityName()), model.getLatitude(), model.getLongitude());
    }
}
