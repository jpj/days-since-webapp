/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.security;

import com.solairis.incident.entity.User;
import com.solairis.incident.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import org.springframework.security.core.GrantedAuthority;
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
public class PrincipalUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Inject
	public PrincipalUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByLogin(username);

		Set<GrantedAuthority> grantedAuthoritys = new HashSet<GrantedAuthority>() {
			{
				add(new SimpleGrantedAuthority("ROLE_USER"));
			}
		};
		UserDetails userDetails = new Principal(user.getPassword(), user.getLogin(), grantedAuthoritys, true, true, true, true, user);
		return userDetails;
	}

}
