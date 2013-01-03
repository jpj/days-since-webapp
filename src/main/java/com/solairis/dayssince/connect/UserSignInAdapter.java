/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.connect;

import java.util.ArrayList;
import javax.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userId, "hoohoo", new ArrayList<SimpleGrantedAuthority>() {
			{
				add(new SimpleGrantedAuthority("ROLE_USER"));
			}
		});
		Authentication authentication = authenticationManager.authenticate(authRequest);
		securityContext.setAuthentication(authentication);

		request.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext, WebRequest.SCOPE_SESSION);
		return null;
	}
}
