package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.dto.WishListDto;
import github.com.dusansisarica.videogameshop.dto.WishListProductsDto;
import github.com.dusansisarica.videogameshop.mapper.WishListDtoMapper;
import github.com.dusansisarica.videogameshop.model.User;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import github.com.dusansisarica.videogameshop.model.WishList;
import github.com.dusansisarica.videogameshop.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {
    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private WishListDtoMapper wishListDtoMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private VideoGameService videoGameService;

    private Integer getUserId(String email) {
        return userService.findByEmail(email).getID();
    }

    public WishListDto save(WishListDto dto, String email) {
        dto.userId = getUserId(email);
        wishListRepository.save(WishListDtoMapper.fromDTOtoModel(dto));
        return dto;
    }

    public List<WishListProductsDto> getAllForUser(String email) {
        List<WishList> ids = wishListRepository.findAllByUserIdAndDeletedFalse(getUserId(email));
        List<WishListProductsDto> products = new ArrayList<>();
        for (WishList id : ids) {
            WishListProductsDto wishListProduct = new WishListProductsDto();
            wishListProduct.setId(id.getID());
            wishListProduct.setProduct(videoGameService.findById(id.getProductId()));
            products.add(wishListProduct);
        }
        return products;
    }

    public List<WishListDto> deleteById(Integer id) {
        WishList wishList = wishListRepository.findById(id).orElse(null);
        if (wishList == null) {
            return WishListDtoMapper.fromModeltoDTOList(wishListRepository.findAll());
        }
        wishList.setDeleted(true);
        wishListRepository.save(wishList);
        return WishListDtoMapper.fromModeltoDTOList(wishListRepository.findAllByUserIdAndDeletedFalse(wishList.getUserId()));

    }

}
