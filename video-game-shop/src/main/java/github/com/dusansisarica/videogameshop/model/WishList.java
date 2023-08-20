package github.com.dusansisarica.videogameshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column
    private Integer productId;

    @Column
    private Integer userId;
}
