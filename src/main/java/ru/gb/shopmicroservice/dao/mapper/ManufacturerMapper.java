package ru.gb.shopmicroservice.dao.mapper;

import org.mapstruct.Mapper;
import ru.gb.shopmicroservice.model.ManufacturerDto;
import ru.gb.shopmicroservice.entity.Manufacturer;

@Mapper
public interface ManufacturerMapper {
    ManufacturerDto toManufacturerDto(Manufacturer manufacturer);

    Manufacturer toManufacturer(ManufacturerDto manufacturerDto);
}
