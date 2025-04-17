package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Integer> {
    List<Price> findByGameIDAndStartDateBeforeAndEndDateAfter(Integer videoGameId, LocalDate startDate, LocalDate endDate);

}
