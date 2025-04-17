package github.com.dusansisarica.videogameshop.dto;

import github.com.dusansisarica.videogameshop.enums.Genre;
import github.com.dusansisarica.videogameshop.enums.Platform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class PaginationDto {
    public int pageNumber;
    public int pageSize;
    public List<Platform> platform;
    public List<Genre> genres;
    public String searchQuery;
    public String sortBy;
    public String sortDirection;

    public PaginationDto(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
