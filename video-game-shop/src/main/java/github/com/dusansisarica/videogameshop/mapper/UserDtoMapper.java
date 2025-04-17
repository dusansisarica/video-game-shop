package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.UserDetailDto;
import github.com.dusansisarica.videogameshop.dto.UserDto;
import github.com.dusansisarica.videogameshop.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public UserDtoMapper(ModelMapper modelMapper) {
        UserDtoMapper.modelMapper = modelMapper;
    }

    public static User fromDTOtoModel(UserDto dto) {
        if (dto == null) {
            return null;
        }
        return modelMapper.map(dto, User.class);
    }

    public static UserDto fromModeltoDTO(User model) {
        return modelMapper.map(model, UserDto.class);
    }

    public static UserDetailDto fromModeltoDTODetail(User model) {
        return new UserDetailDto(model.getID(), model.getEmail(), model.getName(), model.getSurname(), AddressDtoMapper.fromModeltoDTO(model.getAddress()));
    }

}
