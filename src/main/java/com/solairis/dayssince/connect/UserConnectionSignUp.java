/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.connect;

import com.solairis.incident.entity.User;
import com.solairis.incident.repository.UserRepository;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	private UserRepository userRepository;

	@Override
	public String execute(Connection<?> connection) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Object user = securityContext.getAuthentication().getPrincipal();

		if (String.class.isAssignableFrom(user.getClass()) && user.equals("anonymousUser")) {
			List<String> userIds = this.connectionService.getUserIds(connection.createData().getProviderId(), connection.createData().getProviderUserId());

			if (userIds.isEmpty()) {
				// Create new user
				User u = new User();
				if (connection.fetchUserProfile().getUsername() != null) {
					User prevUser = this.userRepository.findByLogin(connection.fetchUserProfile().getUsername());
					if (prevUser == null) {
						u.setLogin(connection.fetchUserProfile().getUsername());
					} else {
						u.setLogin(connection.fetchUserProfile().getUsername() + (this.userRepository.count() + 1));
					}
				} else {
					u.setLogin("user" + (this.userRepository.count() + 1));
				}

				u.setPassword("!bunk");
				this.userRepository.save(u);
				return u.getLogin();
			} else if (userIds.size() == 1) {
				return userIds.get(0);
			} else {
				throw new IllegalArgumentException("Only 1 user Id may be associated with a provider. We found "+userIds.size());
			}
		} else {
			return ((UserDetails)user).getUsername();
		}
	}
}
