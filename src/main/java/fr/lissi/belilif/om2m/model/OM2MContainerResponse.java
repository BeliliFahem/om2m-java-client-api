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
package fr.lissi.belilif.om2m.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



/**
 * The Class OM2MContainerResponse.
 * 
 * @author Belili Fahem - belili.fahem@gmail.com
 */
@XmlRootElement(name = "container")
public class OM2MContainerResponse {

	/** The id. */
	private String id;
	
	/** The app id. */
	private String appId;
	
	/** The expiration time. */
	private String expirationTime;

	/**
	 * Instantiates a new OM2M container response.
	 */
	public OM2MContainerResponse() {}

	/**
	 * Instantiates a new OM2M container response.
	 *
	 * @param id the id
	 * @param appId the app id
	 */
	public OM2MContainerResponse(String id, String appId) {
		super();
		this.id = id;
		this.appId = appId;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@XmlAttribute(name="om2m:id")
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the app id.
	 *
	 * @return the app id
	 */
	@XmlTransient
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
	 * Gets the expiration time.
	 *
	 * @return the expiration time
	 */
	public String getExpirationTime() {
		return expirationTime;
	}

	/**
	 * Sets the expiration time.
	 *
	 * @param expirationTime the new expiration time
	 */
	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}

}
