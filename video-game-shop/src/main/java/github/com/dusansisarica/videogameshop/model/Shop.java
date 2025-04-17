package github.com.dusansisarica.videogameshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

    @Column
    private boolean deleted;

    @OneToMany(mappedBy = "shop")
    private List<ItemsForInventory> inventoryItems;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<User> workers = new ArrayList<>();

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
