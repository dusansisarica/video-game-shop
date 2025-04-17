package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.model.PurchasedItem;
import github.com.dusansisarica.videogameshop.repository.PurchasedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchasedItemService {
    @Autowired
    private PurchasedItemRepository purchasedItemRepository;

    public void save(PurchasedItem item) {
        purchasedItemRepository.save(item);
    }
}
