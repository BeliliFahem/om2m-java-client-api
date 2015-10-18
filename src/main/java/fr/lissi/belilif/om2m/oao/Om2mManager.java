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

package fr.lissi.belilif.om2m.oao;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.om2m.commons.resource.Resource;

import fr.lissi.belilif.om2m.model.Subscription;

/**
 * The Class Om2mManager.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 * 
 * @param <ResourceType>
 *            the generic request type
 * @param <RespType>
 *            the generic response type
 */
public abstract class Om2mManager<ResourceType extends Resource> {

	/** The headers. */
	protected HashMap<String, String> headers = new HashMap<String, String>();

	/** The OM2M url base. */
	protected String OM2MUrlBase;

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = LogManager.getLogger(Om2mManager.class.getName());

	/**
	 * Instantiates a new abstract om2m manager.
	 *
	 * @param hostName
	 *            the host name
	 * @param authorization
	 *            the authorization
	 */
	public Om2mManager(String hostName, String authorization) {
		headers.put("Authorization", authorization);
		this.OM2MUrlBase = hostName + "/om2m/gscl/";
	}

	/**
	 * This method allow you to create @param <T>.
	 * 
	 * @param obj
	 *            Type of @param <T>.
	 * @return HTTP code response.
	 */
	public abstract int create(ResourceType obj);

	/**
	 * Delete.
	 *
	 * @param obj
	 *            the obj
	 * @return the int
	 */
	public abstract int delete(ResourceType obj);

	// public abstract int delete(String id);

	/**
	 * create a "Subscription" resource for receive asynchronously events from @param obj <T>.
	 *
	 * @param obj
	 *            the obj
	 * @param subscription
	 *            the subscription
	 * @return the int
	 */
	public abstract int subscribe(ResourceType obj, Subscription subscription);

	/**
	 * 
	 * Gets by id only. Only the id must be defined in the object parameter.
	 *
	 * @param obj
	 *            the obj
	 * @return the resp type
	 */
	public abstract ResourceType get(ResourceType obj);

	/**
	 * 
	 * Gets by id only. Only the id must be defined in the object parameter.
	 *
	 * @param obj
	 *            the obj
	 * @return the resp type
	 */
	public abstract ResourceType get(String id);

	public abstract boolean exist(ResourceType obj);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public abstract List<ResourceType> getAll(Resource res);

	/**
	 * Collect the first n elements.
	 *
	 * @param n
	 *            the n
	 * @return the first n
	 */
	public abstract List<ResourceType> getFirstN(int n);

	/**
	 * Search by keywords.
	 *
	 * @param keywords
	 *            the keywords
	 * @return the list
	 */
	public abstract List<ResourceType> searchByKeywords(List<String> keywords);

}
