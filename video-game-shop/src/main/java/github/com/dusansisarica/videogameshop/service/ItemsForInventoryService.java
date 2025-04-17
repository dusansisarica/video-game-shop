package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.model.ItemsForInventory;
import github.com.dusansisarica.videogameshop.model.PurchasedItem;
import github.com.dusansisarica.videogameshop.model.Shop;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import github.com.dusansisarica.videogameshop.repository.ItemsForInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsForInventoryService {

    @Autowired
    private ItemsForInventoryRepository itemsForInventoryRepository;

    @Autowired
    private ShopService shopService;

    public void addItem(List<PurchasedItem> purchasedItems) {
        for (PurchasedItem purchasedItem : purchasedItems) {
            VideoGame game = purchasedItem.getCartItem().getGame();
            int quantity = purchasedItem.getCartItem().getQuantity();
            if (!shopService.checkCentralInventoryForQuantity(game.getID(), quantity)){
                quantity = shopService.findMissingQuantity(game.getID(), quantity);
                shopService.findShopWithItem(purchasedItem.getCartItem().getGame().getID(), quantity);
            }
        }
    }
}
