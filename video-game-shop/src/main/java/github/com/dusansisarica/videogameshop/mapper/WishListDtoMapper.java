package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.dto.WishListDto;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import github.com.dusansisarica.videogameshop.model.WishList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WishListDtoMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public WishListDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static WishList fromDTOtoModel(WishListDto dto) {
        return modelMapper.map(dto, WishList.class);
    }

    public static WishListDto fromModeltoDTO(WishList model) {
        return modelMapper.map(model, WishListDto.class);
    }

    public static List<WishListDto> fromModeltoDTOList(List<WishList> modelList){
        List<WishListDto> dto = new ArrayList<>();
        for(WishList game : modelList){
            dto.add(fromModeltoDTO(game));
        }
        return dto;
    }

}
