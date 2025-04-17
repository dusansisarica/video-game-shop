package github.com.dusansisarica.videogameshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import github.com.dusansisarica.videogameshop.enums.Genre;
import github.com.dusansisarica.videogameshop.enums.Platform;
import github.com.dusansisarica.videogameshop.model.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoGameDto {
    public Integer ID;
    public String title;
    public String description;
    public PriceDto price;
    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="EEE MMM dd")
    public LocalDateTime release_date;
    public Set<Platform> platforms;
    public Set<Genre> genres;
    public String image;
    public double rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoGameDto that = (VideoGameDto) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(price.price, that.price.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price.price);
    }
}
