package github.com.dusansisarica.videogameshop.dto;

import github.com.dusansisarica.videogameshop.model.GameQuantity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ShopDto {
    public Integer id;
    public String name;
    public List<GameQuantityDto> items = new ArrayList<>();
    public AddressDto address;
    public int totalItems;
    public boolean deleted;


    public ShopDto(Integer id, String name, List<GameQuantityDto> items, AddressDto address) {
        this.id = id;
        this.name = name;
        this.items = items;
        this.address = address;
    }

    public ShopDto(Integer id, String name, List<GameQuantityDto> items, AddressDto address, boolean deleted) {
        this.id = id;
        this.name = name;
        this.items = items;
        this.address = address;
        this.deleted = deleted;
    }


    public ShopDto() {
    }
}
