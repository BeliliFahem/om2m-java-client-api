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
 * The Class OM2MContainer.
 * 
 * @author Belili Fahem - belili.fahem@gmail.com
 * 
 */
@XmlRootElement(name = "container")
public class OM2MContainer {

	/** The container id. */
	private String id;

	/** The app. */
	private OM2MApplication app;

	/**
	 * Instantiates a new OM2M container.
	 */
	public OM2MContainer() {
	}

	/**
	 * Instantiates a new OM2M container.
	 *
	 * @param containerId
	 *            the container id
	 * @param app
	 *            the app
	 */
	public OM2MContainer(String containerId, OM2MApplication app) {
		super();
		this.id = containerId;
		this.app = app;
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
	public OM2MApplication getApp() {
		return app;
	}

	/**
	 * Sets the app.
	 *
	 * @param app
	 *            the new app
	 */
	public void setApp(OM2MApplication app) {
		this.app = app;
	}

}
