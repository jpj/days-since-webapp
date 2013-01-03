/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.connect;

import com.solairis.incident.entity.User;
import com.solairis.incident.repository.UserRepository;
import java.util.ArrayList;
import javax.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author JanieBear
 */
@Component
public class UserSignInAdapter implements SignInAdapter {

	@Resource
	private AuthenticationManager authenticationManager;
	@Resource
	private UserDetailsService userUserDetailsService;
	@Resource
	private UserRepository userRepository;

	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		User user = this.userRepository.findByLogin(userId);
		UserDetails userDetails = this.userUserDetailsService.loadUserByUsername(userId);

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword());
		authentication = authenticationManager.authenticate(authentication);
		securityContext.setAuthentication(authentication);

		request.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext, WebRequest.SCOPE_SESSION);
		return null;
	}
}
