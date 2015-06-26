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
 * The Class Container.
 * 
 * @author Belili Fahem - belili.fahem@gmail.com
 * 
 */
@XmlRootElement(name = "container")
public class Container {

	/** The container id. */
	private String id;

	/** The app id. */
	private String appId;

	/**
	 * Instantiates a new OM2M container.
	 */
	public Container() {
	}

	/**
	 * Instantiates a new OM2M container.
	 *
	 * @param appId
	 *            the app id
	 *            
	 * @param containerId
	 *            the container id
	 */
	public Container(String appId, String containerId) {
		super();
		this.id = containerId;
		this.appId = appId;
	}

	/**
	 * Gets the container id.
	 *
	 * @return the container id
	 */
	@XmlAttribute(name="om2m:id")
	public String getId() {
		return id;
	}

	/**
	 * Sets the container id.
	 *
	 * @param containerId the new container id
	 */
	public void setId(String containerId) {
		this.id = containerId;
	}

	/**
	 * Gets the app.
	 *
	 * @return the app
	 */
	@XmlTransient
	public String getAppId() {
		return appId;
	}

	/**
	 * Sets the app.
	 *
	 * @param app
	 *            the new app
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

}
