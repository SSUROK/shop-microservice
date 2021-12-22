package ru.gb.shopmicroservice.dao.mapper;

import org.mapstruct.Mapper;
import ru.gb.shopmicroservice.entity.Cart;
import ru.gb.shopmicroservice.model.CartDto;

@Mapper
public interface CartMapper {

    CartDto toCartDto(Cart cart);

    Cart toCart(CartDto cartDto);
}
