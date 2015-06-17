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

	/** The container. */
	private OM2MContainer container;

	/** The body. */
	private String body;

	/**
	 * Instantiates a new OM2M content instance.
	 */
	public OM2MContentInstance() {}

	/**
	 * Instantiates a new OM2M content instance.
	 *
	 * @param container the container
	 * @param body the body
	 */
	public OM2MContentInstance(OM2MContainer container, String body) {
		super();
		this.container = container;
		this.body = body;
	}

	/**
	 * Gets the container.
	 *
	 * @return the container
	 */
	public OM2MContainer getContainer() {
		return container;
	}

	/**
	 * Sets the container.
	 *
	 * @param container the new container
	 */
	public void setContainer(OM2MContainer container) {
		this.container = container;
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

}
