package eu.romanhan.studentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.romanhan.studentmanagementsystem.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	boolean existsByEmail(String email);

}
