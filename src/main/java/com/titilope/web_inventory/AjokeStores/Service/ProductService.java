package com.titilope.web_inventory.AjokeStores.Service;

import com.titilope.web_inventory.AjokeStores.Entity.Product;
import com.titilope.web_inventory.AjokeStores.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements CrudService<Product, Integer>{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAllByOrderByDescriptionAsc();
    }

    @Override
    public Product findById(Integer id) {

        Optional<Product> result =  productRepository.findById(id);
        Product product = null;
        if (result.isPresent()) {
            product = result.get();
        } else {
            throw new RuntimeException("Did not find product with id: " + id);
        }
        return product;
    }

    @Override
    public Product save(Product object) {

        return productRepository.save(object);
    }

    @Override
    public void delete(Product object) {
        productRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
