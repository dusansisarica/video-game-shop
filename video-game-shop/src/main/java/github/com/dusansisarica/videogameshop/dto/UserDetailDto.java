package github.com.dusansisarica.videogameshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailDto {
    public String email;
    public String name;
    public String surname;
    public AddressDto address;
    public String jwt;
}
