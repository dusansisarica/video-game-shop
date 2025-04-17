package github.com.dusansisarica.videogameshop.mapper;

import github.com.dusansisarica.videogameshop.dto.OrderDto;
import github.com.dusansisarica.videogameshop.dto.ReviewDto;
import github.com.dusansisarica.videogameshop.model.Order;
import github.com.dusansisarica.videogameshop.model.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewDtoMapper {
    private static ModelMapper modelMapper;

    @Autowired
    public ReviewDtoMapper(ModelMapper modelMapper) {
        ReviewDtoMapper.modelMapper = modelMapper;
    }

    public static Review fromDTOtoModel(ReviewDto dto) {
        return new Review(
                dto.id,
                UserDtoMapper.fromDTOtoModel(dto.user),
                VideoGameDtoMapper.fromDTOtoModel(dto.game),
                dto.rating,
                dto.comment,
                dto.approved,
                dto.deleted,
                dto.denied
        );
    }

    public static ReviewDto fromModeltoDTO(Review model) {
        return new ReviewDto(
                model.getId(),
                UserDtoMapper.fromModeltoDTO(model.getUser()),
                VideoGameDtoMapper.fromModeltoDTO(model.getGame()),
                model.getRating(),
                model.getComment(),
                model.isApproved(),
                model.isDeleted(),
                model.isDenied());
    }

    public static List<ReviewDto> fromModeltoDTOList(List<Review> modelList) {
        List<ReviewDto> dto = new ArrayList<>();
        for (Review item : modelList) {
            ReviewDto reviewDto = fromModeltoDTO(item);
            dto.add(reviewDto);
        }
        return dto;
    }

}
