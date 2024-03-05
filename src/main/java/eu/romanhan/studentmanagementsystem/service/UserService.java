package eu.romanhan.studentmanagementsystem.service;

import eu.romanhan.studentmanagementsystem.entity.User;

public interface UserService {

	void saveUser(User user);

	User findUserByEmail(String email);

	User findByFirstName(String name);

}
