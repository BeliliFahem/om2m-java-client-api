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
import org.eclipse.om2m.commons.resource.Container;
import org.eclipse.om2m.commons.resource.Containers;
import org.eclipse.om2m.commons.resource.ReferenceToNamedResource;
import org.eclipse.om2m.commons.resource.Resource;

import fr.lissi.belilif.om2m.model.Subscription;
import fr.lissi.belilif.om2m.rest.HttpGetSimpleResp;
import fr.lissi.belilif.om2m.rest.WebServiceActions;
import fr.lissi.belilif.util.jaxb.JAXBMapper;

/**
 * The Class ContainerManager.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
public class ContainerManager extends Om2mManager<Container> {

	/**
	 * Instantiates a new container manager.
	 *
	 * @param hostName
	 *            the host name
	 * @param authorization
	 *            the authorization
	 */
	public ContainerManager(String hostName, String authorization) {
		super(hostName, authorization);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#create(java.lang.Object)
	 */
	@Override
	public int create(Container obj) {
		// verifier si le container existe avant d'en créer : laisser ça au utilisateurs
		// Om2mManager om2mManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);
		// OM2MContainerResponse respExist = (OM2MContainerResponse) om2mManager.get(obj);
		// if (respExist != null) {
		// LOGGER.info("Le container existe");
		// } // else System.out.println("Le container n'existe pas.");

		if (obj.getApplication() == null || obj.getApplication().getAppId() == null) {
			LOGGER.error("you must specify the parent resource (parent Application) to manage containers.");
			return -1;
		}

		LOGGER.debug("Container to create in '" + obj.getApplication().getAppId() + "' : \n" + JAXBMapper.objectToXMLString(obj));
		LOGGER.info("Container to create in '" + obj.getApplication().getAppId() + "' : " + obj.getId());
		int resp = -1;
		try {
			resp = WebServiceActions.doPost(this.OM2MUrlBase + "applications/" + obj.getApplication().getAppId() + "/containers",
					JAXBMapper.objectToXMLString(obj), this.headers);
			LOGGER.info("Container '" + obj.getId() + "' creation in '" + obj.getId() + "' status : " + resp);
		} catch (HttpResponseException e) {
			resp = e.getStatusCode();
			LOGGER.error(e.getStatusCode() + " / " + e.getMessage() + ". " + this.OM2MUrlBase + "applications/"
					+ obj.getApplication().getAppId() + "/containers");
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

	/**
	 * Create a DESCRIPTOR container for an Application.
	 *
	 * @param container
	 *            the container
	 * @return the int
	 */
	public int addDecriptor(Container container) {
		container.setId("DESCRIPTOR");
		return create(container);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#delete(java.lang.Object)
	 */
	@Override
	public int delete(Container obj) {
		if (obj.getApplication() == null || obj.getApplication().getAppId() == null) {
			LOGGER.error("you must specify the parent resource (parent Application) to manage containers.");
			return -1;
		}

		HttpGetSimpleResp resp;
		int appResponse = -1;
		try {
			resp = WebServiceActions
					.doDelete(this.OM2MUrlBase + "applications/" + obj.getApplication().getAppId() + "/containers/" + obj.getId(), headers);
			appResponse = resp.getStatusCode();
		} catch (HttpResponseException e) {
			LOGGER.error("HttpResponseException - " + e.getStatusCode() + " / " + e.getMessage());
			appResponse = e.getStatusCode();
		} catch (ClientProtocolException e) {
			LOGGER.error(e);
		} catch (ConnectException e) {
			LOGGER.error(e.getMessage() + ". \nThe server '" + this.OM2MUrlBase.substring(7, 21) + "' is unreachable.");
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return appResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#subscribe(java.lang.Object, fr.lissi.belilif.om2m.model.Subscription)
	 */
	@Override
	public int subscribe(Container obj, Subscription subscription) {
		// TODO subscribe
		LOGGER.warn("Methode not implemented.");
		return -1;
	}

	/**
	 * Gets the all.
	 *
	 * @param res
	 *            instance of Application.
	 * @return All container of the Application 'res'.
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#getAll()
	 */
	@Override
	public List<Container> getAll(Resource res) {
		Application app;
		if (res instanceof Application) {
			app = (Application) res;
		} else {
			LOGGER.error("The parameter of the method 'ContainerManager->getAll' must be an instance of Application.");
			return null;
		}

		HttpGetSimpleResp resp = null;
		try {
			resp = WebServiceActions.doGet(this.OM2MUrlBase + "applications/" + app.getAppId() + "/containers", headers);
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
		Containers containersResp = (Containers) JAXBMapper.XMLStringToObject(resp.getResult(), Containers.class);
		List<Container> apps = new ArrayList<Container>();

		for (ReferenceToNamedResource refToNamedRes : containersResp.getContainerCollection().getNamedReference()) {
			apps.add(new Container(refToNamedRes.getId()));
		}

		return apps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#getFirstN(int)
	 */
	@Override
	public List<Container> getFirstN(int n) {
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
	public List<Container> searchByKeywords(List<String> keywords) {
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
	public Container get(Container obj) {
		// TODO ContainerResponse -> Container
		if (obj.getApplication() == null || obj.getApplication().getAppId() == null) {
			LOGGER.error("you must specify the parent resource (parent Application) to manage containers.");
			return null;
		}
		
		LOGGER.debug(this.OM2MUrlBase + "applications/" + obj.getApplication().getAppId() + "/containers/" + obj.getId());

		Container containerResponse = null;
		HttpGetSimpleResp resp;
		try {
			resp = WebServiceActions
					.doGet(this.OM2MUrlBase + "applications/" + obj.getApplication().getAppId() + "/containers/" + obj.getId(), headers);
			if (resp.getStatusCode() == 200)
				containerResponse = (Container) JAXBMapper.XMLStringToObject(resp.getResult(), Container.class);
		} catch (HttpResponseException e) {
			LOGGER.error("HttpResponseException - " + e.getStatusCode() + " / " + e.getMessage());
		} catch (ClientProtocolException e) {
			LOGGER.error(e.getMessage());
		} catch (ConnectException e) {
			LOGGER.error(e.getMessage() + ". \nThe server '" + this.OM2MUrlBase.substring(7, 21) + "' is unreachable.");
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return containerResponse;
	}

	/**
	 * Exist.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean exist(Container obj) {
		if (obj.getApplication() == null || obj.getApplication().getAppId() == null) {
			LOGGER.error("you must specify the parent resource (parent Application) to manage containers.");
			return false;
		}

		HttpGetSimpleResp resp;
		try {
			resp = WebServiceActions
					.doGet(this.OM2MUrlBase + "applications/" + obj.getApplication().getAppId() + "/containers/" + obj.getId(), headers);
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

	/*
	 * public boolean exist(Container obj) { HttpGetSimpleResp resp; try { resp = WebServiceActions .doGet(this.OM2MUrlBase +
	 * "applications/" + obj.getApplication().getAppId() + "/containers/" + obj.getId(), headers); if (resp.getStatusCode() == 200) return
	 * true; } catch (HttpResponseException e) { // LOGGER.warn("HttpResponseException - " + e.getStatusCode() + " / " + e.getMessage()); }
	 * catch (ClientProtocolException e) { e.printStackTrace(); LOGGER.error(e.getMessage()); } catch (ConnectException e) {
	 * LOGGER.error(e.getMessage() + ". \nThe server '" + this.OM2MUrlBase.substring(7, 21) + "' is unreachable."); } catch (IOException e)
	 * { LOGGER.error(e.getMessage()); } return false; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#get(java.lang.String)
	 */
	@Override
	public Container get(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
