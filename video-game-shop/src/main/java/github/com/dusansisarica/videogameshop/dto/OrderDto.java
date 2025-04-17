package github.com.dusansisarica.videogameshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import github.com.dusansisarica.videogameshop.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    public Integer id;
    public List<PurchasedItemDto> purchasedItems;
    @JsonFormat(pattern = "dd/MM/yyyy")
    public Date orderDate;
    public OrderStatus orderStatus;
    public UserDetailDto user;
    public boolean canBeShipped;

    public OrderDto(Integer id, List<PurchasedItemDto> purchasedItems, Date orderDate, OrderStatus orderStatus, UserDetailDto user) {
        this.id = id;
        this.purchasedItems = purchasedItems;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.user = user;
    }
}
