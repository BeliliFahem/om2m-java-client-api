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

import java.lang.reflect.InvocationTargetException;

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
	public static final Class<ApplicationManager> APP_MANAGER = ApplicationManager.class;

	/** The Constant CONTAINER_MANAGER. */
	public static final Class<ContainerManager> CONTAINER_MANAGER = ContainerManager.class;

	/** The Constant CONTENT_INSTANCE_MANAGER. */
	public static final Class<ContentInstanceManager> CONTENT_INSTANCE_MANAGER = ContentInstanceManager.class;

	/** The host name. */
	private static String hostName;

	/** The authorization. */
	private static String authorization;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(Om2mManagersFactorty.class.getName());

	/**
	 * Gets the manager.
	 *
	 * @param manager
	 *            the manager
	 * @return the manager
	 */
	public static <T> T getManager(Class<T> managerClass) {

		if (hostName == null | hostName.isEmpty() | hostName == null | hostName.isEmpty()) {
			LOGGER.error("The host-name/authorization is null or empty");
			return null;
		}

		try {
			return managerClass.getDeclaredConstructor(String.class, String.class).newInstance(hostName, authorization);
		} catch (InstantiationException e) {
			LOGGER.error(e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e);
		} catch (IllegalArgumentException e) {
			LOGGER.error(e);
		} catch (InvocationTargetException e) {
			LOGGER.error(e);
		} catch (NoSuchMethodException e) {
			LOGGER.error(e);
		} catch (SecurityException e) {
			LOGGER.error(e);
		}

		return null;
	}

	/**
	 * Configure.
	 *
	 * @param hostName
	 *            by default is http://127.0.0.1:8080
	 * @param authorization
	 *            by default is Basic YWRtaW4vYWRtaW4=
	 */
	public static void configure(String hostName, String authorization) {
		Om2mManagersFactorty.hostName = hostName;
		Om2mManagersFactorty.authorization = authorization;
	}

}
