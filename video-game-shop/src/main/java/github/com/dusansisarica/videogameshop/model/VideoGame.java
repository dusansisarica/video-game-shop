package github.com.dusansisarica.videogameshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import github.com.dusansisarica.videogameshop.enums.Genre;
import github.com.dusansisarica.videogameshop.enums.Platform;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

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
    @Column
    private float price;
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
}
