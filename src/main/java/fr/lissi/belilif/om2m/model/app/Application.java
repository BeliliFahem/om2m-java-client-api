/*******************************************************************************
 * Copyright (c) 2014-2015 LiSSi (www.lab.lissi.fr)
 * 122 rue Paul Armangot, 94400 Vitry-Sur-Seine - France.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Belili Fahem -  Management and initial specification,
 *         conception, implementation, test and documentation.
 ******************************************************************************/

package fr.lissi.belilif.om2m.model.app;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The Class Application.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
@XmlRootElement(name = "application")
@XmlType(propOrder = { "appId", "searchStrings" })
public class Application implements ApplicationGeneric {

	/** The app id. */
	private String appId;
	
	/** The search strings. */
	private List<String> searchStrings;

	/**
	 * Instantiates a new OM2M application.
	 */
	public Application() {}

	/**
	 * Instantiates a new OM2M application.
	 *
	 * @param appId the app id
	 * @param searchStrings the search strings
	 */
	public Application(String appId, List<String> searchStrings) {
		super();
		this.appId = appId;
		this.searchStrings = searchStrings;
	}
	
	/**
	 * Instantiates a new OM2M application.
	 *
	 * @param appId the app id
	 */
	public Application(String appId) {
		super();
		this.appId = appId;
	}

	/**
	 * Gets the app id.
	 *
	 * @return the app id
	 */
	@XmlAttribute
	public String getAppId() {
		return appId;
	}

	/**
	 * Sets the app id.
	 *
	 * @param appId the new app id
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * Gets the search strings.
	 *
	 * @return the search strings
	 */
	@XmlElementWrapper(name = "searchStrings")
	@XmlElement(name = "searchString")
	public List<String> getSearchStrings() {
		return searchStrings;
	}

	/**
	 * Sets the search strings.
	 *
	 * @param searchStrings the new search strings
	 */
	public void setSearchStrings(List<String> searchStrings) {
		this.searchStrings = searchStrings;
	}
}
