package github.com.dusansisarica.videogameshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String country;
    @Column
    private Double longitude;
    @Column
    private Double latitude;

    public Address() {
    }

    public Address(String address, String city, String country, Double longitude, Double latitude) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
