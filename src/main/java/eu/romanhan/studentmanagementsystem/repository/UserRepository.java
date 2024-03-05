package eu.romanhan.studentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.romanhan.studentmanagementsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findByFirstName(String name);

}
