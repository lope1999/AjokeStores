package com.titilope.web_inventory.AjokeStores.Service;

import com.titilope.web_inventory.AjokeStores.Entity.Role;
import com.titilope.web_inventory.AjokeStores.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements CrudService<Role, Integer>{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Integer id) {

        Optional<Role> result =  roleRepository.findById(id);
        Role role = null;
        if (result.isPresent()) {
            role = result.get();
        } else {
            throw new RuntimeException("Did not find role with id: " + id);
        }
        return role;
    }


    @Override
    public Role save(Role object) {
        return roleRepository.save(object);
    }

    @Override
    public void delete(Role object) {
    roleRepository.delete(object);
    }

    @Override
    public void deleteById(Integer integer) {
    roleRepository.deleteById(integer);
    }
}
