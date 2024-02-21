package eu.romanhan.studentmanagementsystem.service;

import java.util.List;

import eu.romanhan.studentmanagementsystem.entity.Student;

public interface StudentService {

	List<Student> getAllStudents();

	Student saveStudent(Student student);

	Student getStudentById(Long id);

	Student updateStudent(Student student);

	void deleteStudent(Long id);

	boolean existsByEmail(String email);
}
