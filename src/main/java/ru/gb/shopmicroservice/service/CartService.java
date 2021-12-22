package ru.gb.shopmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.shopmicroservice.dao.CartDao;
import ru.gb.shopmicroservice.dao.ManufacturerDao;
import ru.gb.shopmicroservice.dao.mapper.CartMapper;
import ru.gb.shopmicroservice.dao.mapper.ProductMapper;
import ru.gb.shopmicroservice.entity.Cart;
import ru.gb.shopmicroservice.entity.Product;
import ru.gb.shopmicroservice.model.CartDto;
import ru.gb.shopmicroservice.model.ProductDto;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    //    private final HashMap<Product, Integer> cart = new HashMap<>();
    private final CartDao cartDao;
    private final Cart cart;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;
    private final ManufacturerDao manufacturerDao;


    public Cart add(ProductDto productDto, Long cartId) {
        Optional<Cart> cartOptional = cartDao.findById(cartId);
        if (cartOptional.isPresent()){
            Cart cartFromDB = cartOptional.get();
            cartFromDB.addProduct(productMapper.toProduct(productDto, manufacturerDao));
            return cartDao.save(cartFromDB);
        }
        return null;
    }

    public void deleteByProductId(ProductDto productDto, Long cartId) {
        Optional<Cart> cartOptional = cartDao.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cartFromDB = cartOptional.get();
            cartFromDB.deleteProduct(productMapper.toProduct(productDto, manufacturerDao));
            cartDao.save(cartFromDB);
        }
    }

    public Set<Product> findAll() {
        if (cart.getId() == null){
            createCart();
        }
        Optional<Cart> cartOptional = cartDao.findById(cart.getId());
        Cart cartFromDB = cartOptional.get();
        return cartFromDB.getProducts();
    }

    public Long createCart(){
        Cart cart = new Cart();
        cartDao.save(cart);
        return  cart.getId();
    }

    public CartDto getCartById(Long id){
        Optional<Cart> cartOptional = cartDao.findById(id);
        if (cartOptional.isPresent()){
            return cartMapper.toCartDto(cartOptional.get());
        }
        return null;
    }

    public void removeCart(Long id){
        Optional<Cart> cartOptional = cartDao.findById(id);
        if (cartOptional.isPresent()){
            cartDao.deleteById(id);
        }
    }


//    @Override
//    public void accept(Product product) {
//        add(product);
//    }
}
