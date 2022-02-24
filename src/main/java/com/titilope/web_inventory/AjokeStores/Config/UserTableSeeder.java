package com.titilope.web_inventory.AjokeStores.Config;


import com.titilope.web_inventory.AjokeStores.Entity.Role;
import com.titilope.web_inventory.AjokeStores.Entity.User;
import com.titilope.web_inventory.AjokeStores.Repository.RoleRepository;
import com.titilope.web_inventory.AjokeStores.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserTableSeeder {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if(userRepository.count() < 1) {
            passwordEncoder = new BCryptPasswordEncoder(10);
            User user = new User();
            user.setUsername("ADMIN");
            user.setPassword(passwordEncoder.encode("ADMIN"));
            user.setFirstName("Titilope");
            user.setLastName("Alaga");
            user.setAddress("Union Systems Limited");
            user.setPhoneNumber("224806865957");
            user.setEmail("titilope@usl.com");


            // create Roles
            Role role1 = new Role("MANAGER");
            Role role2 = new Role("SALES_OFFICER");
            roleRepository.save(role1);
            roleRepository.save(role2);

            user.addRole(role1);
            user.addRole(role2);

            User savedUser = userRepository.save(user);

        }
    }
}
