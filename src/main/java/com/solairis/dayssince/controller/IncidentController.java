/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.controller;

import com.solairis.incident.entity.Incident;
import com.solairis.incident.repository.IncidentRepository;
import javax.annotation.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author josh
 */
@Controller
@RequestMapping(value = "/api/incident")
public class IncidentController {

	@Resource
	private IncidentRepository incidentRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Incident> list(Pageable pageable) {
		return this.incidentRepository.findAll(pageable);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Incident get(@PathVariable("id") Incident incident) {
		return incident;
	}
}
