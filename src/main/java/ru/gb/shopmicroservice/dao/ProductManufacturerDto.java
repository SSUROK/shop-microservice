package ru.gb.shopmicroservice.dao;

import lombok.*;
import ru.gb.shopmicroservice.model.ManufacturerDto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductManufacturerDto {
    @NotBlank
    private String title;
    @NotNull
    @Digits(integer = 5, fraction = 2, message = "мое кастомное сообщение")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal cost;
    @PastOrPresent
    private LocalDate manufactureDate;

    private ManufacturerDto manufacturerDto;
}
