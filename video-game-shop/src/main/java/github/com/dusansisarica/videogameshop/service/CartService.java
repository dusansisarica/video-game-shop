package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.CartDto;
import github.com.dusansisarica.videogameshop.dto.CartItemDto;
import github.com.dusansisarica.videogameshop.dto.PriceDiscountDto;
import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.enums.CartItemStatus;
import github.com.dusansisarica.videogameshop.mapper.CartDtoMapper;
import github.com.dusansisarica.videogameshop.mapper.CartItemDtoMapper;
import github.com.dusansisarica.videogameshop.mapper.VideoGameDtoMapper;
import github.com.dusansisarica.videogameshop.model.*;
import github.com.dusansisarica.videogameshop.repository.CartItemRepository;
import github.com.dusansisarica.videogameshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.ViewportUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PriceService priceService;

    @Autowired
    private CartDtoMapper cartDtoMapper;
    @Autowired
    private CartItemDtoMapper cartItemDtoMapper;
    @Autowired
    private VideoGameDtoMapper videoGameDtoMapper;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private VideoGameService videoGameService;

    public CartDto addItem(CartItemDto cartItemDto, String email) {
        Cart userCart = userService.findByEmail(email).getCart();
        if (userCart == null) {
            userCart = new Cart();
            userCart.setUser(userService.findByEmail(email));
        }
        List<CartItem> items = userCart.getCartItems();
        if (items == null) {
            items = new ArrayList<>();
        }
        List<CartItem> userItems = replaceObject(items, cartItemDto, userCart);
        userCart.setCartItems(userItems);
        cartRepository.save(userCart);
        return CartDtoMapper.fromModeltoDTO(cartRepository.findByUserEmail(email));
    }

    public List<CartItem> setCart(List<CartItem> items, Cart cart) {
        List<CartItem> retVal = items;
        for (CartItem cartItem : retVal) {
            cartItem.setCart(cart);
        }
        return retVal;
    }

    public CartDto getCartDto(String email) {
        Cart userCart = cartRepository.findByUserEmail(email);
        if (userCart != null) {
            List<VideoGameDto> games = new ArrayList<>();
            PriceDiscountDto priceDiscountDto = new PriceDiscountDto();
            for (CartItem item : userCart.getCartItems()){
                for (int i=0; i<item.getQuantity(); i++){
                    games.add(videoGameDtoMapper.fromModeltoDTO(item.getGame()));
                }
            }
            priceDiscountDto.setGames(games);
            userCart.setTotalPrice(priceService.calculateTotalPrice(priceDiscountDto));
            CartDto cartDto = CartDtoMapper.fromModeltoDTO(cartRepository.findByUserEmail(email));
            cartDto.setCartItems(createCartItemsFromGames(priceDiscountDto.games));

            return cartDto;
        }
        return new CartDto();
    }

    public List<CartItemDto> createCartItemsFromGames(List<VideoGameDto> videoGames) {
        // Koristimo HashMap da mapiramo VideoGameDto na broj pojavljivanja
        Map<VideoGameDto, Integer> gameCountMap = new HashMap<>();

        // Prolazimo kroz listu video igara i prebrojavamo pojavljivanja
        for (VideoGameDto game : videoGames) {
//            if (gameCountMap.get(game) != null){
//                int count = gameCountMap.get(game);
//                gameCountMap.put(game, count++);
//            }
//            else {
//                gameCountMap.put(game, 1);
//            }
            gameCountMap.put(game, gameCountMap.getOrDefault(game, 0) + 1);
        }

        // Kreiramo listu CartItemDto
        List<CartItemDto> cartItems = new ArrayList<>();

        // Kreiramo CartItemDto za svaku igru iz mape
        for (Map.Entry<VideoGameDto, Integer> entry : gameCountMap.entrySet()) {
            VideoGameDto game = entry.getKey();
            int quantity = entry.getValue();
            CartItemDto cartItem = new CartItemDto(game, quantity);
            cartItems.add(cartItem);
        }

        return cartItems;
    }


    public Cart getCart(String email) {
        return cartRepository.findByUserEmail(email);
    }


    private List<CartItem> replaceObject(List<CartItem> items, CartItemDto item, Cart cart) {
        List<CartItem> retVal = items;
        int index = 0;
        boolean flag = false;
        for (CartItem ci : items) {
            if (ci.getGame().getID() == item.game.ID) {
                CartItem newItem = CartItemDtoMapper.fromDTOtoModel(item);
                newItem.setCart(cart);
                newItem.setStatus(CartItemStatus.IN_CART);
                newItem.setGame(ci.getGame());
                retVal.set(index, newItem);
                cartItemRepository.delete(ci);
                flag = true;
            }
            index++;
        }
        if (!flag) {
            CartItem newItem = CartItemDtoMapper.fromDTOtoModel(item);
            newItem.setCart(cart);
            newItem.setStatus(CartItemStatus.IN_CART);
            newItem.setGame(videoGameService.findByIdModel(newItem.getGame().getID()));
            retVal.add(newItem);
        }

        return retVal;
    }

    public CartDto removeItem(Integer id, String email) {
//        CartDto cart = getCartDto(email);
        Cart cart = getCart(email);
        List<CartItem> items = cartItemRepository.findAllByCartID(cart.getID());
        for (CartItem item : items) {
            if (item.getGame().getID() == id) {
                cart.getCartItems().remove(item);
                cartItemRepository.delete(item);
            }
        }
//        cart.setCartItems(items);
        cartRepository.save(cart);
        return getCartDto(email);
    }

    public void emptyUserCart(String email) {
        Cart userCart = getCart(email);
        userCart.getCartItems().clear();
        cartRepository.save(userCart);
    }
}
