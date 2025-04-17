package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.*;
import github.com.dusansisarica.videogameshop.enums.Genre;
import github.com.dusansisarica.videogameshop.enums.Platform;
import github.com.dusansisarica.videogameshop.mapper.VideoGameDtoMapper;
import github.com.dusansisarica.videogameshop.model.Shop;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import github.com.dusansisarica.videogameshop.repository.GameQuantityRepository;
import github.com.dusansisarica.videogameshop.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoGameService {
    @Autowired
    private VideoGameRepository videoGameRepository;
    @Autowired
    private VideoGameDtoMapper videoGameDtoMapper;
    @Autowired
    private GameQuantityRepository gameQuantityRepository;
    @Autowired
    private PriceService priceService;

    public VideoGamePagination findAll(PaginationDto paginationDto) {
//        List<VideoGame> games;
//        games = videoGameRepository.findFilteredGames(paginationDto.platform, paginationDto.genres);
        List<VideoGame> allGames = videoGameRepository.findAll();

        allGames = allGames.stream()
                .filter(game -> paginationDto.platform == null || paginationDto.platform.isEmpty() || game.getPlatforms().containsAll(paginationDto.platform))
                .filter(game -> paginationDto.genres == null || paginationDto.genres.isEmpty() || game.getGenres().containsAll(paginationDto.genres))
                .filter(game -> paginationDto.searchQuery == null || paginationDto.searchQuery.isEmpty() ||
                        game.getTitle().toLowerCase().contains(paginationDto.searchQuery.toLowerCase()))
                .sorted((game1, game2) -> {
                    if (paginationDto.getSortBy() != null) {
                        Comparator<VideoGame> comparator;
                        switch (paginationDto.getSortBy()) {
                            case "title":
                                comparator = Comparator.comparing(VideoGame::getTitle);
                                break;
                            case "price":
                                comparator = Comparator.comparingDouble(game -> priceService.calculatePriceForGame(game).price);
                                break;
                            default:
                                comparator = Comparator.comparing(VideoGame::getTitle);
                                break;
                        }
                        if ("desc".equalsIgnoreCase(paginationDto.getSortDirection())) {
                            comparator = comparator.reversed();
                        }
                        return comparator.compare(game1, game2);
                    }
                    return 0;
                })
                .collect(Collectors.toList());
//        if (paginationDto.genres != null && paginationDto.platform == null && paginationDto.searchQuery.equals("")) {
//            games = videoGameRepository.findByGenresIn(paginationDto.getGenres());
//            System.out.println(games.size());
//        } else if (paginationDto.genres == null && paginationDto.platform != null && paginationDto.searchQuery.equals("")) {
//            games = videoGameRepository.findByPlatformsIn(paginationDto.platform);
//        }
//        else if (paginationDto.genres != null && paginationDto.platform != null && paginationDto.searchQuery.equals("")) {
//            games = videoGameRepository.findByGenresInAndPlatformsIn(paginationDto.genres, paginationDto.platform);
//        }
//        else if (paginationDto.genres != null && paginationDto.platform == null && !paginationDto.searchQuery.equals("")) {
//            games = videoGameRepository.findByGenresInAndTitleContainingIgnoreCase(paginationDto.getGenres(), paginationDto.searchQuery);
//        }
//        else if (paginationDto.genres == null && paginationDto.platform != null && !paginationDto.searchQuery.equals("")) {
//            games = videoGameRepository.findByPlatformsInAndTitleContainingIgnoreCase(paginationDto.platform, paginationDto.searchQuery);
//        }
//        else if (paginationDto.genres != null && paginationDto.platform != null && !paginationDto.searchQuery.equals("")){
//            games = videoGameRepository.findByGenresInAndPlatformsInAndTitleContainingIgnoreCase(paginationDto.getGenres(), paginationDto.platform, paginationDto.searchQuery);
//        }
//        else if (paginationDto.genres == null && paginationDto.platform == null && !paginationDto.searchQuery.equals("")){
//            games = videoGameRepository.findByTitleContainingIgnoreCase(paginationDto.searchQuery);
//        }
//        else {
//            games = videoGameRepository.findAllByDeletedFalse();
//        }

        return getAllWithPagination(VideoGameDtoMapper.fromModeltoDTOList(allGames), paginationDto);
    }

    public VideoGamePagination getAllWithPagination(List<VideoGameDto> games, PaginationDto paginationDto) {
        int startIndex = (paginationDto.pageNumber - 1) * paginationDto.pageSize;
        int endIndex = startIndex + paginationDto.pageSize;
        if (endIndex > games.size()) endIndex = games.size();
        if (startIndex > games.size()) games = new ArrayList<>();
        return new VideoGamePagination(games.subList(startIndex, endIndex), games.size());
    }

    private VideoGameDto convertToDto(VideoGame game) {
        // Logika za konverziju VideoGame u VideoGameDto
        return videoGameDtoMapper.fromModeltoDTO(game);
    }


    public VideoGameDto save(VideoGameDto dto) {
        VideoGame videoGame = VideoGameDtoMapper.fromDTOtoModel(dto);
        videoGame.setDeleted(false);
        videoGameRepository.save(videoGame);
        return dto;
    }

    public VideoGame saveModel(VideoGame videoGame) {
        videoGame.setDeleted(false);
        videoGameRepository.save(videoGame);
        return videoGame;
    }



    public VideoGameDto update(VideoGameDto videoGameDto) {
        VideoGame updatedVideoGame = videoGameRepository.findById(videoGameDto.ID).orElse(null);
        if (updatedVideoGame == null) {
            return null;
        }
        updatedVideoGame = VideoGameDtoMapper.fromDTOtoModel(videoGameDto);
        videoGameRepository.save(updatedVideoGame);
        return videoGameDto;
    }


    public List<VideoGameDto> deleteById(Integer id) {
        VideoGame videoGame = videoGameRepository.findById(id).orElse(null);
        if (videoGame == null) {
            return VideoGameDtoMapper.fromModeltoDTOList(videoGameRepository.findAll());
        }
        videoGame.setDeleted(true);
        videoGameRepository.save(videoGame);
        return VideoGameDtoMapper.fromModeltoDTOList(videoGameRepository.findAllByDeletedFalse());
    }

    public VideoGameDto findById(Integer id) {
        return VideoGameDtoMapper.fromModeltoDTO(videoGameRepository.findById(id).orElse(null));
    }

    public VideoGame findByIdModel(Integer id) {
        return videoGameRepository.findById(id).orElse(null);
    }

    public int getTotalQuantity(int gameId) {
        return gameQuantityRepository.getTotalQuantityByGameID(gameId);
    }

    public List<VideoGameDto> findFeatured(PaginationDto paginationDto) {
        int startIndex = (paginationDto.pageNumber - 1) * paginationDto.pageSize;
        int endIndex = startIndex + paginationDto.pageSize;
        List<VideoGame> games = videoGameRepository.findByRating();
        if (endIndex > games.size()) endIndex = games.size();
        if (startIndex > games.size()) games = new ArrayList<>();
        return VideoGameDtoMapper.fromModeltoDTOList(games.subList(startIndex, endIndex));
    }

    public List<String> getGenreNames() {
        List<String> genreList = new ArrayList<>();

        for (Genre genre : Genre.values()) {
            genreList.add(genre.name());
        }
        return genreList;
    }

    public List<String> getPlatformNames() {
        List<String> platformList = new ArrayList<>();

        for (Platform platform : Platform.values()) {
            platformList.add(platform.name());
        }
        return platformList;
    }

}
