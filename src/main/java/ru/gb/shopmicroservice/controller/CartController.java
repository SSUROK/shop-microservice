package ru.gb.shopmicroservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.shopmicroservice.dao.CartDao;
import ru.gb.shopmicroservice.dao.ManufacturerDao;
import ru.gb.shopmicroservice.dao.mapper.CartMapper;
import ru.gb.shopmicroservice.dao.mapper.ProductMapper;
import ru.gb.shopmicroservice.model.CartDto;
import ru.gb.shopmicroservice.model.ManufacturerDto;
import ru.gb.shopmicroservice.model.ProductDto;
import ru.gb.shopmicroservice.service.CartService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping(path = "/add/{cartId}", produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    public ResponseEntity<?> add(@RequestBody ProductDto productDto, @PathVariable(value = "cartId", required = false) Long cartId) {
        if(cartId == null){
            cartId = cartService.createCart();
        }
        cartService.add(productDto, cartId);
        return new ResponseEntity<>(cartService.getCartById(cartId), HttpStatus.OK);
//        CartDto cart = cartService.getCartById(cartId);
//        Set<ProductDto> productDtos = cart.getProductList();
//        productDtos.add(productDto);
//
//        CartDto savedCart = CartDto.builder()
//                .id(cartId)
//                .productList(productDtos)
//                .build();
//        cartService.
    }

    @GetMapping(path = "/{cartId}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> getCart(@PathVariable(value = "cartId") Long cartId) {
        CartDto cartDto;
        if (cartId != null){
            cartDto = cartService.getCartById(cartId);
            if (cartDto != null){
                return new ResponseEntity<>(cartService.getCartById(cartId), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{cartId}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCart(@PathVariable(value = "cartId") Long cartId) {
        cartService.removeCart(cartId);
    }

    @DeleteMapping(path = "/product/{cartId}", produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProduct(@RequestBody ProductDto productDto, @PathVariable(value = "cartId") Long cartId) {
        cartService.deleteByProductId(productDto, cartId);
    }
}
