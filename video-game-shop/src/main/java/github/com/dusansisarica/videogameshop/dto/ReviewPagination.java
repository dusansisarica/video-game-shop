package github.com.dusansisarica.videogameshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewPagination {
    public List<ReviewDto> reviews;
    public int totalNumber;
}
