package web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private Validator userValidator;
	
	@Autowired
	private UserService userService;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

	@GetMapping("/users")
	public String getUsers(@ModelAttribute("user") User user, ModelMap model) {
		List<User> users = userService.getAllUser();
		model.addAttribute("users", users);
		return "users";
	}
	
	@GetMapping("/user")
	public String getUser(@RequestParam("id") Integer userId, ModelMap model) {
		System.out.println(userId);
		Integer id = userId;
		User user = userService.get(id);
		model.addAttribute("user", user);
		return "user";
	}
	
	@PostMapping("/create")
	public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult, ModelMap model) {
		
		userValidator.validate(user, bindingResult);
		
		if(bindingResult.hasErrors()) {
			List<User> users = userService.getAllUser();
			model.addAttribute("users", users);
			return "users";
		}else {
			userService.create(user);
			return "redirect:/users";
		}
	}
	
	@DeleteMapping("/delete")
	public String deleteUser(@RequestParam("id") Integer id) {
		userService.delete(id);
		return "redirect:/users";
	}
	
	@PatchMapping("/update")
	public String updateUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
		
		userValidator.validate(user, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "user";
		}else {
			userService.update(user);
			return "redirect:/users";
		}
	}
}
