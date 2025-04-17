package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.*;
import github.com.dusansisarica.videogameshop.enums.InventoryStatus;
import github.com.dusansisarica.videogameshop.mapper.*;
import github.com.dusansisarica.videogameshop.model.*;
import github.com.dusansisarica.videogameshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private VideoGameService videoGameService;
    @Autowired
    private ItemsForInventoryRepository itemsForInventoryRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemsForInventoryDtoMapper itemsForInventoryDtoMapper;
    @Autowired
    private CentralInventoryRepository centralInventoryRepository;
    @Autowired
    private CountryRepositroy countryRepositroy;
    @Autowired
    private CityRepository cityRepository;

    public ShopDto save(ShopDto shopDto) {
        Country country = countryRepositroy.findByName(shopDto.getAddress().getCity().getCountryDto().name);
        if (country == null) {
            country = new Country(shopDto.getAddress().getCity().getCountryDto().name);
        }
        City city = cityRepository.findByCityNameAndCountryName(shopDto.getAddress().getCity().name, country.getName());
        if (city == null) {
            city = new City(shopDto.getAddress().getCity().name, country);
        }
        countryRepositroy.save(country);
        cityRepository.save(city);

        Address address = new Address(shopDto.address.address, city, shopDto.address.longitude, shopDto.address.latitude);
        Shop shop = new Shop();
        shop.setDeleted(false);
        shop.setName(shopDto.name);
        shop.setAddress(address);
        shopRepository.save(shop);
        return shopDto;
    }

    public ShopDto findById(Integer id) {
        Shop shop = shopRepository.findById(id).orElse(null);
        if (shop == null) return null;
        ShopDto dto = ShopDtoMapper.fromModeltoDTO(shop);
        dto.setAddress(AddressDtoMapper.fromModeltoDTO(shop.getAddress()));
        dto.setItems(makeItemsForShop(dto.id));
        return dto;
    }

    public List<GameQuantityDto> makeItemsForShop(Integer shopId) {
        List<GameQuantityDto> items = GameQuantityDtoMapper.fromModeltoDTOList(gameQuantityRepository.findAllByShopId(shopId));
        List<GameQuantityDto> returnVal = new ArrayList<>();
        for (GameQuantityDto item : items) {
            item.setGame(videoGameService.findById(item.gameId));
            returnVal.add(item);
        }
        return returnVal;
    }

    public ShopPagination getAll(PaginationDto paginationDto) {
        List<Shop> allShops = shopRepository.findAll();
        allShops = allShops.stream()
                .filter(shop -> paginationDto.searchQuery == null || paginationDto.searchQuery.isEmpty() ||
                        shop.getName().toLowerCase().contains(paginationDto.searchQuery.toLowerCase()))
                .collect(Collectors.toList());

        return getAllWithPagination(allShops, paginationDto);
    }

    public ShopPagination getAllWithPagination(List<Shop> shops, PaginationDto paginationDto) {
        int startIndex = (paginationDto.pageNumber - 1) * paginationDto.pageSize;
        int endIndex = startIndex + paginationDto.pageSize;
        if (endIndex > shops.size()) endIndex = shops.size();
        if (startIndex > shops.size()) shops = new ArrayList<>();
        return new ShopPagination(ShopDtoMapper.fromModeltoDTOList(shops.subList(startIndex, endIndex)), shops.size());
    }

    public GameQuantityPagination getAllWithPaginationGameQuantity(List<GameQuantity> gameQuantities, PaginationDto paginationDto) {
        int startIndex = (paginationDto.pageNumber - 1) * paginationDto.pageSize;
        int endIndex = startIndex + paginationDto.pageSize;
        if (endIndex > gameQuantities.size()) endIndex = gameQuantities.size();
        if (startIndex > gameQuantities.size()) gameQuantities = new ArrayList<>();
        return new GameQuantityPagination(GameQuantityDtoMapper.fromModeltoDTOListWithGame(gameQuantities.subList(startIndex, endIndex)), gameQuantities.size());
    }


    public ShopPagination findShopsByItemId(Integer id, PaginationDto paginationDto) {
        return getAllWithPagination(gameQuantityRepository.findShopIdsByGameID(id), paginationDto);
    }

    public void findShopWithItem(Integer gameId, Integer quantity) {
        List<Integer> declinedShops = new ArrayList<>();
        declinedShops.add(0);

        while (quantity > 0) {
            GameQuantity gameQuantity;
            if (gameQuantityRepository.findFirstByGameIDAndQuantityMinusReservedGreaterThanEqualOrderByQuantityMinusReservedDesc(gameId, quantity, declinedShops).size() > 0) {
                gameQuantity = gameQuantityRepository.findFirstByGameIDAndQuantityMinusReservedGreaterThanEqualOrderByQuantityMinusReservedDesc(gameId, quantity, declinedShops).get(0);
            } else {
                gameQuantity = null;
            }

            if (gameQuantity != null) {
                //Ako postoji prodavnica sa odredjenom kolicinog igrica:
                //reduceGameQuantity(gameQuantity.getShop().getId(), gameQuantity.getGameId(), quantity);
                ItemsForInventory item = new ItemsForInventory(null, videoGameService.findByIdModel(gameId), quantity, gameQuantity.getShop(), InventoryStatus.PENDING, new ArrayList<>());
                gameQuantity.setReserved(quantity);
                itemsForInventoryRepository.save(item);
                gameQuantityRepository.save(gameQuantity);
                quantity = 0;

//                return gameQuantity.getShop();
            } else {
                GameQuantity shopWithMostQuantity = gameQuantityRepository.findFirstByVideoGameIDOrderByQuantityMinusReservedDesc(gameId, declinedShops).get(0);

                if (shopWithMostQuantity != null && (shopWithMostQuantity.getQuantity()- shopWithMostQuantity.getReserved() > 0)) {
                    int shopQuantity = shopWithMostQuantity.getQuantity();
                    quantity -= shopQuantity;
                    ItemsForInventory item = new ItemsForInventory(null, videoGameService.findByIdModel(gameId), shopQuantity, shopWithMostQuantity.getShop(), InventoryStatus.PENDING, new ArrayList<>());
                    itemsForInventoryRepository.save(item);
                    shopWithMostQuantity.setReserved(shopQuantity);
                    gameQuantityRepository.save(shopWithMostQuantity);
                    //reduceGameQuantity(shopWithMostQuantity.getShop().getId(), shopWithMostQuantity.getGameId(), shopQuantity);
                } else {
                    System.out.println("NEMAMO DOVOLJNO!!!");
                    quantity = 0;
//                    return null;
                }

            }
        }
//        return null;
    }

    public boolean checkCentralInventoryForQuantity(Integer gameId, Integer quantity) {
        List<CentralInventory> centralInventoryItems = centralInventoryRepository.findAll();
        CentralInventory matchingItem = centralInventoryItems.stream()
                .filter(item -> item.getGame().getID() == gameId)
                .findFirst()
                .orElse(null);

        if (matchingItem != null && matchingItem.getQuantity() >= quantity) {
            return true;
        } else {
            return false;
        }

    }

    public int findMissingQuantity(Integer gameId, Integer quantity) {
        List<CentralInventory> centralInventoryItems = centralInventoryRepository.findAll();
        CentralInventory matchingItem = centralInventoryItems.stream()
                .filter(item -> item.getGame().getID() == gameId)
                .findFirst()
                .orElse(null);
        if (matchingItem == null) {
            return quantity;
        }
        else {
            return quantity - matchingItem.getQuantity();
        }
    }

    public void findDIfferentShopWithItem(ItemsForInventory itemForInventory) {
        Integer quantity = itemForInventory.getQuantity();
        Integer gameId = itemForInventory.getVideoGame().getID();
        List<Integer> declinedShops = itemForInventory.getDeclinedShops();
        itemsForInventoryRepository.delete(itemForInventory);
        while (quantity > 0) {
            GameQuantity gameQuantity;
            if (gameQuantityRepository.findFirstByGameIDAndQuantityMinusReservedGreaterThanEqualOrderByQuantityMinusReservedDesc(gameId, quantity, declinedShops).size() > 0) {
                gameQuantity = gameQuantityRepository.findFirstByGameIDAndQuantityMinusReservedGreaterThanEqualOrderByQuantityMinusReservedDesc(gameId, quantity, declinedShops).get(0);
            } else {
                gameQuantity = null;
            }

            if (gameQuantity != null) {
                //reduceGameQuantity(gameQuantity.getShop().getId(), gameQuantity.getGameId(), quantity);
                ItemsForInventory item = new ItemsForInventory(null, videoGameService.findByIdModel(gameId), quantity, gameQuantity.getShop(), InventoryStatus.PENDING, new ArrayList<>());
                gameQuantity.setReserved(quantity);
                itemsForInventoryRepository.save(item);
                gameQuantityRepository.save(gameQuantity);
                quantity = 0;

//                return gameQuantity.getShop();
            } else {
                GameQuantity shopWithMostQuantity = gameQuantityRepository.findFirstByVideoGameIDOrderByQuantityMinusReservedDesc(gameId, declinedShops).get(0);

                if (shopWithMostQuantity != null) {
                    int shopQuantity = shopWithMostQuantity.getQuantity();
                    quantity -= shopQuantity;
                    ItemsForInventory item = new ItemsForInventory(null, videoGameService.findByIdModel(gameId), shopQuantity, shopWithMostQuantity.getShop(), InventoryStatus.PENDING, new ArrayList<>());
                    itemsForInventoryRepository.save(item);
                    shopWithMostQuantity.setReserved(shopQuantity);
                    gameQuantityRepository.save(shopWithMostQuantity);
                    //reduceGameQuantity(shopWithMostQuantity.getShop().getId(), shopWithMostQuantity.getGameId(), shopQuantity);
                } else {
//                    return null;
                }

            }
        }
//        return null;
    }


    public List<GameQuantityDto> addGame(GameQuantityDto item) {
        gameQuantityRepository.save(fromDTOtoModel(item));
        return GameQuantityDtoMapper.fromModeltoDTOList(gameQuantityRepository.findAllByShopId(item.shopId));
    }

    public void reduceGameQuantity(int shopId, int gameId, int quantity) {
        GameQuantity gameQuantity = gameQuantityRepository.findByShopIdAndGameID(shopId, gameId);
        int leftQuantity = gameQuantity.getQuantity() - quantity;
        if (leftQuantity == 0) gameQuantityRepository.delete(gameQuantity);
        else {
            gameQuantity.setQuantity(leftQuantity);
            gameQuantity.setReserved(gameQuantity.getReserved() - quantity);
            gameQuantityRepository.save(gameQuantity);
        }
//        ItemsForInventory item = new ItemsForInventory(null, videoGameService.findByIdModel(gameId), quantity, gameQuantity.getShop());
//        itemsForInventoryRepository.save(item);
    }

    public GameQuantity fromDTOtoModel(GameQuantityDto dto) {
        return new GameQuantity(dto.id, dto.gameId, dto.quantity, dto.reserved, shopRepository.findById(dto.shopId).orElse(null), VideoGameDtoMapper.fromDTOtoModel(dto.getGame()));
    }

    public ShopPagination updateShop(ShopDto shopDto, PaginationDto paginationDto) {
        Shop shop = shopRepository.findById(shopDto.id).orElse(null);
        shop.setName(shopDto.name);
        Country country = countryRepositroy.findByName(shopDto.getAddress().getCity().getCountryDto().name);
        if (country == null) {
            country = new Country(shopDto.getAddress().getCity().getCountryDto().name);
        }
        City city = cityRepository.findByCityNameAndCountryName(shopDto.getAddress().getCity().name, country.getName());
        if (city == null) {
            city = new City(shopDto.getAddress().getCity().name, country);
        }
        countryRepositroy.save(country);
        cityRepository.save(city);
        Address address = new Address(shopDto.address.address, city, shopDto.address.longitude, shopDto.address.latitude);
        shop.setAddress(address);
        shopRepository.save(shop);
        return getAllWithPagination(shopRepository.findAll(), paginationDto);
    }

    public ShopPagination deleteShop(Integer id, PaginationDto paginationDto) {
        Shop shop = shopRepository.findById(id).orElse(null);
        shop.setDeleted(true);
        shopRepository.save(shop);
        List<GameQuantity> shopItems = gameQuantityRepository.findAllByShopId(shop.getId());
        deleteAllGameQuantity(shopItems);
        sendShopsOrders(shop.getId());
        return getAllWithPagination(shopRepository.findAll(), paginationDto);
    }

    private void deleteAllGameQuantity(List<GameQuantity> shopItems) {
        gameQuantityRepository.deleteAll(shopItems);
    }

    private void sendShopsOrders(Integer id) {
        List<ItemsForInventory> items = itemsForInventoryRepository.findAllByShopIdAndInventoryStatus(id, InventoryStatus.PENDING);
        for (ItemsForInventory item : items) {
            findShopWithItem(item.getVideoGame().getID(), item.getQuantity());
        }
        itemsForInventoryRepository.deleteAll(items);
    }

    public GameQuantityPagination findItemsForShop(String email, PaginationDto paginationDto) {
        User user = userService.findByEmail(email);
        List<GameQuantity> gameQuantities = gameQuantityRepository.findAllByShopId(user.getShop().getId());
        gameQuantities = gameQuantities.stream()
                .filter(game -> paginationDto.searchQuery == null || paginationDto.searchQuery.isEmpty() ||
                        game.getVideoGame().getTitle().toLowerCase().contains(paginationDto.searchQuery.toLowerCase()))
                .collect(Collectors.toList());
        return getAllWithPaginationGameQuantity(gameQuantities, paginationDto);
    }

    public List<ItemsForInventoryDto> getOrdersForShop(String email) {
        User user = userService.findByEmail(email);
        List<InventoryStatus> inventoryStatuses = new ArrayList<>();
        inventoryStatuses.add(InventoryStatus.PENDING);
        inventoryStatuses.add(InventoryStatus.ACCEPTED);
        return ItemsForInventoryDtoMapper.fromModeltoDTOList(itemsForInventoryRepository.findAllByShopIdAndInventoryStatus(user.getShop().getId(), inventoryStatuses));
    }

    public List<ItemsForInventoryDto> acceptOrder(String email, Integer id) {
        User user = userService.findByEmail(email);
        ItemsForInventory item = itemsForInventoryRepository.findById(id).orElse(null);
        reduceGameQuantity(user.getShop().getId(), item.getVideoGame().getID(), item.getQuantity());
        item.setInventoryStatus(InventoryStatus.ACCEPTED);
        itemsForInventoryRepository.save(item);
        return getOrdersForShop(email);
    }

    public void reduceReserved(Integer shopId, Integer gameId, Integer quantity) {
        GameQuantity gameQuantity = gameQuantityRepository.findByShopIdAndGameID(shopId, gameId);
        gameQuantity.setReserved(gameQuantity.getReserved() - quantity);
        gameQuantityRepository.save(gameQuantity);
    }

    public List<ItemsForInventoryDto> declineOrder(String email, Integer id) {
        User user = userService.findByEmail(email);
        ItemsForInventory item = itemsForInventoryRepository.findById(id).orElse(null);
        List<Integer> shops = item.getDeclinedShops();
        shops.add(user.getShop().getId());
        item.setDeclinedShops(shops);
        findDIfferentShopWithItem(item);
//        findShopWithItem(item.getVideoGame().getID(), item.getQuantity());
        reduceReserved(user.getShop().getId(), item.getVideoGame().getID(), item.getQuantity());
        item.setInventoryStatus(InventoryStatus.DECLINED);
        itemsForInventoryRepository.save(item);
        return getOrdersForShop(email);
    }


    public List<ItemsForInventoryDto> shipOrder(String email, Integer id) {
        ItemsForInventory item = itemsForInventoryRepository.findById(id).orElse(null);
        item.setInventoryStatus(InventoryStatus.SHIPPED);
        itemsForInventoryRepository.save(item);
        addToCentralInventory(item.getVideoGame(), item.getQuantity());
//        centralInventoryRepository.save(new CentralInventory(null, item.getVideoGame(), item.getQuantity()));
        return getOrdersForShop(email);
    }

    public void addToCentralInventory(VideoGame game, Integer quantity) {
        if (centralInventoryRepository.findByGameID(game.getID()) == null) {
            centralInventoryRepository.save(new CentralInventory(null, game, quantity));
        } else {
            CentralInventory ci = centralInventoryRepository.findByGameID(game.getID());
            ci.setQuantity(ci.getQuantity() + quantity);
            centralInventoryRepository.save(ci);
        }
    }

    public GameQuantityPagination getGamesForShop(Integer shopId, PaginationDto paginationDto) {
        return getAllWithPaginationGameQuantity(gameQuantityRepository.findAllByShopId(shopId), paginationDto);
    }
}
