package github.com.dusansisarica.videogameshop.service;

import com.itextpdf.text.DocumentException;
import github.com.dusansisarica.videogameshop.dto.OrderDto;
import github.com.dusansisarica.videogameshop.dto.PaginationDto;
import github.com.dusansisarica.videogameshop.dto.ReviewDto;
import github.com.dusansisarica.videogameshop.enums.CartItemStatus;
import github.com.dusansisarica.videogameshop.enums.OrderStatus;
import github.com.dusansisarica.videogameshop.mapper.CartItemDtoMapper;
import github.com.dusansisarica.videogameshop.mapper.OrderDtoMapper;
import github.com.dusansisarica.videogameshop.model.*;
import github.com.dusansisarica.videogameshop.repository.CartItemRepository;
import github.com.dusansisarica.videogameshop.repository.CentralInventoryRepository;
import github.com.dusansisarica.videogameshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PurchasedItemService purchasedItemService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemDtoMapper cartItemDtoMapper;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderDtoMapper orderDtoMapper;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ItemsForInventoryService itemsForInventoryService;
    @Autowired
    private CentralInventoryService centralInventoryService;
    @Autowired
    private CentralInventoryRepository centralInventoryRepository;
    @Autowired
    private PdfService pdfService;

    public OrderDto makeOrder(String email) {
        Order order = new Order();
        order.setUser(userService.findByEmail(email));
        order.setOrderDate(new Date());
        order.setOrderStatus(OrderStatus.PENDING);
        List<PurchasedItem> purchasedItems = new ArrayList<>();
        Cart userCart = cartService.getCart(email);

        for (CartItem cartItem : userCart.getCartItems()) {
            PurchasedItem purchasedItem = new PurchasedItem();
            purchasedItem.setOrder(order);
            purchasedItem.setCartItem(cartItem);
            cartItem.setStatus(CartItemStatus.PURCHASED);
            purchasedItems.add(purchasedItem);
            purchasedItemService.save(purchasedItem);
            cartItem.setCart(null);
            cartItemRepository.save(cartItem);
        }
        order.setPurchasedItems(purchasedItems);
        orderRepository.save(order);
        itemsForInventoryService.addItem(purchasedItems);
        return OrderDtoMapper.fromModeltoDTO(order);
    }

    public List<OrderDto> getUserOrders(String email, PaginationDto paginationDto) {
        return getAllWithPagination(orderDtoMapper.fromModeltoDTOList(orderRepository.findAllByUserID(userService.findByEmail(email).getID())), paginationDto);
    }

    public List<OrderDto> getAll(PaginationDto paginationDto) {
        List<CentralInventory> centralInventoryItems = centralInventoryRepository.findAll();
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> retOrder = new ArrayList<>();

        for (Order order : orders){
            boolean flag = true;
            for (PurchasedItem orderItem : order.getPurchasedItems()) {
                VideoGame gameInOrder = orderItem.getCartItem().getGame();
                int desiredQuantity = orderItem.getCartItem().getQuantity();

                // Check if the game is available in the central inventory
                CentralInventory matchingItem = centralInventoryItems.stream()
                        .filter(item -> item.getGame().getID() == gameInOrder.getID())
                        .findFirst()
                        .orElse(null);

                if (matchingItem != null && matchingItem.getQuantity() >= desiredQuantity) {

                } else {
                   flag = false;
                }
            }
            OrderDto dto = orderDtoMapper.fromModeltoDTO(order);
            dto.setCanBeShipped(flag);
            retOrder.add(dto);
        }
        return getAllWithPagination(retOrder, paginationDto);
    }

    public List<OrderDto> getAllWithPagination(List<OrderDto> orders, PaginationDto paginationDto) {
        int startIndex = (paginationDto.pageNumber - 1) * paginationDto.pageSize;
        int endIndex = startIndex + paginationDto.pageSize;
        if (endIndex > orders.size()) endIndex = orders.size();
        if (startIndex > orders.size()) orders = new ArrayList<>();
        return orders.subList(startIndex, endIndex);
    }


    public OrderDto changeStatus(String status, Integer orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            return null;
        }
        if (status.equals("PROCESSING")) {
            order.setOrderStatus(OrderStatus.PROCESSING);
        }
        if (status.equals("SHIPPED")) {
            order.setOrderStatus(OrderStatus.SHIPPED);
        }
        if (status.equals("DELIVERED")) {
            order.setOrderStatus(OrderStatus.DELIVERED);
        }
        orderRepository.save(order);
        return OrderDtoMapper.fromModeltoDTO(order);
    }

    public List<OrderDto> shipOrder(OrderDto orderDto, PaginationDto paginationDto) throws MessagingException, DocumentException, IOException {
        Order order = orderRepository.findById(orderDto.id).orElse(null);
        order.setOrderStatus(OrderStatus.SHIPPED);
        centralInventoryService.reduceCentralQuantity(order.getPurchasedItems());
        orderRepository.save(order);
        pdfService.generatePdf(order);
        return getAll(paginationDto);
    }



}
