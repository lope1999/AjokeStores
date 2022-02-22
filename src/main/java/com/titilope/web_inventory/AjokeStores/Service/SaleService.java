package com.titilope.web_inventory.AjokeStores.Service;

import com.titilope.web_inventory.AjokeStores.Entity.Sale;
import com.titilope.web_inventory.AjokeStores.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService implements CrudService<Sale, Integer>{

    @Autowired
    SaleRepository saleRepository;

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public Sale findById(Integer id) {

        Optional<Sale> result =  saleRepository.findById(id);
        Sale sale = null;
        if (result.isPresent()) {
            sale = result.get();
        } else {
            throw new RuntimeException("Did not find sale with id: " + id);
        }
        return sale;
    }

    @Override
    public Sale save(Sale object) {

        return saleRepository.save(object);
    }

    @Override
    public void delete(Sale object) {
        saleRepository.delete(object);

    }

    @Override
    public void deleteById(Integer id) {
        saleRepository.deleteById(id);
    }
}