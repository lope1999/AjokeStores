package com.titilope.web_inventory.AjokeStores.Controller;


import com.titilope.web_inventory.AjokeStores.Service.UserService;
import com.titilope.web_inventory.AjokeStores.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import validation.UserValidation;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model)
    {
        UserValidation user = new UserValidation();
        model.addAttribute("user", user);
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegistration(final @Valid UserValidation userData, final BindingResult bindingResult, final Model model)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("user", userData);
            return "auth/register";
        }

        try {
            userService.register(userData);
        }catch (UserAlreadyExistException e){
            bindingResult.rejectValue("email", "userData.email","An account already exists for this email.");
            model.addAttribute("registrationForm", userData);
            return "auth/register";
        }
        return "redirect:/products/list";
    }
}
