package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
    City findByCityNameAndCountryName(String name, String countryName);
}
