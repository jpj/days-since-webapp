/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.controller;

import com.solairis.incident.entity.Incident;
import com.solairis.incident.repository.IncidentRepository;
import java.text.DecimalFormat;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author josh
 */
@Controller
@RequestMapping(value = "/app/incident")
public class IncidentOtherController {

	@Resource
	private IncidentRepository incidentRepository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String get(@PathVariable("id") Incident incident, Model model) {
		model.addAttribute("daysSince", new DecimalFormat("0.000").format( (double)((new Date().getTime() - incident.getStartDate().getTime())) / 1000 / 60 / 60 / 24));
		model.addAttribute("incident", incident);
		return "incident.html";
	}

}
