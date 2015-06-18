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



/**
 * The Class OM2MContentInstance.
 * 
 * @author Belili Fahem - belili.fahem@gmail.com
 */
public class OM2MContentInstance {
	
	/** The app id. */
	private String appId;

	/**  The container id. */
	private String containerId;

	/** The body. */
	private String body;

	/**
	 * Instantiates a new OM2M content instance.
	 */
	public OM2MContentInstance() {}

	/**
	 * Instantiates a new OM2M content instance.
	 *
	 * @param appId the app id
	 * @param containerId the container id
	 * @param body the body
	 */
	public OM2MContentInstance(String appId,  String containerId, String body) {
		super();
		this.appId = appId;
		this.containerId = containerId;
		this.body = body;
	}

	/**
	 * Gets the container id.
	 *
	 * @return the container
	 */
	public String getContainerId() {
		return containerId;
	}

	/**
	 * Sets the container id.
	 *
	 * @param containerId the new container id
	 */
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Sets the body.
	 *
	 * @param body the new body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Gets the app id.
	 *
	 * @return the app id
	 */
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

}
