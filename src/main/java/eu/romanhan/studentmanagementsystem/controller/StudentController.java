package eu.romanhan.studentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import eu.romanhan.studentmanagementsystem.service.StudentService;

@Controller
public class StudentController {

	private StudentService studetnService;

	public StudentController(StudentService studetnService) {
		this.studetnService = studetnService;
	}

	@GetMapping("/students")
	public String getAllUsers(Model model) {
		model.addAttribute("students", studetnService.getAllStudents());
		return "students";
	}
}
