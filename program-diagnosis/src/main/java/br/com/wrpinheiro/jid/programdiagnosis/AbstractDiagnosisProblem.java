/*
 * Copyright 2006-2013 Wellington Ricardo Pinheiro.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.wrpinheiro.jid.programdiagnosis;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jgraphlib.familyset.FamilySet;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.statistics.DiagnosisStatistics;

public abstract class AbstractDiagnosisProblem {
	/**
	 * Log instance.
	 */
	private static Logger LOG = Logger
			.getLogger(AbstractDiagnosisProblem.class);

	/**
	 * An identification for this diagnosis problem (DP).
	 */
	private String name;

	/**
	 * Observations for the system.
	 */
	private Map<String, Integer> observations = new LinkedHashMap<String, Integer>();

	/**
	 * The statistics maintainer.
	 */
	private DiagnosisStatistics statistics = DiagnosisStatistics.getInstance();

	/**
	 * Creates an instance of this diagnosis problem and set its name.
	 * 
	 * @param name
	 *            the name of the system.
	 */
	public AbstractDiagnosisProblem(String name) {
		this.name = name;
	}

	/**
	 * Apply all observations to the system.
	 * 
	 * @throws ConflictException
	 *             exception thrown if a conflict is detected while setting the
	 *             observations in the system.
	 */
	public abstract void applyObservations() throws ConflictException;

	/**
	 * Find a diagnose for the system.
	 * 
	 * @return the hyphotesis set.
	 */
	public abstract FamilySet<IntegerComponent> diagnose();

	/**
	 * Add an observation for the system.
	 * 
	 * @param connName
	 *            name of the connection where the observation will be set.
	 * @param value
	 *            the value for the observation.
	 */
	public void addObservation(String connName, Integer value) {
		this.observations.put(connName, value);
	}

	/**
	 * Set the observation for the system.
	 * 
	 * @param observations
	 *            the observations to set
	 */
	public void setObservations(Map<String, Integer> observations) {
		this.observations = observations;
	}

	/**
	 * Get all observations.
	 * 
	 * @return the observations
	 */
	public Map<String, Integer> getObservations() {
		return observations;
	}

	/**
	 * Get the name of the DP.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the statistics
	 */
	public DiagnosisStatistics getStatistics() {
		return statistics;
	}

	/**
	 * Load the observations from a file.
	 * @param propertyFile the file with the observations.
	 */
	public void loadObservations(String propertyFile) {
		try {
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream(propertyFile);
			props.load(fis);
			
			for (Enumeration<Object> it = props.keys(); it.hasMoreElements();) {
				String key = (String)it.nextElement();
				String value = (String)props.get(key);
				LOG.debug("setting observation " + key + "=" + value);
				this.addObservation(key, Integer.valueOf(value));
			}
		} catch (IOException ex) {
			throw new RuntimeException("Could not load property file: "
					+ propertyFile, ex);
		}
	}
	
	/**
	 * Load the observations from a map of properties.
	 * @param properties the observations.
	 */
	public void loadObservations(Map<String, String> properties) {
		for (String key : properties.keySet()) {
			String value = properties.get(key);
			LOG.debug("setting observation " + key + "=" + value);
			this.addObservation(key, Integer.valueOf(value));
		}
	}
}