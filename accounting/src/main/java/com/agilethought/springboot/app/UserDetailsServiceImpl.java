package com.agilethought.springboot.app;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * Class form implements {@link UserDetailsService} required for Spring Security
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("agilethought".equals(username)) {
			return new User("agilethought", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}
