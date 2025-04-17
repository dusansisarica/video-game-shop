package github.com.dusansisarica.videogameshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import github.com.dusansisarica.videogameshop.enums.Genre;
import github.com.dusansisarica.videogameshop.enums.Platform;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
public class VideoGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @Column
    private String title;
    @Column
    private String description;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Price> price = new ArrayList<>();
//    @ManyToOne
//    @JoinColumn(name = "discount_id")
//    private Discount activeDiscount;
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime release_date;
    @ElementCollection(targetClass = Platform.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<Platform> platforms;
    @ElementCollection(targetClass = Genre.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres;
    @Column
    private boolean deleted;
    @Column
    private String image;
    @OneToMany(mappedBy = "game")
    private List<Review> reviews;
    @OneToMany(mappedBy = "videoGame")
    private List<ItemsForInventory> inventoryItems;
    @OneToMany(mappedBy = "videoGame")
    private List<GameQuantity> gameQuantities;
//    @ManyToMany
//    @JoinTable(
//            name = "game_action",
//            joinColumns = @JoinColumn(name = "game_id"),
//            inverseJoinColumns = @JoinColumn(name = "action_id"))
//    private Set<Discount> discounts = new HashSet<>();


    public double calculateAverageRating() {
        List<Review> reviews = this.reviews; // Assuming "this.reviews" is the list of reviews in your VideoGame entity
        if (reviews == null || reviews.isEmpty()) {
            return 0.0; // Return 0 if there are no reviews
        }

        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getRating(); // Assuming "getRating()" returns the rating for each review
        }

        return sum / reviews.size();
    }


}
