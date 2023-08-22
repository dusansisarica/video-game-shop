package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.GameQuantityDto;
import github.com.dusansisarica.videogameshop.dto.ShopDto;
import github.com.dusansisarica.videogameshop.mapper.GameQuantityDtoMapper;
import github.com.dusansisarica.videogameshop.mapper.ShopDtoMapper;
import github.com.dusansisarica.videogameshop.model.GameQuantity;
import github.com.dusansisarica.videogameshop.model.Shop;
import github.com.dusansisarica.videogameshop.repository.GameQuantityRepository;
import github.com.dusansisarica.videogameshop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopDtoMapper shopDtoMapper;
    @Autowired
    private GameQuantityRepository gameQuantityRepository;
    @Autowired
    private GameQuantityDtoMapper gameQuantityDtoMapper;


    public ShopDto save(ShopDto shopDto) {
        shopRepository.save(shopDtoMapper.fromDTOtoModel(shopDto));
        return shopDto;
    }

    public ShopDto findById(Integer id) {
        Shop shop = shopRepository.findById(id).orElse(null);
        if (shop == null) return null;
        ShopDto dto = shopDtoMapper.fromModeltoDTO(shop);
        dto.setItems(gameQuantityDtoMapper.fromModeltoDTOList(gameQuantityRepository.findAllByShopId(shop.getId())));
        return shopDtoMapper.fromModeltoDTO(shop);
    }

    public List<ShopDto> getAll() {
        List<ShopDto> shops = new ArrayList<>();
        for(ShopDto s : shopDtoMapper.fromModeltoDTOList(shopRepository.findAll())){
            s.setItems(gameQuantityDtoMapper.fromModeltoDTOList(gameQuantityRepository.findAllByShopId(s.id)));
            shops.add(s);
        }
        return shops;
    }

    public List<ShopDto> findShopsByItemId(Integer id){
        return shopDtoMapper.fromModeltoDTOList(gameQuantityRepository.findShopIdsByGameId(id));
    }

    public List<GameQuantityDto> addGame(GameQuantityDto item) {
        gameQuantityRepository.save(fromDTOtoModel(item));
        return gameQuantityDtoMapper.fromModeltoDTOList(gameQuantityRepository.findAllByShopId(item.shopId));
    }

    public GameQuantity fromDTOtoModel(GameQuantityDto dto) {
        return new GameQuantity(dto.id, dto.gameId, dto.quantity, shopRepository.findById(dto.shopId).orElse(null));
    }

}
