/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.security;

import java.util.Date;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

/**
 *
 * @author JanieBear
 */
public class MongoPersistentRememberMeToken extends PersistentRememberMeToken {

	private String id;

	public MongoPersistentRememberMeToken(String username, String series, String tokenValue, Date date) {
		super(username, series, tokenValue, date);
		this.id = series;
	}

	public String getId() {
		return id;
	}

}
