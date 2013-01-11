/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

/**
 *
 * @author JanieBear
 */
public interface TokenRepository extends CrudRepository<PersistentRememberMeToken, String> {

	PersistentRememberMeToken findBySeries(String series);
	Iterable<PersistentRememberMeToken> findByUsername(String username);

}
