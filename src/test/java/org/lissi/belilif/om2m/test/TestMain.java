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
package org.lissi.belilif.om2m.test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.om2m.commons.resource.Application;
import org.eclipse.om2m.commons.resource.Base64Binary;
import org.eclipse.om2m.commons.resource.Container;
import org.eclipse.om2m.commons.resource.ContentInstance;
import org.eclipse.om2m.commons.resource.Resource;
import org.eclipse.om2m.commons.resource.SearchStrings;

import fr.lissi.belilif.om2m.oao.Om2mManager;
import fr.lissi.belilif.om2m.oao.ApplicationManager;
import fr.lissi.belilif.om2m.oao.ContainerManager;
import fr.lissi.belilif.om2m.oao.ContentInstanceManager;
import fr.lissi.belilif.om2m.oao.Om2mManagersFactorty;

/**
 * The Class TestMain.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
public class TestMain {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(TestMain.class.getName());

	private static final int APP_ID_TEST = 3;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		//scenarioCreationTest1(APP_ID_TEST);

		// isServerReachable();

		getAllContainer(APP_ID_TEST);

		/// testDeleteApp();

		// testGetApp();

		// for (int i = 0; i < 20; i++) {
		// scenarioTest1(i);
		// }

		//// testExistApp();

		//testExistContainer();

		//testGetAllApps();

		// genGetAllAppsReponseTest();

		// getContentInstenceTest(APP_ID_TEST);

	}

	private static void createApp(int i) {
		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

		// 1- create an application "MyApp1"
		Application myApp = new Application("MyApp" + i);

		SearchStrings searchStrings = new SearchStrings();
		// List<String> searchStrings = Arrays.asList(new String[] { "A/Z", "B/Y", "C/X" });
		myApp.setSearchStrings(searchStrings);

		Om2mManager<Application> om2mManager = (Om2mManager<Application>) Om2mManagersFactorty
				.getManager(Om2mManagersFactorty.APP_MANAGER);
		om2mManager.create(myApp);

	}

	private static void isServerReachable() {
		Socket socket = null;
		boolean reachable = false;
		try {
			socket = new Socket("127.0.0.1", 8080);
			reachable = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
				}
		}
		LOGGER.info("is server reachable ? :" + reachable);

	}

	private static void getAllContainer(int appId) {
		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

		ContainerManager containerManager = (ContainerManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);

		List<Container> containers = containerManager.getAll(new Application("MyApp" + appId));
		for (Container container : containers) {
			System.out.println("container >>> " + container.getId());
		}

	}

	private static void testDeleteApp() {

		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

		Om2mManager<Application> om2mManager = (Om2mManager<Application>) Om2mManagersFactorty
				.getManager(Om2mManagersFactorty.APP_MANAGER);

		Application myApp = new Application("MyApp3");

		if (om2mManager.exist(myApp))
			LOGGER.info(om2mManager.delete(myApp));
		else {
			LOGGER.info("The Application " + myApp.getAppId() + " does not exist.");
		}

	}

	private static void getContentInstenceTest(int appID) {
		System.out.println(new String(Base64.getDecoder().decode("NjA=")));
		System.out.println(String.valueOf(Base64.getDecoder().decode("NjA=")));

		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

		String appId = "MyApp" + appID;

		ContentInstanceManager contentInstanceManager = (ContentInstanceManager) Om2mManagersFactorty
				.getManager(Om2mManagersFactorty.CONTENT_INSTANCE_MANAGER);
		contentInstanceManager.getAll(null);
	}

	// public static void genGetAllAppsReponseTest() {
	// ArrayList<NamedReference> applicationCollection = new ArrayList<NamedReference>();
	// for (int i = 0; i < 4; i++) {
	// applicationCollection.add(new NamedReference("ID-" + i, "namedReferenceValue-" + i));
	// }
	// ApplicationsResp appsResp = new ApplicationsResp("accessRightIDX", applicationCollection, "", "subscriptionsReferenceX",
	// "mgmtObjsReferenceX");
	//
	// System.out.println(JAXBMapper.objectToXMLString(appsResp));
	// }

	/**
	 * Test get all apps.
	 */
	private static void testGetAllApps() {

		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

		Om2mManager<Application> om2mManager = (Om2mManager<Application>) Om2mManagersFactorty
				.getManager(Om2mManagersFactorty.APP_MANAGER);

		List<Application> apps = om2mManager.getAll(null);
		for (Application application : apps) {
			System.out.println("app >>> " + application.getAppId());
		}

	}

	private static void testGetApp() {

		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

		Om2mManager<Application> om2mManager = (Om2mManager<Application>) Om2mManagersFactorty
				.getManager(Om2mManagersFactorty.APP_MANAGER);

		LOGGER.info(om2mManager.get("MyApp1"));

	}

	/**
	 * <b>This example do the the following tasks :</b>
	 * <ol>
	 * <li>create an application "MyApp1"
	 * <li>create a DESCRIPTOR container
	 * <li>create a container for data named "MyData1"
	 * <li>create a data contentInstance
	 * <li>subscribe to MyApp1 data
	 * <ul>
	 * <li>do the subscription
	 * <li>start the a monitor server sample (by OM2M)
	 * <li>add data to see the automatic notification in the monitor server sample
	 * </ul>
	 * </ol>
	 * 
	 *
	 * @author Belili Fahem - belili.fahem@gmail.com
	 */
	private static void scenarioCreationTest1(int appIdNumber) {

		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

		// 1- create an application "MyApp1"
		Application myApp = new Application("MyApp" + appIdNumber);

		SearchStrings searchStrings = new SearchStrings();
		// List<String> searchStrings = Arrays.asList(new String[] { "A/Z", "B/Y", "C/X" });
		myApp.setSearchStrings(searchStrings);

		Om2mManager om2mManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.APP_MANAGER);
		om2mManager.create(myApp);

		// 2- create a DECRIPTOR container
		om2mManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);
		// containerManager.addDecriptor(new OM2MContainer("DESCRIPTOR", myApp)); // The DECRIPTOR container is defined by
		// default, so we don

		// 3- create a container for data named "MyData1"
		Container container = new Container("MyDataId1");
		container.setApplication(myApp);
		om2mManager.create(container);
		// MyData2
		container = new Container("MyDataId2");
		container.setApplication(myApp);
		om2mManager.create(container);

		// 4- create a data contentInstance
		ContentInstanceManager contentInstanceManager = (ContentInstanceManager) Om2mManagersFactorty
				.getManager(Om2mManagersFactorty.CONTENT_INSTANCE_MANAGER);
		ContentInstance contentInstance = new ContentInstance("contentInstance1");
		Base64Binary base64Binary = new Base64Binary();
		base64Binary.setContentType("String");
		base64Binary.setValue((appIdNumber + "DATA").toString().getBytes());
		contentInstance.setContent(base64Binary);
		contentInstance.setContainer(container);
		contentInstanceManager.create(contentInstance);

		contentInstanceManager.exist(contentInstance);
		contentInstanceManager.get(contentInstance);
		contentInstanceManager.getFirstN(5);
	}

	/**
	 * Test exist app.
	 */
	private static void testExistApp() {

		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

		// create OM2MApplication java object
		Application myApp1 = new Application("MyApp00");

		ApplicationManager appManager = (ApplicationManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.APP_MANAGER);
		LOGGER.info(appManager.exist(myApp1));

	}

	/**
	 * Test exist container.
	 */
	private static void testExistContainer() {

		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

		ContainerManager containerManager = (ContainerManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);

		Container container = new Container("MyDataId1");
		container.setApplication(new Application("MyApp6"));
		System.out.println(containerManager.exist(container));

	}

}
