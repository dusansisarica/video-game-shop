package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.GameQuantityDto;
import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.model.GameQuantity;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import github.com.dusansisarica.videogameshop.service.ShopService;
import github.com.dusansisarica.videogameshop.service.VideoGameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameQuantityDtoMapper {
    private static ModelMapper modelMapper;
    @Autowired
    private static VideoGameService videoGameService;

    @Autowired
    public GameQuantityDtoMapper(ModelMapper modelMapper) {
        GameQuantityDtoMapper.modelMapper = modelMapper;
    }

    public static GameQuantity fromDTOtoModel(GameQuantityDto dto) {
        return modelMapper.map(dto, GameQuantity.class);
    }

    public static GameQuantityDto fromModeltoDTO(GameQuantity model) {
        return new GameQuantityDto(model.getId(), model.getGameID(), model.getQuantity(), model.getShop().getId(), VideoGameDtoMapper.fromModeltoDTO(model.getVideoGame()));
    }

    public static List<GameQuantityDto> fromModeltoDTOList(List<GameQuantity> modelList) {
        List<GameQuantityDto> dto = new ArrayList<>();
        for (GameQuantity game : modelList) {
            dto.add(fromModeltoDTO(game));
        }
        return dto;
    }

    public static List<GameQuantityDto> fromModeltoDTOListWithGame(List<GameQuantity> modelList) {
        List<GameQuantityDto> dto = new ArrayList<>();
        for (GameQuantity game : modelList) {
            GameQuantityDto gameQuantityDto = fromModeltoDTO(game);
//            gameQuantityDto.setGame( VideoGameDtoMapper.fromModeltoDTO(game.getGame()));
            dto.add(gameQuantityDto);
        }
        return dto;
    }


    public static List<GameQuantity> fromDTOtoModelList(List<GameQuantityDto> modelList) {
        List<GameQuantity> dto = new ArrayList<>();
        for (GameQuantityDto game : modelList) {
            dto.add(fromDTOtoModel(game));
        }
        return dto;
    }
}
