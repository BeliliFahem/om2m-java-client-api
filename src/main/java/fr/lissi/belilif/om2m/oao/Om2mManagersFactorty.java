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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class Om2mManagersFactorty. Factory of app/container/contentInstance layer.
 *
 * @author Belili Fahem - belili.fahem@gmail.com 
 * 
 */
public class Om2mManagersFactorty {

	/** The Constant APP_MANAGER. */
	public static final int APP_MANAGER = 0;
	
	/** The Constant CONTAINER_MANAGER. */
	public static final int CONTAINER_MANAGER = 1;
	
	/** The Constant CONTENT_INSTANCE_MANAGER. */
	public static final int CONTENT_INSTANCE_MANAGER = 2;
	
	/** The host name. */
	private static String hostName;
	
	/** The authorization. */
	private static String authorization;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(Om2mManagersFactorty.class.getName());

	/**
	 * Gets the manager.
	 *
	 * @param manager the manager
	 * @return the manager
	 */
	public static AbstractOm2mManager getManager(int manager) {

		if(hostName == null | hostName.isEmpty() | hostName == null | hostName.isEmpty()){
			LOGGER.error("The host-name/authorization is null or empty");
//			System.err.println("The host-name/authorization is null or empty");
			return null;
		}
		
		switch (manager) {
		case APP_MANAGER:
			return new ApplicationManager(hostName, authorization);

		case CONTAINER_MANAGER:
			return new ContainerManager(hostName, authorization);

		case CONTENT_INSTANCE_MANAGER:
			return new ContentInstanceManager(hostName, authorization);

		default:
			System.err.println("This type of OM2M Manager is not defined.");
			return null;
		}
	}

	/**
	 * Configure.
	 *
	 * @param hostName by default is http://127.0.0.1:8080
	 * @param authorization by default is Basic YWRtaW4vYWRtaW4=
	 */
	public static void configure(String hostName, String authorization) {
		Om2mManagersFactorty.hostName = hostName;
		Om2mManagersFactorty.authorization = authorization;
	}

}
