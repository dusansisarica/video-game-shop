package github.com.dusansisarica.videogameshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;
//    private boolean discountedPrice = false;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private VideoGame game;

    @ManyToOne
    @JoinColumn(name = "action_id")
    private Discount discount;

    public Price(Integer id, double price, LocalDate startDate, LocalDate endDate, Discount discount) {
        this.id = id;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
    }

    public Price(double price, LocalDate startDate, LocalDate endDate, VideoGame game) {
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
//        this.discountedPrice = discountedPrice;
        this.game = game;
    }
}
