package ru.gb.shopmicroservice.dao;

import org.springframework.data.repository.CrudRepository;
import ru.gb.shopmicroservice.entity.Manufacturer;

import java.util.Optional;

public interface ManufacturerDao extends CrudRepository<Manufacturer, Long> {

    Optional<Manufacturer> findByName(String manufacturer);
}
