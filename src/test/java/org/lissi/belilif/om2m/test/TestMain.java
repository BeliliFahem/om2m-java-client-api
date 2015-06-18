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

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.lissi.belilif.om2m.model.OM2MApplication;
import fr.lissi.belilif.om2m.model.OM2MApplicationResponse;
import fr.lissi.belilif.om2m.model.OM2MContainer;
import fr.lissi.belilif.om2m.model.OM2MContentInstance;
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
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		scenarioTest1();

//		testExistApp();
		
//		testExistContainer();

	}

	/**
	 * <pre>
	 * This example do the the following tasks :
	 * 1- create an application "MyApp1"
	 * 2- create a DESCRIPTOR container
	 * 3- create a container for data named "MyData1"
	 * 4- create a data contentInstance
	 * 5- subscribe to MyApp1 data
	 * -- do the subscription
	 * -- start the a monitor server sample (by OM2M)
	 * -- add data to see the automatic notification in the monitor server sample
	 * 
	 * </pre>.
	 *
	 * @author Belili Fahem - belili.fahem@gmail.com
	 */
	private static void scenarioTest1() {

		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

		// 1- create an application "MyApp1"
		OM2MApplication myApp1 = new OM2MApplication();

		myApp1.setAppId("MyApp5");

		List<String> searchStrings = Arrays.asList(new String[] { "A/Z", "B/Y", "C/X" });
		myApp1.setSearchStrings(searchStrings);

		ApplicationManager appManager = (ApplicationManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.APP_MANAGER);
		appManager.create(myApp1);

		// 2- create a DECRIPTOR container
		ContainerManager containerManager = (ContainerManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);
		//		containerManager.addDecriptor(new OM2MContainer("DESCRIPTOR", myApp1)); // The DECRIPTOR container is defined by default, so we don

		// 3- create a container for data named "MyData1"
		OM2MContainer container = new OM2MContainer("MyData1", myApp1);
		containerManager.create(container);
		// MyData2
		container = new OM2MContainer("MyData2", myApp1);
		containerManager.create(container);

		// 4- create a data contentInstance
		ContentInstanceManager contentInstanceManager = (ContentInstanceManager) Om2mManagersFactorty
				.getManager(Om2mManagersFactorty.CONTENT_INSTANCE_MANAGER);
		contentInstanceManager.create(new OM2MContentInstance(container, "50"));

	}

	/**
	 * Test exist app.
	 */
	private static void testExistApp() {

		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW4vYWRtaW4=");

		// 1- create an application "MyApp1"
		OM2MApplication myApp1 = new OM2MApplication();

		myApp1.setAppId("patient00005");

		ApplicationManager appManager = (ApplicationManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.APP_MANAGER);
		System.out.println( appManager.exist(myApp1));

	}

	/**
	 * Test exist container.
	 */
	private static void testExistContainer() {
		
		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW4vYWRtaW4=");
		
		ContainerManager containerManager = (ContainerManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);
		
		OM2MContainer containerSPO2 = new OM2MContainer("spo2", new OM2MApplication("patient00004"));
		System.out.println( containerManager.exist(containerSPO2) );
		
	}

}
