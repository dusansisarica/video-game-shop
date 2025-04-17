package github.com.dusansisarica.videogameshop.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // User who wrote the review

    @ManyToOne
    @JoinColumn(name = "game_id")
    private VideoGame game; // Game being reviewed

    @Column(nullable = false)
    private int rating; // Rating given by the user (e.g., 1 to 5)

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column
    private boolean approved;

    @Column
    private boolean deleted;
    @Column
    private boolean denied;
}
