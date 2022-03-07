package com.titilope.web_inventory.AjokeStores.Service;

import com.titilope.web_inventory.AjokeStores.Entity.Role;
import com.titilope.web_inventory.AjokeStores.Entity.User;
import com.titilope.web_inventory.AjokeStores.Repository.RoleRepository;
import com.titilope.web_inventory.AjokeStores.Repository.UserRepository;
import com.titilope.web_inventory.AjokeStores.Exception.UserAlreadyExistException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import validation.UserValidation;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements CrudService<User, Integer>{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {

        Optional<User> result =  userRepository.findById(id);
        User user = null;
        if (result.isPresent()) {
            user = result.get();
        } else {
            throw new RuntimeException("Did not find user with id: " + id);
        }
        return user;
    }

    @Override
    public User save(User object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Integer integer) {
        userRepository.deleteById(integer);

    }

    public void register(UserValidation user) throws UserAlreadyExistException {
        //Let's check if user already registered with us
        if(checkIfUserExist(user.getEmail())){
            throw new UserAlreadyExistException("User already exists for this email");
        }
        // get role for USER
        Role role = roleRepository.findByName("SALES_OFFICER");
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        encodePassword(userEntity, user);
        userEntity.addRole(role); // attach the user to the USER role.
        userRepository.save(userEntity);
    }


    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    private void encodePassword( User userEntity, UserValidation user){
        passwordEncoder = new BCryptPasswordEncoder(10);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
