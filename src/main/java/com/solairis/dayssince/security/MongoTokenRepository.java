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
		this.tokenRepository.save(new MongoPersistentRememberMeToken(token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate()));
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		MongoPersistentRememberMeToken token = this.tokenRepository.findOne(series);
		MongoPersistentRememberMeToken updatedToken = new MongoPersistentRememberMeToken(token.getUsername(), token.getSeries(), tokenValue, token.getDate());
		this.tokenRepository.save(updatedToken);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		return this.tokenRepository.findOne(seriesId);
	}

	@Override
	public void removeUserTokens(String username) {
		Iterable<MongoPersistentRememberMeToken> tokens = this.tokenRepository.findByUsername(username);
		if (tokens != null) {
			this.tokenRepository.delete(tokens);
		}
	}

}
