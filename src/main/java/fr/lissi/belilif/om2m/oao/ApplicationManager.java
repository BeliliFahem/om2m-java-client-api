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
 *     Belili Fahem (belili.fahem@gmail.com) -  Management and initial specification,
 *         conception, implementation, test and documentation.
 ******************************************************************************/
package fr.lissi.belilif.om2m.oao;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.eclipse.om2m.commons.resource.Application;
import org.eclipse.om2m.commons.resource.Applications;
import org.eclipse.om2m.commons.resource.ReferenceToNamedResource;
import org.eclipse.om2m.commons.resource.Resource;

import fr.lissi.belilif.om2m.model.Subscription;
import fr.lissi.belilif.om2m.rest.HttpGetSimpleResp;
import fr.lissi.belilif.om2m.rest.WebServiceActions;
import fr.lissi.belilif.util.jaxb.JAXBMapper;

/**
 * The Class ApplicationManager.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
public class ApplicationManager extends Om2mManager<Application> {

	/**
	 * Instantiates a new application manager.
	 *
	 * @param OM2MUrlBase
	 *            the OM2M url base
	 * @param authorization
	 *            the authorization
	 */
	ApplicationManager(String OM2MUrlBase, String authorization) {
		super(OM2MUrlBase, authorization);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#create(java.lang.Object)
	 */
	@Override
	public int create(Application obj) {
		String appInXML = JAXBMapper.objectToXMLString(obj);
		LOGGER.info("App to create : " + obj.getAppId());
		LOGGER.info(obj.getAppId() + " in OM2M XML strcuture : \n" + appInXML);
		// appeler le client REST avec le code XML correspondant
		int resp = -1;
		try {
			resp = WebServiceActions.doPost(this.OM2MUrlBase + "applications", appInXML, this.headers);
			LOGGER.info("Application '" + obj.getAppId() + "' creation status : " + resp);
		} catch (HttpResponseException e) {
			resp = e.getStatusCode();
			LOGGER.error("HttpResponseException - " + e.getStatusCode() + " / " + e.getMessage() + "\n"
					+ "Unable to create the application '" + obj.getAppId() + "' with " + this.OM2MUrlBase + "applications");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		} catch (ConnectException e) {
			LOGGER.error(e.getMessage() + ". \nThe server '" + this.OM2MUrlBase.substring(7, 21) + "' is unreachable.");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#delete(java.lang.Object)
	 */
	@Override
	public int delete(Application obj) {
		HttpGetSimpleResp resp;
		int appResponse = -1;
		try {
			resp = WebServiceActions.doDelete(this.OM2MUrlBase + "applications/" + obj.getAppId(), headers);
			appResponse = resp.getStatusCode();
		} catch (HttpResponseException e) {
			LOGGER.error("HttpResponseException - " + e.getStatusCode() + " / " + e.getMessage());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		} catch (ConnectException e) {
			LOGGER.error(e.getMessage() + ". \nThe server '" + this.OM2MUrlBase.substring(7, 21) + "' is unreachable.");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return appResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#subscribe(java.lang.Object, fr.lissi.belilif.om2m.model.Subscription)
	 */
	@Override
	public int subscribe(Application obj, Subscription subscription) {
		// TODO subscribe
		LOGGER.warn("Methode not implemented.");
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#getAll()
	 */
	@Override
	public List<Application> getAll(Resource resource) {
		// TODO manage exceptions
		HttpGetSimpleResp resp = null;
		try {
			resp = WebServiceActions.doGet(this.OM2MUrlBase + "applications", headers);
		} catch (HttpResponseException e) {
			LOGGER.error("HttpResponseException - " + e.getStatusCode() + " / " + e.getMessage());
		} catch (ClientProtocolException e) {
			LOGGER.error(e);
		} catch (ConnectException e) {
			LOGGER.error(e.getMessage() + ". \nThe server '" + this.OM2MUrlBase.substring(7, 21) + "' is unreachable.");
		} catch (IOException e) {
			LOGGER.error(e);
		}
		// xml to object
		Applications appsResp = (Applications) JAXBMapper.XMLStringToObject(resp.getResult(), Applications.class);
		List<Application> apps = new ArrayList<Application>();
		LOGGER.info("MODEL APPS : " + appsResp);
		for (ReferenceToNamedResource refToNamedRes : appsResp.getApplicationCollection().getNamedReference()) {
			apps.add(new Application(refToNamedRes.getId()));
		}
		return apps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#getFirstN(int)
	 */
	@Override
	public List<Application> getFirstN(int n) {
		// TODO getFirstN
		LOGGER.warn("Methode not implemented.");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#searchByKeywords(java.util.List)
	 */
	@Override
	public List<Application> searchByKeywords(List<String> keywords) {
		// TODO searchByKeywords
		LOGGER.warn("Methode not implemented.");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#get(java.lang.Object)
	 */
	@Override
	public Application get(Application obj) {
		return get(obj.getAppId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#get(java.lang.String)
	 */
	@Override
	public Application get(String id) {
		Application appResponse = null;

		HttpGetSimpleResp resp;
		try {
			resp = WebServiceActions.doGet(this.OM2MUrlBase + "applications/" + id, headers);
			if (resp.getStatusCode() == 200)
				appResponse = (Application) JAXBMapper.XMLStringToObject(resp.getResult(), Application.class);
		} catch (HttpResponseException e) {
			LOGGER.error("HttpResponseException - " + e.getStatusCode() + " / " + e.getMessage());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		} catch (ConnectException e) {
			LOGGER.error(e.getMessage() + ". \nThe server '" + this.OM2MUrlBase.substring(7, 21) + "' is unreachable.");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		return appResponse;
	}

	/**
	 * Exist.
	 * 
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean exist(Application obj) {
		HttpGetSimpleResp resp;
		try {
			resp = WebServiceActions.doGet(this.OM2MUrlBase + "applications/" + obj.getAppId(), headers);
			if (resp.getStatusCode() == 200)
				return true;
		} catch (HttpResponseException e) {
			// LOGGER.warn("HttpResponseException - " + e.getStatusCode() + " / " + e.getMessage());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		} catch (ConnectException e) {
			LOGGER.error(e.getMessage() + ". \nThe server '" + this.OM2MUrlBase.substring(7, 21) + "' is unreachable.");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}

}
