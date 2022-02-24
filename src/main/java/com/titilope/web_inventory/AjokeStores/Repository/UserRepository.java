package com.titilope.web_inventory.AjokeStores.Repository;

import com.titilope.web_inventory.AjokeStores.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);
}
