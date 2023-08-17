package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoGameRepository extends JpaRepository<VideoGame, Integer> {
//    List<VideoGame> findAll();
    List<VideoGame> findAllByDeletedFalse();
}
