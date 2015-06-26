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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.lissi.belilif.om2m.model.NamedReference;
import fr.lissi.belilif.om2m.model.Container;
import fr.lissi.belilif.om2m.model.ContentInstance;
import fr.lissi.belilif.om2m.model.app.Application;
import fr.lissi.belilif.om2m.model.app.ApplicationsResp;
import fr.lissi.belilif.om2m.oao.ApplicationManager;
import fr.lissi.belilif.om2m.oao.ContainerManager;
import fr.lissi.belilif.om2m.oao.ContentInstanceManager;
import fr.lissi.belilif.om2m.oao.Om2mManagersFactorty;
import fr.lissi.belilif.util.jaxb.JAXBMapper;

/**
 * The Class TestMain.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
public class TestMain {

	/** The Constant LOGGER. */
	static final Logger LOGGER = LogManager.getLogger(TestMain.class.getName());

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		// scenarioTest1();

		// testExistApp();

		// testExistContainer();

		 testGetAllApps();

//		genGetAllAppsReponseTest();


	}
	
	public static void genGetAllAppsReponseTest(){
		ArrayList<NamedReference> applicationCollection = new ArrayList<NamedReference>();
		for (int i = 0; i < 4; i++) {
			applicationCollection.add(new NamedReference("ID-" + i, "namedReferenceValue-"+i));
		}
		ApplicationsResp appsResp = new ApplicationsResp("accessRightIDX", applicationCollection, "",
				"subscriptionsReferenceX", "mgmtObjsReferenceX");

		System.out.println(JAXBMapper.objectToXMLString(appsResp));
	}

	/**
	 * Test get all apps.
	 */
	private static void testGetAllApps() {

		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");
		;

		ApplicationManager appManager = (ApplicationManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.APP_MANAGER);

		List<Application> apps = appManager.getAll();
		for (Application application : apps) {
			System.out.println("app >>> "+application.getAppId());
		}

	}

	/**
	 * 
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
	private static void scenarioTest1() {

		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

		// 1- create an application "MyApp1"
		Application myApp1 = new Application();

		String appId = "MyApp6";
		myApp1.setAppId(appId);

		List<String> searchStrings = Arrays.asList(new String[] { "A/Z", "B/Y", "C/X" });
		myApp1.setSearchStrings(searchStrings);

		ApplicationManager appManager = (ApplicationManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.APP_MANAGER);
		appManager.create(myApp1);

		// 2- create a DECRIPTOR container
		ContainerManager containerManager = (ContainerManager) Om2mManagersFactorty
				.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);
		// containerManager.addDecriptor(new OM2MContainer("DESCRIPTOR", myApp1)); // The DECRIPTOR container is defined by
		// default, so we don

		// 3- create a container for data named "MyData1"
		Container container = new Container(appId, "MyData1");
		containerManager.create(container);
		// MyData2
		container = new Container(appId, "MyData2");
		containerManager.create(container);

		// 4- create a data contentInstance
		ContentInstanceManager contentInstanceManager = (ContentInstanceManager) Om2mManagersFactorty
				.getManager(Om2mManagersFactorty.CONTENT_INSTANCE_MANAGER);
		contentInstanceManager.create(new ContentInstance(appId, "MyData1", "60"));

	}

	/**
	 * Test exist app.
	 */
	private static void testExistApp() {

		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

		// create OM2MApplication java object
		Application myApp1 = new Application("MyApp1");

		ApplicationManager appManager = (ApplicationManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.APP_MANAGER);
		System.out.println(appManager.exist(myApp1));

	}

	/**
	 * Test exist container.
	 */
	private static void testExistContainer() {

		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

		ContainerManager containerManager = (ContainerManager) Om2mManagersFactorty
				.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);

		Container container = new Container("MyApp6", "MyData1");
		System.out.println(containerManager.exist(container));

	}

}
