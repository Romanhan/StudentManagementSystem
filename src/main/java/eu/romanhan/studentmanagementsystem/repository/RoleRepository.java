package eu.romanhan.studentmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.romanhan.studentmanagementsystem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
