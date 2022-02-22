package com.titilope.web_inventory.AjokeStores.Service;

import com.titilope.web_inventory.AjokeStores.Entity.PackagingUnit;
import com.titilope.web_inventory.AjokeStores.Repository.PackagingUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackagingUnitService implements CrudService<PackagingUnit, Integer> {

    @Autowired
    PackagingUnitRepository packagingUnitRepository;

    @Override
    public List<PackagingUnit> findAll() {
        return packagingUnitRepository.findAll();
    }

    @Override
    public PackagingUnit findById(Integer id) {

        Optional<PackagingUnit> result =  packagingUnitRepository.findById(id);
        PackagingUnit packagingUnit = null;
        if (result.isPresent()) {
            packagingUnit = result.get();
        } else {
            throw new RuntimeException("Did not find packaging unit with id: " + id);
        }
        return packagingUnit;
    }

    @Override
    public PackagingUnit save(PackagingUnit object) {

        return packagingUnitRepository.save(object);
    }

    @Override
    public void delete(PackagingUnit object) {
        packagingUnitRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        packagingUnitRepository.deleteById(id);
    }
}
