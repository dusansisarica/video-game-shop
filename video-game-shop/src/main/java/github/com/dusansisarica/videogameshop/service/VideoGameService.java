package github.com.dusansisarica.videogameshop.service;

import github.com.dusansisarica.videogameshop.dto.VideoGameDto;
import github.com.dusansisarica.videogameshop.mapper.VideoGameDtoMapper;
import github.com.dusansisarica.videogameshop.model.VideoGame;
import github.com.dusansisarica.videogameshop.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoGameService {
    @Autowired
    private VideoGameRepository videoGameRepository;
    @Autowired
    private VideoGameDtoMapper videoGameDtoMapper;

    public List<VideoGameDto> findAll(){
        return videoGameDtoMapper.fromModeltoDTOList(videoGameRepository.findAllByDeletedFalse());
    }

    public VideoGameDto save(VideoGameDto dto){
        VideoGame videoGame = videoGameDtoMapper.fromDTOtoModel(dto);
        videoGame.setDeleted(false);
        videoGameRepository.save(videoGame);
        return dto;
    }


    public VideoGameDto update(VideoGameDto videoGameDto) {
        VideoGame updatedVideoGame = videoGameRepository.findById(videoGameDto.ID).orElse(null);
        if (updatedVideoGame == null) {
            return null;
        }
        updatedVideoGame = videoGameDtoMapper.fromDTOtoModel(videoGameDto);
        videoGameRepository.save(updatedVideoGame);
        return videoGameDto;
    }

    public List<VideoGameDto> deleteById(Integer id) {
        VideoGame videoGame = videoGameRepository.findById(id).orElse(null);
        if (videoGame == null){
            return videoGameDtoMapper.fromModeltoDTOList(videoGameRepository.findAll());
        }
        videoGame.setDeleted(true);
        videoGameRepository.save(videoGame);
        return videoGameDtoMapper.fromModeltoDTOList(videoGameRepository.findAllByDeletedFalse());
    }

    public VideoGameDto findById(Integer id) {
        return videoGameDtoMapper.fromModeltoDTO(videoGameRepository.findById(id).orElse(null));
    }
}
