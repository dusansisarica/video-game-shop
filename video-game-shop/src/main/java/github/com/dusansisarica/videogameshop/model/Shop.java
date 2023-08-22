package github.com.dusansisarica.videogameshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

//    @OneToMany(mappedBy="shop", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<GameQuantity> items;

    public Shop(String name, Address address, List<GameQuantity> items) {
        this.name = name;
        this.address = address;
//        this.items = items;
    }

    public Shop() {
    }
}
