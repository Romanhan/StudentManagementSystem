package eu.romanhan.studentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import eu.romanhan.studentmanagementsystem.dto.UserDto;
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
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register/save")
	public String registration(@ModelAttribute("user") @Valid UserDto userDto, BindingResult result, Model model) {
		User existingUserByEmail = userService.findUserByEmail(userDto.getEmail());
		User existingUserByName = userService.findByFirstName(userDto.getFirstName());

		if (existingUserByEmail != null) {
			result.rejectValue("email", null, "There is already an account registered with the same email");
		}
		if (existingUserByName != null) {
			result.rejectValue("firstName", null, "There is already an account registered with the same name");
		}

		if (result.hasErrors()) {
			model.addAttribute("user", userDto);
			return "/register";
		}

		userService.saveUser(userDto);
		return "redirect:/register?success";
	}
}
