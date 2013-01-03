/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.connect;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.mongo.ConnectionService;
import org.springframework.stereotype.Component;

/**
 *
 * @author JanieBear
 */
@Component
public class UserConnectionSignUp implements ConnectionSignUp {

	@Resource
	private ConnectionService connectionService;
	@Resource
	private UserDetailsService namePassUserService;

	@Override
	public String execute(Connection<?> connection) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Object user = securityContext.getAuthentication().getPrincipal();

		if (String.class.isAssignableFrom(user.getClass()) && user.equals("anonymousUser")) {
			List<String> userIds = this.connectionService.getUserIds(connection.createData().getProviderId(), connection.createData().getProviderUserId());

			if (userIds.isEmpty()) {
				// Create new user
				return connection.getDisplayName();
			} else if (userIds.size() == 1) {
				UserDetails userDetails = this.namePassUserService.loadUserByUsername(userIds.get(0));
				return userDetails.getUsername();
			} else {
				throw new IllegalArgumentException("Only 1 user Id may be associated with a provider. We found "+userIds.size());
			}
		} else {
			return ((UserDetails)user).getUsername();
		}
	}
}
