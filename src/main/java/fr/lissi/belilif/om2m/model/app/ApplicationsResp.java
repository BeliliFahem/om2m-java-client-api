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

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import fr.lissi.belilif.om2m.model.NamedReference;


/**
 * The Class ApplicationsResp.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
@XmlRootElement(name = "applications")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApplicationsResp implements ApplicationGeneric {

	/** The access right id. */
	private String accessRightID;

	// XmLElementWrapper generates a wrapper element around XML representation
	/** The application collection. */
	@XmlElementWrapper(name = "applicationCollection")
	// XmlElement sets the name of the entities in collection
	@XmlElement(name = "namedReference", type = NamedReference.class)
	private ArrayList<NamedReference> applicationCollection;

	/** The application annc collection. */
	private String applicationAnncCollection;

	/** The subscriptions reference. */
	private String subscriptionsReference;

	// TODO dachou dwayyi
	/** The mgmt objs reference. */
	private String mgmtObjsReference;

	/**
	 * Instantiates a new applications resp.
	 */
	public ApplicationsResp() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new applications resp.
	 *
	 * @param accessRightID the access right id
	 * @param applicationCollection the application collection
	 * @param applicationAnncCollection the application annc collection
	 * @param subscriptionsReference the subscriptions reference
	 * @param mgmtObjsReference the mgmt objs reference
	 */
	public ApplicationsResp(String accessRightID, ArrayList<NamedReference> applicationCollection,
			String applicationAnncCollection, String subscriptionsReference, String mgmtObjsReference) {
		super();
		this.accessRightID = accessRightID;
		this.applicationCollection = applicationCollection;
		this.applicationAnncCollection = applicationAnncCollection;
		this.subscriptionsReference = subscriptionsReference;
		this.mgmtObjsReference = mgmtObjsReference;
	}

	/**
	 * Gets the access right id.
	 *
	 * @return the access right id
	 */
	public String getAccessRightID() {
		return accessRightID;
	}

	/**
	 * Sets the access right id.
	 *
	 * @param accessRightID the new access right id
	 */
	public void setAccessRightID(String accessRightID) {
		this.accessRightID = accessRightID;
	}

	/**
	 * Gets the application collection.
	 *
	 * @return the application collection
	 */
	public ArrayList<NamedReference> getApplicationCollection() {
		return applicationCollection;
	}

	/**
	 * Sets the application collection.
	 *
	 * @param applicationCollection the new application collection
	 */
	public void setApplicationCollection(ArrayList<NamedReference> applicationCollection) {
		this.applicationCollection = applicationCollection;
	}

	/**
	 * Gets the application annc collection.
	 *
	 * @return the application annc collection
	 */
	public String getApplicationAnncCollection() {
		return applicationAnncCollection;
	}

	/**
	 * Sets the application annc collection.
	 *
	 * @param applicationAnncCollection the new application annc collection
	 */
	public void setApplicationAnncCollection(String applicationAnncCollection) {
		this.applicationAnncCollection = applicationAnncCollection;
	}

	/**
	 * Gets the subscriptions reference.
	 *
	 * @return the subscriptions reference
	 */
	public String getSubscriptionsReference() {
		return subscriptionsReference;
	}

	/**
	 * Sets the subscriptions reference.
	 *
	 * @param subscriptionsReference the new subscriptions reference
	 */
	public void setSubscriptionsReference(String subscriptionsReference) {
		this.subscriptionsReference = subscriptionsReference;
	}

	/**
	 * Gets the mgmt objs reference.
	 *
	 * @return the mgmt objs reference
	 */
	public String getMgmtObjsReference() {
		return mgmtObjsReference;
	}

	/**
	 * Sets the mgmt objs reference.
	 *
	 * @param mgmtObjsReference the new mgmt objs reference
	 */
	public void setMgmtObjsReference(String mgmtObjsReference) {
		this.mgmtObjsReference = mgmtObjsReference;
	}
}
