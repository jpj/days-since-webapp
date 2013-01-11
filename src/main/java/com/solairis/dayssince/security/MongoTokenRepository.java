/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.security;

import com.solairis.dayssince.repository.TokenRepository;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author JanieBear
 */
@Component
public class MongoTokenRepository implements PersistentTokenRepository {

	@Resource
	private TokenRepository tokenRepository;

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		this.tokenRepository.save(token);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		PersistentRememberMeToken token = this.tokenRepository.findBySeries(series);
		PersistentRememberMeToken updatedToken = new PersistentRememberMeToken(token.getUsername(), token.getSeries(), tokenValue, token.getDate());
		this.tokenRepository.save(updatedToken);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		return this.tokenRepository.findBySeries(seriesId);
	}

	@Override
	public void removeUserTokens(String username) {
		Iterable<PersistentRememberMeToken> tokens = this.tokenRepository.findByUsername(username);
		this.tokenRepository.delete(tokens);
	}

}
