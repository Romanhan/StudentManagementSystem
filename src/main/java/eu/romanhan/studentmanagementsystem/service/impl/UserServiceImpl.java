package eu.romanhan.studentmanagementsystem.service.impl;

import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import eu.romanhan.studentmanagementsystem.dto.UserDto;
import eu.romanhan.studentmanagementsystem.entity.Role;
import eu.romanhan.studentmanagementsystem.entity.User;
import eu.romanhan.studentmanagementsystem.repository.RoleRepository;
import eu.romanhan.studentmanagementsystem.repository.UserRepository;
import eu.romanhan.studentmanagementsystem.service.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	@Override
	public void saveUser(UserDto userDto) {
		User userToSave = new User();
		userToSave.setFirstName(userDto.getFirstName());
		userToSave.setLastName(userDto.getLastName());
		userToSave.setEmail(userDto.getEmail());
		userToSave.setPassword(passwordEncoder.encode(userDto.getPassword()));

		Role role = roleRepository.findByName("ROLE_USER");
		if (role == null) {
			role = checkRoleExists();
		}

		userToSave.setRoles(Arrays.asList(role));
		userRepository.save(userToSave);
	}

	private Role checkRoleExists() {
		Role role = new Role();
		role.setName("ROLE_USER");
		return roleRepository.save(role);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByFirstName(String name) {
		return userRepository.findByFirstName(name);
	}

}
