package github.com.dusansisarica.videogameshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CentralInventoryDto {
    public Integer id;
    public VideoGameDto videoGame;
    public Integer quantity;
}
