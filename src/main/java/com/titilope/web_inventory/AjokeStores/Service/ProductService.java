package com.titilope.web_inventory.AjokeStores.Service;

import com.titilope.web_inventory.AjokeStores.Entity.Product;
import com.titilope.web_inventory.AjokeStores.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public List<Product> findLowStockedProducts(){
        return productRepository.findLowStockedProducts();
    }

    public List<Product> findProductByName(String name){
        return productRepository.findByNameContainsAllIgnoreCase(name);
    }

    public Page<Product> findPaginated(Pageable pageable){
        List <Product> products= productRepository.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage*pageSize;
        List<Product> list;

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        Page<Product> bookPage = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), products.size());

        return bookPage;

    }
}
