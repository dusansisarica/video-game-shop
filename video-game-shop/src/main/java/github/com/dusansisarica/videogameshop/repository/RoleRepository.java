package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
