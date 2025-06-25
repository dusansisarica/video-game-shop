package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.UserDto;
import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.model.User;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import github.com.dusansisarica.videogameshop.service.PriceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoGameDtoMapper {
    private static ModelMapper modelMapper;
    private static PriceService priceService;

    @Autowired
    public VideoGameDtoMapper(ModelMapper modelMapper, PriceService priceService) {
        VideoGameDtoMapper.modelMapper = modelMapper;
        VideoGameDtoMapper.priceService = priceService;
    }

    public static VideoGame fromDTOtoModel(VideoGameDto dto) {
        return modelMapper.map(dto, VideoGame.class);
    }

    public static VideoGameDto fromModeltoDTO(VideoGame model) {
        return new VideoGameDto(model.getID(),
                model.getTitle(),
                model.getDescription(),
//                PriceDtoMapper.fromModeltoDto(model.getPrice().get(model.getPrice().size()-1)),
//                PriceDtoMapper.fromModeltoDto(priceService.calculatePriceForGame(model)),
                priceService.calculatePriceForGame(model),
                model.getRelease_date(),
                model.getPlatforms(),
                model.getGenres(),
                model.getImage(),
                model.calculateAverageRating(),
                model.isDeleted());
    }

    public static List<VideoGameDto> fromModeltoDTOList(List<VideoGame> modelList) {
        List<VideoGameDto> dto = new ArrayList<>();
        for (VideoGame game : modelList) {
            dto.add(fromModeltoDTO(game));
        }
        return dto;
    }
}
