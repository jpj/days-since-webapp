/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.controller;

import com.solairis.incident.entity.Incident;
import com.solairis.incident.repository.IncidentRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author josh
 */
@Controller
@RequestMapping(value = "/api/incident")
public class IncidentController {

	@Resource
	private IncidentRepository incidentRepository;

	@RequestMapping(method= RequestMethod.GET)
	public List<Incident> list() {
		List<Incident> incidents = new ArrayList<Incident>();
		for (Incident incident : this.incidentRepository.findAll()) {
			incidents.add(incident);
		}
		return incidents;
	}
}
