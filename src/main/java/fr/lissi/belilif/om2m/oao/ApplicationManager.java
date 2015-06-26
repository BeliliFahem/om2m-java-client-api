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
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;

import fr.lissi.belilif.om2m.model.NamedReference;
import fr.lissi.belilif.om2m.model.Subscription;
import fr.lissi.belilif.om2m.model.app.Application;
import fr.lissi.belilif.om2m.model.app.ApplicationGeneric;
import fr.lissi.belilif.om2m.model.app.ApplicationResp;
import fr.lissi.belilif.om2m.model.app.ApplicationsResp;
import fr.lissi.belilif.om2m.rest.WebServiceActions;
import fr.lissi.belilif.util.jaxb.JAXBMapper;

/**
 * The Class ApplicationManager.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
public class ApplicationManager extends AbstractOm2mManager<Application, ApplicationGeneric> {

	/**
	 * Instantiates a new application manager.
	 *
	 * @param OM2MUrlBase
	 *            the OM2M url base
	 * @param authorization
	 *            the authorization
	 */
	public ApplicationManager(String OM2MUrlBase, String authorization) {
		super(OM2MUrlBase, authorization);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#create(java.lang.Object)
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
			// TODO elevate exception
			resp = e.getStatusCode();
			LOGGER.error("HttpResponseException - " + e.getStatusCode() + " / " + e.getMessage() + "\n"
					+ "Unable to create the application '" + obj.getAppId() + "' with " + this.OM2MUrlBase + "applications");
		} catch (ClientProtocolException e) {
			LOGGER.error("ClientProtocolException - " + e.getMessage());
		} catch (IOException e) {
			LOGGER.error("IOException - " + e.getMessage());
		}
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#delete(java.lang.Object)
	 */
	@Override
	public int delete(Application obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#subscribe(java.lang.Object,
	 * fr.lissi.belilif.om2m.model.Subscription)
	 */
	@Override
	public int subscribe(Application obj, Subscription subscription) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#getAll()
	 */
	@Override
	public List<Application> getAll() {
		// TODO manage exceptions
		String resp = null;
		try {
			resp = WebServiceActions.doGet(this.OM2MUrlBase + "applications", headers);
		} catch (HttpResponseException e) {
			// TODO manage the exception
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO manage the exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO manage the exception
			e.printStackTrace();
		}

		// xml to object
		ApplicationsResp appsResp = (ApplicationsResp) JAXBMapper.XMLStringToObject(resp, ApplicationsResp.class);
		List<Application> apps = new ArrayList<Application>();
		for (NamedReference namedRef : appsResp.getApplicationCollection()) {
			apps.add(new Application(namedRef.getId()));
		}

		return apps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#getFirstN(int)
	 */
	@Override
	public List<ApplicationGeneric> getFirstN(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#searchByKeywords(java.util.List)
	 */
	@Override
	public List<ApplicationGeneric> searchByKeywords(List<String> keywords) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#get(java.lang.Object)
	 */
	@Override
	public ApplicationResp get(Application obj) {
		ApplicationResp appResponse = null;
		String resp = null;
		try {
			resp = WebServiceActions.doGet(this.OM2MUrlBase + "applications/" + obj.getAppId(), headers);
			if (resp.equals("200"))
				appResponse = new ApplicationResp();
		} catch (HttpResponseException e) {
			resp = e.getStatusCode() + "";
			LOGGER.error("HttpResponseException - " + e.getStatusCode() + " / " + e.getMessage());
		} catch (ClientProtocolException e) {
			// TODO ClientProtocolException
			e.printStackTrace();
		} catch (IOException e) {
			// TODO IOException
			e.printStackTrace();
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
	public boolean exist(Application obj) {
		boolean appResponse = false;
		String resp = null;
		try {
			resp = WebServiceActions.doGet(this.OM2MUrlBase + "applications/" + obj.getAppId(), headers);
			if (resp.equals("200"))
				appResponse = true;
		} catch (HttpResponseException e) {
			resp = e.getStatusCode() + "";
			LOGGER.error("HttpResponseException - " + e.getStatusCode() + " / " + e.getMessage());
		} catch (ClientProtocolException e) {
			// TODO ClientProtocolException
			e.printStackTrace();
		} catch (IOException e) {
			// TODO IOException
			e.printStackTrace();
		}
		return appResponse;
	}

}
