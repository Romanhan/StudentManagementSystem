package eu.romanhan.studentmanagementsystem.service;

import eu.romanhan.studentmanagementsystem.dto.UserDto;
import eu.romanhan.studentmanagementsystem.entity.User;

public interface UserService {

	void saveUser(UserDto userDto);

	User findUserByEmail(String email);

	User findByFirstName(String name);

}
