package ru.gb.shopmicroservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.shopmicroservice.entity.Cart;

public interface CartDao extends JpaRepository<Cart, Long> {

}
