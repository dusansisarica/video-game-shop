package github.com.dusansisarica.videogameshop.dto;

import github.com.dusansisarica.videogameshop.enums.ActionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionDto {
    public Integer id;
    public String name;
    public ActionType actionType;
    public double discountValue;

    public LocalDate startDate;
    public LocalDate endDate;
}
