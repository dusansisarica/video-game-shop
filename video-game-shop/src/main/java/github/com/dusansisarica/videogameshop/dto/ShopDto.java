package github.com.dusansisarica.videogameshop.dto;

import github.com.dusansisarica.videogameshop.model.GameQuantity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShopDto {
    public Integer id;
    public String name;
    public List<GameQuantityDto> items;
    public AddressDto address;

    public ShopDto(Integer id, String name, List<GameQuantityDto> items, AddressDto address) {
        this.id = id;
        this.name = name;
        this.items = items;
        this.address = address;
    }

    public ShopDto(){}
}
