package github.com.dusansisarica.videogameshop.mapper;

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
        this.modelMapper = modelMapper;
    }

    public static User fromDTOtoModel(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }

    public static UserDto fromModeltoDTO(User model) {
        return modelMapper.map(model, UserDto.class);
    }
}
