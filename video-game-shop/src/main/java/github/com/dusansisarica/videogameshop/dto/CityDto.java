package github.com.dusansisarica.videogameshop.dto;

import github.com.dusansisarica.videogameshop.mapper.AddressDtoMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {
    public Integer id;
    public CountryDto countryDto;
    public String name;


}
