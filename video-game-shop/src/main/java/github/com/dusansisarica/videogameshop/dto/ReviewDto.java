package github.com.dusansisarica.videogameshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    public Integer id;
    public UserDto user;
    public VideoGameDto game;
    public int rating;
    public String comment;
    public boolean approved;
    public boolean deleted;
    public boolean denied;
}
