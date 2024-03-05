package eu.romanhan.studentmanagementsystem.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import eu.romanhan.studentmanagementsystem.entity.Role;
import eu.romanhan.studentmanagementsystem.entity.User;
import eu.romanhan.studentmanagementsystem.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user = userRepository.findByFirstName(name);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					mapRolesToAuthorities(user.getRoles()));
		} else {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		Collection<? extends GrantedAuthority> mapRoles = roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return mapRoles;
	}

}
