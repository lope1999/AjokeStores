package com.titilope.web_inventory.AjokeStores.Service;

import com.titilope.web_inventory.AjokeStores.Entity.ProductSale;
import com.titilope.web_inventory.AjokeStores.Repository.ProductSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductSaleService implements CrudService<ProductSale, Integer>{

    @Autowired
    ProductSaleRepository productSaleRepository;

    @Override
    public List<ProductSale> findAll() {
        return productSaleRepository.findAll();
    }

    @Override
    public ProductSale findById(Integer id) {

        Optional<ProductSale> result =  productSaleRepository.findById(id);
        ProductSale productSale = null;
        if (result.isPresent()) {
            productSale = result.get();
        } else {
            throw new RuntimeException("Did not find product sale with id: " + id);
        }
        return productSale;
    }

    @Override
    public ProductSale save(ProductSale object) {

        return productSaleRepository.save(object);
    }

    @Override
    public void delete(ProductSale object) {
        productSaleRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        productSaleRepository.deleteById(id);
    }


   /* public Set<ProductSale> findProductSaleBy_Product(){
        return productSaleRepository.findProductSaleBy_Product();
    }*/
}
