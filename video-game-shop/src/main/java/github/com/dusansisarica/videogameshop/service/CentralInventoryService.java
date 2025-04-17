package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.CentralInventoryDto;
import github.com.dusansisarica.videogameshop.dto.CentralInventoryPagination;
import github.com.dusansisarica.videogameshop.dto.PaginationDto;
import github.com.dusansisarica.videogameshop.dto.ShopPagination;
import github.com.dusansisarica.videogameshop.mapper.CentralInventoryDtoMapper;
import github.com.dusansisarica.videogameshop.mapper.ShopDtoMapper;
import github.com.dusansisarica.videogameshop.model.CartItem;
import github.com.dusansisarica.videogameshop.model.CentralInventory;
import github.com.dusansisarica.videogameshop.model.PurchasedItem;
import github.com.dusansisarica.videogameshop.model.Shop;
import github.com.dusansisarica.videogameshop.repository.CentralInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CentralInventoryService {
    @Autowired
    private CentralInventoryRepository centralInventoryRepository;
    @Autowired
    private CentralInventoryDtoMapper centralInventoryDtoMapper;

    public CentralInventoryPagination getAll(PaginationDto paginationDto) {
        return getAllWithPagination(centralInventoryRepository.findAll(), paginationDto);
    }

    public CentralInventoryPagination getAllWithPagination(List<CentralInventory> centralInventories, PaginationDto paginationDto) {
        int startIndex = (paginationDto.pageNumber - 1) * paginationDto.pageSize;
        int endIndex = startIndex + paginationDto.pageSize;
        if (endIndex > centralInventories.size()) endIndex = centralInventories.size();
        if (startIndex > centralInventories.size()) centralInventories = new ArrayList<>();
        return new CentralInventoryPagination(centralInventoryDtoMapper.fromModeltoDTOList(centralInventories.subList(startIndex, endIndex)), centralInventories.size());
    }

    public void reduceCentralQuantity(List<PurchasedItem> items) {
        for (PurchasedItem item : items) {
            CentralInventory ci = centralInventoryRepository.findByGameID(item.getCartItem().getGame().getID());
            ci.setQuantity(ci.getQuantity() - item.getCartItem().getQuantity());
            centralInventoryRepository.save(ci);
        }
    }
}
