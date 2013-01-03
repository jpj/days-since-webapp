/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.security;

import com.solairis.incident.entity.User;
import com.solairis.incident.repository.UserRepository;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.inject.Inject;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author JanieBear
 */
@Component
public class UserUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Inject
	public UserUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByLogin(username);
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new ArrayList<SimpleGrantedAuthority>() {
			{
				add(new SimpleGrantedAuthority("ROLE_USER"));
			}
		});
		return userDetails;
	}

}
