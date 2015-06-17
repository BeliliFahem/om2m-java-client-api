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

import javax.xml.bind.annotation.XmlRootElement;



/**
 * The Class OM2MSubscription.
 * 
 * @author Belili Fahem - belili.fahem@gmail.com
 */
@XmlRootElement(name = "subscription")
public class OM2MSubscription {

	/**  URL of the listener. */
	private String contact;

	/**
	 * Instantiates a new OM2M subscription.
	 */
	public OM2MSubscription() {}

	/**
	 * Instantiates a new OM2M subscription.
	 *
	 * @param contact the contact
	 */
	public OM2MSubscription(String contact) {
		super();
		this.contact = contact;
	}

	/**
	 * Gets the contact.
	 *
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * Sets the contact.
	 *
	 * @param contact the new contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

}
