package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.CentralInventoryDto;
import github.com.dusansisarica.videogameshop.dto.GameQuantityDto;
import github.com.dusansisarica.videogameshop.model.CentralInventory;
import github.com.dusansisarica.videogameshop.model.GameQuantity;
import github.com.dusansisarica.videogameshop.service.VideoGameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CentralInventoryDtoMapper {
    private static ModelMapper modelMapper;
    @Autowired
    private static VideoGameService videoGameService;

    @Autowired
    public CentralInventoryDtoMapper(ModelMapper modelMapper) {
        CentralInventoryDtoMapper.modelMapper = modelMapper;
    }

    public static CentralInventory fromDTOtoModel(CentralInventoryDto dto) {
        return new CentralInventory(dto.getId(), VideoGameDtoMapper.fromDTOtoModel(dto.videoGame), dto.quantity);
    }

    public static CentralInventoryDto fromModeltoDTO(CentralInventory model) {
        return new CentralInventoryDto(model.getId(), VideoGameDtoMapper.fromModeltoDTO(model.getGame()), model.getQuantity());
    }

    public static List<CentralInventoryDto> fromModeltoDTOList(List<CentralInventory> modelList) {
        List<CentralInventoryDto> dto = new ArrayList<>();
        for (CentralInventory game : modelList) {
            dto.add(fromModeltoDTO(game));
        }
        return dto;
    }

}
