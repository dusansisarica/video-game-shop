package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepositroy extends JpaRepository<Country, Integer> {
    Country findByName(String name);
}
