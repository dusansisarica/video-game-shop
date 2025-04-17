package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.OrderDto;
import github.com.dusansisarica.videogameshop.dto.PurchasedItemDto;
import github.com.dusansisarica.videogameshop.model.Order;
import github.com.dusansisarica.videogameshop.model.PurchasedItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDtoMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public OrderDtoMapper(ModelMapper modelMapper) {
        OrderDtoMapper.modelMapper = modelMapper;
    }

//    public static Order fromDTOtoModel(OrderDto dto) {
//        return new Order(
//                dto.getId(),
//                PurchasedItemDtoMapper.fromDTOtoModelList(dto.purchasedItems),
//                dto.getOrderDate(),
//                dto.getOrderStatus(),
//                UserDtoMapper.fromDto(model.getUser()));
//
//    }

    public static OrderDto fromModeltoDTO(Order model) {
        return new OrderDto(model.getId(), PurchasedItemDtoMapper.fromModeltoDTOList(model.getPurchasedItems()), model.getOrderDate(), model.getOrderStatus(), UserDtoMapper.fromModeltoDTODetail(model.getUser()));
    }

    public static List<OrderDto> fromModeltoDTOList(List<Order> modelList) {
        List<OrderDto> dto = new ArrayList<>();
        for (Order item : modelList) {
            OrderDto purchasedItemDto = fromModeltoDTO(item);
            dto.add(purchasedItemDto);
        }
        return dto;
    }
}
