package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.ItemsForInventoryDto;
import github.com.dusansisarica.videogameshop.dto.ReviewDto;
import github.com.dusansisarica.videogameshop.dto.ShopDto;
import github.com.dusansisarica.videogameshop.model.ItemsForInventory;
import github.com.dusansisarica.videogameshop.model.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemsForInventoryDtoMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public ItemsForInventoryDtoMapper(ModelMapper modelMapper) {
        ItemsForInventoryDtoMapper.modelMapper = modelMapper;
    }

    public static ItemsForInventory fromDTOtoModel(ItemsForInventoryDto dto) {
        return new ItemsForInventory(
                dto.ID,
                VideoGameDtoMapper.fromDTOtoModel(dto.videoGame),
                dto.quantity,
                ShopDtoMapper.fromDTOtoModel(dto.shop),
                dto.inventoryStatus,
                dto.declinedShops
        );
    }

    public static ItemsForInventoryDto fromModeltoDTO(ItemsForInventory model) {
        return new ItemsForInventoryDto(
                model.getID(),
                VideoGameDtoMapper.fromModeltoDTO(model.getVideoGame()),
                model.getQuantity(),
                null,
                //ShopDtoMapper.fromModeltoDTO(model.getShop()),
                model.getInventoryStatus(),
                model.getDeclinedShops());
    }

    public static List<ItemsForInventoryDto> fromModeltoDTOList(List<ItemsForInventory> modelList) {
        List<ItemsForInventoryDto> dto = new ArrayList<>();
        for (ItemsForInventory item : modelList) {
            ItemsForInventoryDto itemForInventoryDto = fromModeltoDTO(item);
            dto.add(itemForInventoryDto);
        }
        return dto;
    }

}
