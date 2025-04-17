package github.com.dusansisarica.videogameshop.repository;


import github.com.dusansisarica.videogameshop.model.Role;
import github.com.dusansisarica.videogameshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findByVerificationCode(String code);

    List<User> findAllByDeletedFalse();

}
