package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.CentralInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentralInventoryRepository extends JpaRepository<CentralInventory, Integer> {

    CentralInventory findByGameID(Integer gameId);
}
