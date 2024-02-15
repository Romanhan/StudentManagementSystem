package eu.romanhan.studentmanagementsystem.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import eu.romanhan.studentmanagementsystem.entity.Student;
import eu.romanhan.studentmanagementsystem.repository.StudentRepository;
import eu.romanhan.studentmanagementsystem.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

}
