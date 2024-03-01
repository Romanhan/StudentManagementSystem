package eu.romanhan.studentmanagementsystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import eu.romanhan.studentmanagementsystem.entity.Student;
import eu.romanhan.studentmanagementsystem.entity.User;
import eu.romanhan.studentmanagementsystem.service.StudentService;
import jakarta.validation.Valid;

@Controller
public class StudentController {

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

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

	@GetMapping("/students")
	public String getAllUsers(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("students/new")
	public String createStudent(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
	}

	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") @Valid Student student, BindingResult result) {
		if (result.hasErrors()) {
			return "create_student";
		}
		if (studentService.existsByEmail(student.getEmail())) {
			result.rejectValue("email", "unique", "This Email already exists");
			return "create_student";
		}

		studentService.saveStudent(student);

		return "redirect:/students";
	}

	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
	}

	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") @Valid Student student,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("student", student);
			return "edit_student";
		}

		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(student.getId());
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());

		studentService.updateStudent(existingStudent);
		return "redirect:/students";
	}

	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/students";
	}
}
