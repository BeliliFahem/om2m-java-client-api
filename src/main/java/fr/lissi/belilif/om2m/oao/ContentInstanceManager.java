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

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;

import fr.lissi.belilif.om2m.model.OM2MContentInstance;
import fr.lissi.belilif.om2m.model.OM2MContentInstancesResponse;
import fr.lissi.belilif.om2m.model.OM2MSubscription;
import fr.lissi.belilif.om2m.rest.WebServiceActions;
import fr.lissi.belilif.util.jaxb.JAXBMapper;



/**
 * The Class ContentInstanceManager.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
public class ContentInstanceManager extends AbstractOm2mManager<OM2MContentInstance, OM2MContentInstancesResponse> {

	/**
	 * Instantiates a new content instance manager.
	 *
	 * @param hostName the host name
	 * @param authorization the authorization
	 */
	public ContentInstanceManager(String hostName, String authorization) {
		super(hostName, authorization);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#create(java.lang.Object)
	 */
	@Override
	public int create(OM2MContentInstance obj) {
		LOGGER.info("Create ContentInstance for " + obj.getAppId()+"/"+obj.getContainerId());
		int resp = -1;
		try {
			resp = WebServiceActions.doPost(this.OM2MUrlBase + "applications/" + obj.getAppId()
					+ "/containers/" + obj.getContainerId() + "/contentInstances", obj.getBody(), this.headers);
			
			LOGGER.info("ContentInstance creation in '" + obj.getAppId() + "/" + obj.getContainerId()
					+ "' status : " + resp);
			LOGGER.debug("ContentInstance body : \n" + obj.getBody());
		} catch (HttpResponseException e) {
			resp = e.getStatusCode();
			LOGGER.info(e.getStatusCode() + " / " + e.getMessage());
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO ClientProtocolException
			e.printStackTrace();
		} catch (IOException e) {
			// TODO IOException
			e.printStackTrace();
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#delete(java.lang.Object)
	 */
	@Override
	public int delete(OM2MContentInstance obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#subscribe(java.lang.Object, fr.lissi.belilif.om2m.model.OM2MSubscription)
	 */
	@Override
	public int subscribe(OM2MContentInstance obj, OM2MSubscription subscription) {
		LOGGER.info("Create : \n" + JAXBMapper.objectToXMLString(obj));
		int resp = -1;
		try {
			resp = WebServiceActions.doPost(this.OM2MUrlBase + "applications/" + obj.getAppId()
					+ "/containers/" + JAXBMapper.objectToXMLString(subscription) + "/contentInstances/subscriptions", obj.getBody(),
					this.headers);
		} catch (HttpResponseException e) {
			resp = e.getStatusCode();
			LOGGER.info(e.getStatusCode() + " / " + e.getMessage());
		} catch (ClientProtocolException e) {
			// TODO ClientProtocolException
			e.printStackTrace();
		} catch (IOException e) {
			// TODO IOException
			e.printStackTrace();
		}
		return resp;
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#getAll()
	 */
	@Override
	public List<OM2MContentInstancesResponse> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#getFirstN(int)
	 */
	@Override
	public List<OM2MContentInstancesResponse> getFirstN(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#searchByKeywords(java.util.List)
	 */
	@Override
	public List<OM2MContentInstancesResponse> searchByKeywords(List<String> keywords) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#get(java.lang.Object)
	 */
	@Override
	public OM2MContentInstancesResponse get(OM2MContentInstance obj) {
		// TODO Auto-generated method stub
		return null;
	}
}
