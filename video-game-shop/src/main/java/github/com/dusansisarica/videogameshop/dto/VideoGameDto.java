package github.com.dusansisarica.videogameshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import github.com.dusansisarica.videogameshop.enums.Genre;
import github.com.dusansisarica.videogameshop.enums.Platform;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class VideoGameDto {
    public Integer ID;
    public String title;
    public String description;
    public float price;
    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="EEE MMM dd")
    public LocalDateTime release_date;
    public Set<Platform> platforms;
    public Set<Genre> genres;
    public String image;
}
