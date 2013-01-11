/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.repository;

import com.solairis.dayssince.security.MongoPersistentRememberMeToken;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author JanieBear
 */
public interface TokenRepository extends CrudRepository<MongoPersistentRememberMeToken, String> {

	Iterable<MongoPersistentRememberMeToken> findByUsername(String username);

}
