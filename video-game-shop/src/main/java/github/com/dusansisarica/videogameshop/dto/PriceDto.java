package github.com.dusansisarica.videogameshop.dto;
import github.com.dusansisarica.videogameshop.model.Discount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {
    public Integer id;
    public double price;
    public LocalDate startDate;
    public LocalDate endDate;
    public DiscountDto action;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceDto)) return false;
        PriceDto priceDto = (PriceDto) o;

        // Uporedi cene zaokružene na dve decimale
        return Double.compare(Math.round(priceDto.getPrice() * 100) / 100.0,
                Math.round(getPrice() * 100) / 100.0) == 0 &&
                Objects.equals(getId(), priceDto.getId()) &&
                Objects.equals(getStartDate(), priceDto.getStartDate()) &&
                Objects.equals(getEndDate(), priceDto.getEndDate()) &&
                Objects.equals(getAction(), priceDto.getAction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), Math.round(getPrice() * 100) / 100.0, getStartDate(), getEndDate(), getAction());
    }
}
