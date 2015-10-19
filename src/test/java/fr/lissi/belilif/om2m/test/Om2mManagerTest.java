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
package fr.lissi.belilif.om2m.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.om2m.commons.resource.Application;
import org.eclipse.om2m.commons.resource.Base64Binary;
import org.eclipse.om2m.commons.resource.Container;
import org.eclipse.om2m.commons.resource.ContentInstance;
import org.eclipse.om2m.commons.resource.SearchStrings;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.lissi.belilif.om2m.oao.ContentInstanceManager;
import fr.lissi.belilif.om2m.oao.Om2mManager;
import fr.lissi.belilif.om2m.oao.Om2mManagersFactorty;

/**
 * The Class Om2mManagerTest.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Om2mManagerTest {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(Om2mManagersFactorty.class.getName());

	/** The Constant APP_ID_TEST. */
	private static final int APP_ID_TEST = 205;

	/**
	 * Before test.
	 */
	@Before
	public void beforeTest() {
		Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");
		System.out.println();
	}

	/**
	 * After test.
	 */
	public void afterTest() {
		System.out.println();
	}

	/**
	 * Test1 application create.
	 */
	@Test
	public void test1ApplicationCreate() {
		LOGGER.info("\t***************** begin test *****************");
		Application myApp = new Application("MyApp" + APP_ID_TEST);

		SearchStrings searchStrings = new SearchStrings();
		searchStrings.getSearchString().add("keyWord1");
		searchStrings.getSearchString().add("keyWord2");
		myApp.setSearchStrings(new SearchStrings());

		Om2mManager<Application> om2mManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.APP_MANAGER);
		int httpCodeResp = om2mManager.create(myApp);

		Assert.assertEquals(201, httpCodeResp);
		Assert.assertTrue("should be true", om2mManager.exist(myApp));

		// create many applications
		// for (int i = 1; i < 6; i++) {
		// myApp = new Application("MyApp" + (APP_ID_TEST + i));
		// httpCodeResp = om2mManager.create(myApp);
		//
		// Assert.assertEquals(201, httpCodeResp);
		// Assert.assertTrue("should be true", om2mManager.exist(myApp));
		// }
	}

	/**
	 * Test2 application get.
	 */
	@Test
	public void test2ApplicationGet() {
		LOGGER.info("\t***************** begin test *****************");
		Om2mManager<Application> om2mManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.APP_MANAGER);

		Application app = om2mManager.get("MyApp" + APP_ID_TEST);

		LOGGER.info("MyApp" + APP_ID_TEST + " : " + app.toString());

		Assert.assertNotNull("should not be null", app);
		Assert.assertNotNull("should not be null", app.getAppId());
	}

	@Test
	public void test2ApplicationGetAll() {
		LOGGER.info("\t***************** begin test *****************");
		Om2mManager<Application> om2mManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.APP_MANAGER);

		List<Application> apps = om2mManager.getAll(null);

		LOGGER.info("is the contentInstance list null ? : " + (apps == null));
		LOGGER.info("is the contentInstance list empty ? : " + apps.isEmpty());

		Assert.assertNotEquals(null, apps);
		Assert.assertFalse("Must be false (is the contentInstance list empty ?)", apps.isEmpty());

		LOGGER.info("List of application >");
		for (Application application : apps) {
			System.out.println("app >>> " + application.getAppId());
		}
	}

	/**
	 * Test3 container create.
	 */
	@Test
	public void test3ContainerCreate() {
		LOGGER.info("\t***************** begin test *****************");

		Container container = new Container("MyContainerId1");
		// Specify the application in which is contained the "container".
		Application myApp = new Application("MyApp" + APP_ID_TEST);
		container.setApplication(myApp);

		Om2mManager<Container> containerManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);
		int httpCodeResp = containerManager.create(container);

		LOGGER.info("httpCodeResp : " + httpCodeResp);
		LOGGER.info("exist(container) : " + containerManager.exist(container));

		Assert.assertEquals(201, httpCodeResp);
		Assert.assertTrue("should be true", (containerManager.exist(container) == true));

		// ContainerId2
		container = new Container("MyContainerId2");
		container.setApplication(myApp);
		httpCodeResp = containerManager.create(container);

		Assert.assertEquals(201, httpCodeResp);
		Assert.assertTrue("should be true", (containerManager.exist(container) == true));
	}

	@Test
	public void test4ContainerGet() {
		LOGGER.info("\t***************** begin test *****************");

		Container container = new Container("MyContainerId1");
		// Specify the application in which is contained the "container".
		Application myApp = new Application("MyApp" + APP_ID_TEST);
		container.setApplication(myApp);

		Om2mManager<Container> containerManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);
		Container containerResp = containerManager.get(container);

		LOGGER.info("Container '" + container.getId() + "' : " + containerResp);

		Assert.assertNotNull("should not be null", containerResp);
		Assert.assertNotNull("should not be null", containerResp.getId());
	}

	@Test
	public void test4ContainerGetAll() {
		LOGGER.info("\t***************** begin test *****************");

		Om2mManager<Container> containerManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);
		List<Container> containers = containerManager.getAll(new Application("MyApp" + APP_ID_TEST));

		LOGGER.info("is the container list null ? : " + (containers == null));
		LOGGER.info("is the container list empty ? : " + containers.isEmpty());

		Assert.assertNotEquals(null, containers);
		Assert.assertFalse("Must be false (is the contentInstance list empty ?)", containers.isEmpty());

		LOGGER.info("List of contentInstance >");
		for (Container container : containers) {
			System.out.println("container> " + container.getId());
		}
	}

	@Test
	public void test5ContainerDelete() {
		LOGGER.info("\t***************** begin test *****************");

		Container container = new Container("MyContainerId1");
		// Specify the application in which is contained the "container".
		Application myApp = new Application("MyApp" + APP_ID_TEST);
		container.setApplication(myApp);

		Om2mManager<Container> om2mManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);
		int httpCodeResp = om2mManager.delete(container);

		LOGGER.info("httpCodeResp : " + httpCodeResp);
		LOGGER.info("exist after deleting : " + om2mManager.exist(container));

		Assert.assertEquals(204, httpCodeResp);
		Assert.assertTrue("should be true", (om2mManager.exist(container) == false));
	}

	/**
	 * Test4 content instance create.
	 */
	@Test
	public void test6ContentInstanceCreate() {
		LOGGER.info("\t***************** begin test *****************");

		// parent container of the future contentInstance
		Container container = new Container("MyContainerId2");
		// Parent application of the container
		Application myApp = new Application("MyApp" + APP_ID_TEST);
		container.setApplication(myApp);

		ContentInstanceManager contentInstanceManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTENT_INSTANCE_MANAGER);

		for (int i = 0; i < 10; i++) {
			ContentInstance contentInstance = new ContentInstance("contentInstanceId" + i);
			contentInstance.setContainer(container);
			Base64Binary base64Binary = new Base64Binary();
			base64Binary.setContentType("String");

			base64Binary.setValue(("dataInstance_" + container.getId() + "_" + myApp.getAppId() + "-" + i).toString().getBytes());
			contentInstance.setContent(base64Binary);

			int httpCodeResp = contentInstanceManager.create(contentInstance);

			LOGGER.info("httpCodeResp : " + httpCodeResp);
			// LOGGER.info("exist(container) : " + contentInstanceManager.exist(contentInstance));

			Assert.assertEquals(201, httpCodeResp);
			// Assert.assertTrue("should be true", (contentInstanceManager.exist(contentInstance) == true));

		}

	}

	/**
	 * Test5 content instance get all.
	 */
	@Test
	public void test7ContentInstanceGetAll() {
		LOGGER.info("\t***************** begin test *****************");

		// parent container of the future contentInstance
		Container container = new Container("MyContainerId2");
		// Parent application of the container
		Application myApp = new Application("MyApp" + APP_ID_TEST);
		container.setApplication(myApp);

		ContentInstanceManager contentInstanceManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTENT_INSTANCE_MANAGER);

		List<ContentInstance> contentInstances = contentInstanceManager.getAll(container);

		LOGGER.info("is the contentInstance list null ? : " + (contentInstances == null));
		LOGGER.info("is the contentInstance list empty ? : " + contentInstances.isEmpty());

		Assert.assertNotEquals(null, contentInstances);
		Assert.assertFalse("Must be false (is the contentInstance list empty ?)", contentInstances.isEmpty());

		LOGGER.info("List of contentInstance >");
		for (ContentInstance ci : contentInstances) {
			System.out.println("CI> " + ci.getValueAsString() + " // " + ci.toString());
		}
	}

	@Test
	public void test8ApplicationDelete() {
		LOGGER.info("\t***************** begin test *****************");
		Om2mManager<Application> om2mManager = Om2mManagersFactorty.getManager(Om2mManagersFactorty.APP_MANAGER);

		Application myApp = new Application("MyApp" + APP_ID_TEST);

		int httpCodeResp = om2mManager.delete(myApp);

		LOGGER.info("httpCodeResp : " + httpCodeResp);
		LOGGER.info("exist after deleting : " + om2mManager.exist(myApp));

		Assert.assertEquals(204, httpCodeResp);
		Assert.assertTrue("should be true", (om2mManager.exist(myApp) == false));
	}

}
