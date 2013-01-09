/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.connect;

import com.solairis.dayssince.security.ManualAuthenticationToken;
import javax.annotation.Resource;
import org.springframework.security.core.Authentication;
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
	private UserDetailsService principalUserDetailsService;

	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetails userDetails = this.principalUserDetailsService.loadUserByUsername(userId);

		Authentication authentication = new ManualAuthenticationToken(userDetails, userDetails.getAuthorities());
		securityContext.setAuthentication(authentication);

		request.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext, WebRequest.SCOPE_SESSION);
		return null;
	}
}
