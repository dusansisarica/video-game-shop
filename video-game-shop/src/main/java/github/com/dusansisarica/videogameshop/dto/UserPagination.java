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
public class UserPagination {
    public List<UserDetailDto> users;
    public int totalSize;
}
