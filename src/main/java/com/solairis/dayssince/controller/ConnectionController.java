/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author JanieBear
 */
@Controller
@RequestMapping(value = "/api/connectioncontroller")
public class ConnectionController {

	@Resource
	private ConnectionRepository connectionRepository;

	@Resource
	private ConnectionFactoryLocator connectionFactoryLocator;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Connection<?>> list() {
		return this.connectionRepository.findConnections("facebook");
//		return this.connectionFactoryLocator.registeredProviderIds();
	}

}
