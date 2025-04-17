package github.com.dusansisarica.videogameshop.dto;

import github.com.dusansisarica.videogameshop.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto {
    public int ID;
    public String email;
    public String name;
    public String surname;
    public AddressDto address;
    public String jwt;
    public Role role;

    public UserDetailDto(int ID, String email, String name, String surname, AddressDto address) {
        this.ID = ID;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.address = address;
    }
}
