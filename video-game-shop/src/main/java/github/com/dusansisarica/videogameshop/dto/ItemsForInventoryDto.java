package github.com.dusansisarica.videogameshop.dto;

import github.com.dusansisarica.videogameshop.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemsForInventoryDto {
    public Integer ID;

    public VideoGameDto videoGame;

    public int quantity;

    public ShopDto shop;

    public InventoryStatus inventoryStatus;
    public List<Integer> declinedShops;
}
