/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.connect;

import com.solairis.incident.entity.User;
import com.solairis.incident.repository.UserRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mongo.ConnectionService;
import org.springframework.stereotype.Component;

/**
 *
 * @author JanieBear
 */
@Component
public class UserConnectionSignUp implements ConnectionSignUp {

	@Resource
	private PasswordEncoder passwordEncoder;
	@Resource
	private UserRepository userRepository;
	@Resource
	private UsersConnectionRepository usersConnectionRepository;

	@Override
	public String execute(Connection<?> connection) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Object user = securityContext.getAuthentication().getPrincipal();

		if (String.class.isAssignableFrom(user.getClass()) && user.equals("anonymousUser")) {
//			List<String> userIds = this.connectionService.getUserIds(connection.createData().getProviderId(), connection.createData().getProviderUserId());
			Set<String> userIds = this.usersConnectionRepository.findUserIdsConnectedTo(connection.createData().getProviderId(), new HashSet<String>(Arrays.asList(connection.createData().getProviderUserId())));

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
				return userIds.iterator().next();
			} else {
				throw new IllegalArgumentException("Only 1 user Id may be associated with a provider. We found "+userIds.size());
			}
		} else {
			return ((UserDetails)user).getUsername();
		}
	}
}
