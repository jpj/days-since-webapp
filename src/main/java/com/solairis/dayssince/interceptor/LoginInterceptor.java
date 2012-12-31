/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author JanieBear
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Resource
	private ConnectionRepository connectionRepository;
	@Resource
	private AuthenticationManager authenticationManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Object user = securityContext.getAuthentication().getPrincipal();

		if (String.class.isAssignableFrom(user.getClass()) && user.equals("anonymousUser")) {
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken("anonymous", "badpass", new ArrayList<SimpleGrantedAuthority>() {
				{
					add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
					add(new SimpleGrantedAuthority("ROLE_USER"));
				}
			});
			Authentication authentication = authenticationManager.authenticate(authRequest);
			securityContext.setAuthentication(authentication);

			HttpSession session = request.getSession(true);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
		}

		return true;
	}
}