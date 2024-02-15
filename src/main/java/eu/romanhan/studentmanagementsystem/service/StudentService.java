package eu.romanhan.studentmanagementsystem.service;

import java.util.List;

import eu.romanhan.studentmanagementsystem.entity.Student;

public interface StudentService {

	List<Student> getAllStudents();

	Student saveStudent(Student student);
}
