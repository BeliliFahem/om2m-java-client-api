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


package fr.lissi.belilif.util.jaxb;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



/**
 * Do the mapping between the OM2M XML structure and Java object .
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 * 
 */
public class JAXBMapper {

	/**
	 * XML file to object.
	 *
	 * @param filePath the file path
	 * @param clazz the clazz
	 * @return the object
	 */
	public static Object XMLFileToObject(String filePath, Class clazz) {
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller un = context.createUnmarshaller();
			Object obj = un.unmarshal(new File(filePath));
			return obj;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Object to xml file.
	 *
	 * @param filePath the file path
	 * @param obj the obj
	 */
	public static void objectToXMLFile(String filePath, Object obj) {
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller m = context.createMarshaller();
			// for pretty-print XML in JAXB
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out for debugging
			// m.marshal(emp, System.out);

			// Write to File
			m.marshal(obj, new File(filePath));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Object to xml string.
	 *
	 * @param obj the obj
	 * @return the string
	 */
	public static String objectToXMLString(Object obj) {
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller m = context.createMarshaller();
			// for pretty-print XML in JAXB
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// Write to to String varible
			StringWriter sw = new StringWriter();
			m.marshal(obj, sw);
			return sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * XML string to object.
	 *
	 * @param XMLCode the XML code
	 * @param clazz the clazz
	 * @return the object
	 */
	public static Object XMLStringToObject(String XMLCode, Class clazz) {
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller un = context.createUnmarshaller();
					
			StringReader sr = new StringReader(XMLCode);
			
			Object obj = un.unmarshal(sr);
			return obj;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

}
