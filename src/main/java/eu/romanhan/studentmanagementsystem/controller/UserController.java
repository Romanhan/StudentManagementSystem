package eu.romanhan.studentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import eu.romanhan.studentmanagementsystem.entity.User;
import eu.romanhan.studentmanagementsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class UserController {

	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "/login";
	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register/save")
	public String registration(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
		User existingUserByEmail = userService.findUserByEmail(user.getEmail());
		User existingUserByName = userService.findByFirstName(user.getFirstName());

		if (existingUserByEmail != null) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
		}
		if (existingUserByName != null) {
			result.rejectValue("firstName", null, "There is already an account registered with the same name");
		}

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "/register";
		}

		userService.saveUser(user);
		return "redirect:/register?success";
	}
}
