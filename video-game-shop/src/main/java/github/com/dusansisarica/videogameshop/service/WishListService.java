package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.WishListDto;
import github.com.dusansisarica.videogameshop.mapper.WishListDtoMapper;
import github.com.dusansisarica.videogameshop.model.User;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import github.com.dusansisarica.videogameshop.model.WishList;
import github.com.dusansisarica.videogameshop.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {
    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private WishListDtoMapper wishListDtoMapper;
    @Autowired
    private UserService userService;

    private Integer getUserId(String email){
        return userService.findByEmail(email).getID();
    }
    public WishListDto save(WishListDto dto, String email){
        dto.userId = getUserId(email);
        wishListRepository.save(wishListDtoMapper.fromDTOtoModel(dto));
        return dto;
    }

    public List<WishListDto> getAllForUser(String email) {
        return wishListDtoMapper.fromModeltoDTOList(wishListRepository.findAllByUserIdAndDeletedFalse(getUserId(email)));
    }

    public List<WishListDto> deleteById(Integer id) {
        WishList wishList = wishListRepository.findById(id).orElse(null);
        if (wishList == null){
            return wishListDtoMapper.fromModeltoDTOList(wishListRepository.findAll());
        }
        wishList.setDeleted(true);
        wishListRepository.save(wishList);
        return wishListDtoMapper.fromModeltoDTOList(wishListRepository.findAllByUserIdAndDeletedFalse(wishList.getUserId()));

    }

}
