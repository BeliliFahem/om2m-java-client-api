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

import fr.lissi.belilif.om2m.model.OM2MContainer;
import fr.lissi.belilif.om2m.model.OM2MContainerResponse;
import fr.lissi.belilif.om2m.model.OM2MSubscription;
import fr.lissi.belilif.om2m.rest.WebServiceActions;
import fr.lissi.belilif.util.jaxb.JAXBMapper;



/**
 * The Class ContainerManager.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
public class ContainerManager extends AbstractOm2mManager<OM2MContainer, OM2MContainerResponse> {

	/**
	 * Instantiates a new container manager.
	 *
	 * @param hostName the host name
	 * @param authorization the authorization
	 */
	public ContainerManager(String hostName, String authorization) {
		super(hostName, authorization);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#create(java.lang.Object)
	 */
	@Override
	public int create(OM2MContainer obj) {
		// verifier si le container existe avant d'en créer : laisser ça au utilisateur
//		AbstractOm2mManager om2mManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);
//		OM2MContainerResponse respExist = (OM2MContainerResponse) om2mManager.get(obj);
//		if (respExist != null) {
//			LOGGER.info("Le container existe");
//		} // else System.out.println("Le container n'existe pas.");

		LOGGER.debug("Container to create in '"+obj.getAppId()+"' : \n" + JAXBMapper.objectToXMLString(obj));
		LOGGER.info("Container to create in '"+obj.getAppId()+"' : " + obj.getId());
		int resp = -1;
		try {
			resp = WebServiceActions.doPost(this.OM2MUrlBase + "applications/" + obj.getAppId() + "/containers",
					JAXBMapper.objectToXMLString(obj), this.headers);
			LOGGER.info("Container '"+obj.getId()+"' creation in '"+obj.getAppId()+"' status : "+resp);
		} catch (HttpResponseException e) {
			resp = e.getStatusCode();
			LOGGER.error(e.getStatusCode() + " / " + e.getMessage()+". "+this.OM2MUrlBase + "applications/" + obj.getAppId() + "/containers");
		} catch (ClientProtocolException e) {
			// TODO ClientProtocolException
			e.printStackTrace();
		} catch (IOException e) {
			// TODO IOException
			e.printStackTrace();
		}
		return resp;
	}

	/**
	 * Create a DESCRIPTOR container for an Application.
	 *
	 * @param container the container
	 * @return the int
	 */
	public int addDecriptor(OM2MContainer container) {
		container.setId("DESCRIPTOR");	
		return create(container);
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#delete(java.lang.Object)
	 */
	@Override
	public int delete(OM2MContainer obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#subscribe(java.lang.Object, fr.lissi.belilif.om2m.model.OM2MSubscription)
	 */
	@Override
	public int subscribe(OM2MContainer obj, OM2MSubscription subscription) {
		// contentInstances/subscriptions
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#getAll()
	 */
	@Override
	public List<OM2MContainerResponse> getAll() {
		// TODO Auto-generated method stub
		return null;
	} 

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#getFirstN(int)
	 */
	@Override
	public List<OM2MContainerResponse> getFirstN(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#searchByKeywords(java.util.List)
	 */
	@Override
	public List<OM2MContainerResponse> searchByKeywords(List<String> keywords) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.AbstractOm2mManager#get(java.lang.Object)
	 */
	@Override
	public OM2MContainerResponse get(OM2MContainer obj) {
		OM2MContainerResponse containerResponse = null;
		String resp = null;
		try {
			resp = WebServiceActions
					.doGet(this.OM2MUrlBase + "applications/" + obj.getAppId() + "/containers/" + obj.getId(), headers);
			if (resp.equals("200")) containerResponse = new OM2MContainerResponse();
		} catch (HttpResponseException e) {
			resp = e.getStatusCode() + "";
			System.out.println(e.getStatusCode() + " / " + e.getMessage());
		} catch (ClientProtocolException e) {
			// TODO ClientProtocolException
			e.printStackTrace();
		} catch (IOException e) {
			// TODO IOException
			e.printStackTrace();
		}
		return containerResponse;
	}
	
	/**
	 * Exist.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	public boolean exist(OM2MContainer obj) {
		boolean containerResponse = false;
		String resp = null;
		try {
			resp = WebServiceActions
					.doGet(this.OM2MUrlBase + "applications/" + obj.getAppId() + "/containers/" + obj.getId(), headers);
			if (resp.equals("200")) containerResponse = true;
		} catch (HttpResponseException e) {
			resp = e.getStatusCode() + "";
			System.out.println(e.getStatusCode() + " / " + e.getMessage());
		} catch (ClientProtocolException e) {
			// TODO ClientProtocolException
			e.printStackTrace();
		} catch (IOException e) {
			// TODO IOException
			e.printStackTrace();
		}
		return containerResponse;
	}
}
