package com.titilope.web_inventory.AjokeStores.Controller;

import com.titilope.web_inventory.AjokeStores.Entity.Role;
import com.titilope.web_inventory.AjokeStores.Entity.User;
import com.titilope.web_inventory.AjokeStores.Service.RoleService;
import com.titilope.web_inventory.AjokeStores.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@GetMapping("/login")
	public String showMyLoginPage() {
		
		return "auth/login";
	}
	
	// add request mapping for /access-denied
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {

		return "access-denied";
	}

	@GetMapping("/register")
	public String register(Model model) {

		User user = new User();

		model.addAttribute("user", user);

		return "auth/register";
	}

	@PostMapping("/saveUser")
	public String saveProduct(@ModelAttribute("user") User user){

		Role role = roleService.findById(2);

		user.addRole(role);

		userService.save(user);
		System.out.println(user);

		return "auth/login";

	}
}
