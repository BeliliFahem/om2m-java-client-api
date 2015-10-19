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
import org.eclipse.om2m.commons.resource.Container;
import org.eclipse.om2m.commons.resource.ContentInstance;
import org.eclipse.om2m.commons.resource.ContentInstances;
import org.eclipse.om2m.commons.resource.Resource;

import fr.lissi.belilif.om2m.exception.UnsupportedMethodException;
import fr.lissi.belilif.om2m.model.Subscription;
import fr.lissi.belilif.om2m.rest.HttpGetSimpleResp;
import fr.lissi.belilif.om2m.rest.WebServiceActions;
import fr.lissi.belilif.util.jaxb.JAXBMapper;

/**
 * The Class ContentInstanceManager.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
public class ContentInstanceManager extends Om2mManager<ContentInstance> {

	/**
	 * Instantiates a new content instance manager.
	 *
	 * @param hostName            the host name
	 * @param authorization            the authorization
	 */
	ContentInstanceManager(String hostName, String authorization) {
		super(hostName, authorization);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#create(java.lang.Object)
	 */
	@Override
	public int create(ContentInstance obj) {

		if (obj.getContainer() == null || obj.getContainer().getApplication().getAppId() == null) {
			LOGGER.error("you must specify the parent resource (parent Container) to create a ContentInstanceManager instance.");
			return -1;
		}

		LOGGER.info("Create ContentInstance '" + obj.getValueAsString() + "' for "
				+ obj.getContainer().getApplication().getAppId() + "/" + obj.getContainer().getId());
		int resp = -1;
		try {
			resp = WebServiceActions.doPost(this.OM2MUrlBase + "applications/" + obj.getContainer().getApplication().getAppId()
					+ "/containers/" + obj.getContainer().getId() + "/contentInstances", obj.getValueAsString(),
					this.headers);

			LOGGER.info("ContentInstance creation in '" + obj.getContainer().getApplication().getAppId() + "/" + obj.getContainer().getId()
					+ "' status : " + resp);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#delete(java.lang.Object)
	 */
	@Override
	public int delete(ContentInstance obj) {
		try {
			throw new UnsupportedMethodException("The methode is unsupported for ContantInstance.");
		} catch (UnsupportedMethodException e) {
			LOGGER.error(e);
			return -1;
		}

		// HttpGetSimpleResp resp;
		// int appResponse = -1;
		// try {
		// resp = WebServiceActions.doDelete(this.OM2MUrlBase + "applications/" + obj.getContainer().getApplication().getAppId()
		// + "/containers/" + obj.getContainer().getId() + "/contentInstances/" + obj.getId(), headers);
		// appResponse = resp.getStatusCode();
		// } catch (HttpResponseException e) {
		// LOGGER.error("HttpResponseException - " + e.getStatusCode() + " / " + e.getMessage());
		// appResponse = e.getStatusCode();
		// } catch (ClientProtocolException e) {
		// e.printStackTrace();
		// LOGGER.error(e.getMessage());
		// } catch (ConnectException e) {
		// LOGGER.error(e.getMessage() + ". \nThe server '" + this.OM2MUrlBase.substring(7, 21) + "' is unreachable.");
		// } catch (IOException e) {
		// LOGGER.error(e.getMessage());
		// }
		// return appResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#subscribe(java.lang.Object, fr.lissi.belilif.om2m.model.Subscription)
	 */
	@Override
	public int subscribe(ContentInstance obj, Subscription subscription) {
		LOGGER.info("Create : \n" + JAXBMapper.objectToXMLString(obj));
		int resp = -1;
		try {
			resp = WebServiceActions.doPost(
					this.OM2MUrlBase + "applications/" + obj.getContainer().getApplication().getAppId() + "/containers/"
							+ JAXBMapper.objectToXMLString(subscription) + "/contentInstances/subscriptions",
					"BODY-" + new String(obj.getContent().getValue()), this.headers); // ID pas content
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#getAll()
	 */
	@Override
	public List<ContentInstance> getAll(Resource res) {
		Container container;
		if (res instanceof Container) {
			container = (Container) res;
		} else {
			LOGGER.error("The parameter of the method 'ContainerManager->getAll' must be an instance of Application.");
			return null;
		}

		HttpGetSimpleResp resp = null;
		try {
			resp = WebServiceActions.doGet(this.OM2MUrlBase + "applications/" + container.getApplication().getAppId() + "/containers/"
					+ container.getId() + "/contentInstances", headers);
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
		ContentInstances contentInstancesResp = (ContentInstances) JAXBMapper.XMLStringToObject(resp.getResult(), ContentInstances.class);
		List<ContentInstance> contentInstances = new ArrayList<ContentInstance>();

		for (ContentInstance contentInstance : contentInstancesResp.getContentInstanceCollection().getContentInstance()) {
			contentInstances.add(contentInstance);
		}

		return contentInstances;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#getFirstN(int)
	 */
	@Override
	public List<ContentInstance> getFirstN(int n) {
		LOGGER.warn("Methode not implemented.");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#searchByKeywords(java.util.List)
	 */
	@Override
	public List<ContentInstance> searchByKeywords(List<String> keywords) {
		LOGGER.warn("Methode not implemented.");
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#get(java.lang.Object)
	 */
	@Override
	public ContentInstance get(ContentInstance obj) {
		try {
			throw new UnsupportedMethodException("The methode is unsupported for ContantInstance.");
		} catch (UnsupportedMethodException e) {
			LOGGER.error(e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#get(java.lang.String)
	 */
	@Override
	public ContentInstance get(String id) {
		try {
			throw new UnsupportedMethodException("The methode is unsupported for ContantInstance.");
		} catch (UnsupportedMethodException e) {
			LOGGER.error(e);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see fr.lissi.belilif.om2m.oao.Om2mManager#exist(java.lang.Object)
	 */
	@Override
	public boolean exist(ContentInstance obj) {
		try {
			throw new UnsupportedMethodException("The methode is unsupported for ContantInstance.");
		} catch (UnsupportedMethodException e) {
			LOGGER.error(e);
			return false;
		}

		// HttpGetSimpleResp resp;
		// try {
		// resp = WebServiceActions.doGet(this.OM2MUrlBase + "applications/" + obj.getContainer().getApplication().getAppId()
		// + "/containers/" + obj.getContainer().getId() + "/contentInstances/" + obj.getId(), headers);
		// if (resp.getStatusCode() == 200)
		// return true;
		// } catch (HttpResponseException e) {
		// // LOGGER.warn("HttpResponseException - " + e.getStatusCode() + " / " + e.getMessage());
		// } catch (ClientProtocolException e) {
		// e.printStackTrace();
		// LOGGER.error(e.getMessage());
		// } catch (ConnectException e) {
		// LOGGER.error(e.getMessage() + ". \nThe server '" + this.OM2MUrlBase.substring(7, 21) + "' is unreachable.");
		// } catch (IOException e) {
		// LOGGER.error(e.getMessage());
		// }
		// return false;
	}
}
