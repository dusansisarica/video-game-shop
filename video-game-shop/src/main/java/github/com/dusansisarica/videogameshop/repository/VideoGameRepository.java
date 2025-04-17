package github.com.dusansisarica.videogameshop.repository;

import github.com.dusansisarica.videogameshop.enums.Genre;
import github.com.dusansisarica.videogameshop.enums.Platform;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface VideoGameRepository extends JpaRepository<VideoGame, Integer> {
    //    List<VideoGame> findAll();
    List<VideoGame> findAllByDeletedFalse();

    @Query("SELECT game FROM VideoGame game JOIN game.reviews AS review GROUP BY game ORDER BY AVG(review.rating) DESC")
    List<VideoGame> findByRating();

    List<VideoGame> findByGenresIn(Set<Genre> genres);

    List<VideoGame> findByPlatformsIn(Set<Platform> genres);

    List<VideoGame> findByGenresInAndPlatformsIn(Set<Genre> genres, Set<Platform> platforms);

    @Query("SELECT g FROM VideoGame g WHERE g.title LIKE %:query%")
    List<VideoGame> findByQuery(@Param("query") String query);

    List<VideoGame> findByTitleContainingIgnoreCase(String title);

//    @Query("SELECT DISTINCT g FROM VideoGame g " +
//            "JOIN g.genres genre " +
//            "JOIN g.platforms platform " +
//            "WHERE genre IN :genres " +
//            "AND platform IN :platforms " +
//            "AND LOWER(g.title) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<VideoGame> findByGenresInAndPlatformsInAndTitleContainingIgnoreCase(
            @Param("genres") Set<Genre> genres,
            @Param("platforms") Set<Platform> platforms,
            @Param("query") String query
    );

    List<VideoGame> findByGenresInAndTitleContainingIgnoreCase(
            @Param("genres") Set<Genre> genres,
            @Param("query") String query
    );

    List<VideoGame> findByPlatformsInAndTitleContainingIgnoreCase(
            @Param("platforms") Set<Platform> platforms,
            @Param("query") String query
    );

    @Query("SELECT g FROM VideoGame g JOIN g.platforms p JOIN g.genres ge " +
            "WHERE (:platforms IS NULL OR p IN :platforms) " +
            "AND (:genres IS NULL OR ge IN :genres)")
    List<VideoGame> findFilteredGames(@Param("platforms") List<Platform> platforms,
                                      @Param("genres") List<Genre> genres);




}
