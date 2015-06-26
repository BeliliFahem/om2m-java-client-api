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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;


/**
 * The Class NamedReference.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
@XmlRootElement(name = "namedReference")
@XmlAccessorType(XmlAccessType.FIELD)
public class NamedReference {

	/** The id. */
	@XmlAttribute
	private String id;
	
	/** The named reference value. */
	@XmlValue
	private String namedReferenceValue;

	/**
	 * Instantiates a new named reference.
	 */
	public NamedReference() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new named reference.
	 *
	 * @param id the id
	 * @param namedReferenceValue the named reference value
	 */
	public NamedReference(String id, String namedReferenceValue) {
		super();
		this.id = id;
		this.namedReferenceValue = namedReferenceValue;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
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
	 * Gets the named reference value.
	 *
	 * @return the named reference value
	 */
	public String getNamedReferenceValue() {
		return namedReferenceValue;
	}

	/**
	 * Sets the named reference value.
	 *
	 * @param namedReferenceValue the new named reference value
	 */
	public void setNamedReferenceValue(String namedReferenceValue) {
		this.namedReferenceValue = namedReferenceValue;
	}
	
	
}
