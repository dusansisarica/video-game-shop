package github.com.dusansisarica.videogameshop.model;

import github.com.dusansisarica.videogameshop.enums.ActionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
//    private String description;
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
//    @Transient
//    private DiscountFactory discountFactory;
//
//    @Autowired
//    public Discount(DiscountFactory discountFactory) {
//        this.discountFactory = discountFactory;
//    }
//    @ManyToMany(mappedBy = "discounts")
//    private Set<VideoGame> games = new HashSet<>();
//    @OneToMany(mappedBy = "activeDiscount", cascade = CascadeType.ALL)
//    private List<VideoGame> videoGames = new ArrayList<>();


    @Column(nullable = false)
    private double discountValue;

    private LocalDate startDate;
    private LocalDate endDate;

    public Discount(Integer id, String name, ActionType actionType, double discountValue, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.actionType = actionType;
        //this.games = games;
        this.discountValue = discountValue;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isDiscountActive() {
        LocalDate currentDateTime = LocalDate.now(); // Trenutni datum i vreme

        // Proveravamo da li je trenutni datum i vreme između startDate i endDate
        return (currentDateTime.isEqual(startDate) || currentDateTime.isAfter(startDate)) &&
                (currentDateTime.isEqual(endDate) || currentDateTime.isBefore(endDate));
    }




//    public DiscountStrategy getDiscountStrategy() {
//        return discountFactory.getDiscountStrategy(this.actionType);
//    }

//    public DiscountStrategy getDiscountStrategy() {
//        switch (this.actionType) {
////            case PERCENTAGE_DISCOUNT:
////                return new PercentageDiscount(this.discountValue);
//            case BUY_ONE_GET_ONE_FREE:
////                return new BuyOneGetOneFree();
////            case QUANTITY_DISCOUNT:
////                return new QuantityDiscount();
//            default:
//                throw new IllegalArgumentException("Unknown discount type");
//        }
//    }

}
