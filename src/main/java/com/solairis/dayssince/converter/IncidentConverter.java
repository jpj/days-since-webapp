/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solairis.dayssince.converter;

import com.solairis.incident.entity.Incident;
import com.solairis.incident.repository.IncidentRepository;
import javax.annotation.Resource;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author josh
 */
public class IncidentConverter implements Converter<String, Incident> {

	@Resource
	private IncidentRepository incidentRepository;

	@Override
	public Incident convert(String id) {
		return incidentRepository.findOne(id);
	}

}
