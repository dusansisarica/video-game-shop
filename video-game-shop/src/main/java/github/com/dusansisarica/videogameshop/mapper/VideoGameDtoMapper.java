package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.UserDto;
import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.model.User;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoGameDtoMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public VideoGameDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static VideoGame fromDTOtoModel(VideoGameDto dto) {
        return modelMapper.map(dto, VideoGame.class);
    }

    public static VideoGameDto fromModeltoDTO(VideoGame model) {
        return modelMapper.map(model, VideoGameDto.class);
    }

    public static List<VideoGameDto> fromModeltoDTOList(List<VideoGame> modelList){
        List<VideoGameDto> dto = new ArrayList<>();
        for(VideoGame game : modelList){
            dto.add(fromModeltoDTO(game));
        }
        return dto;
    }
}
