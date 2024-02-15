package eu.romanhan.studentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import eu.romanhan.studentmanagementsystem.entity.Student;
import eu.romanhan.studentmanagementsystem.service.StudentService;

@Controller
public class StudentController {

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/students")
	public String getAllUsers(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}

	@GetMapping("students/new")
	public String createStudent(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
	}

	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}
}
