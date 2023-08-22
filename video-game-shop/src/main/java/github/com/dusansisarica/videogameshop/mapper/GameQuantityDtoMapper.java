package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.GameQuantityDto;
import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.model.GameQuantity;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import github.com.dusansisarica.videogameshop.service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameQuantityDtoMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public GameQuantityDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static GameQuantity fromDTOtoModel(GameQuantityDto dto) {
        return modelMapper.map(dto, GameQuantity.class);
    }

    public static GameQuantityDto fromModeltoDTO(GameQuantity model) {
        return new GameQuantityDto(model.getId(), model.getGameId(), model.getQuantity(), model.getShop().getId());
    }

    public static List<GameQuantityDto> fromModeltoDTOList(List<GameQuantity> modelList){
        List<GameQuantityDto> dto = new ArrayList<>();
        for(GameQuantity game : modelList){
            dto.add(fromModeltoDTO(game));
        }
        return dto;
    }

    public static List<GameQuantity> fromDTOtoModelList(List<GameQuantityDto> modelList){
        List<GameQuantity> dto = new ArrayList<>();
        for(GameQuantityDto game : modelList){
            dto.add(fromDTOtoModel(game));
        }
        return dto;
    }
}
