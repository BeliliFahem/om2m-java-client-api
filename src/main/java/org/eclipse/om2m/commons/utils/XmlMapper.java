/*******************************************************************************
 * Copyright (c) 2013-2015 LAAS-CNRS (www.laas.fr)
 * 7 Colonel Roche 31077 Toulouse - France
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Thierry Monteil (Project co-founder) - Management and initial specification,
 *         conception and documentation.
 *     Mahdi Ben Alaya (Project co-founder) - Management and initial specification,
 *         conception, implementation, test and documentation.
 *     Christophe Chassot - Management and initial specification.
 *     Khalil Drira - Management and initial specification.
 *     Yassine Banouar - Initial specification, conception, implementation, test
 *         and documentation.
 ******************************************************************************/
package org.eclipse.om2m.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Converts resource XML representation to resource Java Object and vice versa.
 *
 * @author <ul>
 *         <li>Mahdi Ben Alaya < ben.alaya@laas.fr > < benalaya.mahdi@gmail.com ></li>
 *         <li>Yassine Banouar < ybanouar@laas.fr > < yassine.banouar@gmail.com ></li>
 *         </ul>
 */
public class XmlMapper {
    /** XmlMapper Logger */
    private static Log LOGGER = LogFactory.getLog(XmlMapper.class);
    /** XmlMapper Singleton */
    private static XmlMapper xmlMapper = new XmlMapper();
    /** Entry point to the JAXB API*/
    private JAXBContext ctx;
    /** Resource package name used for JAXBContext instantiation*/
    private String resourcePackage = "org.eclipse.om2m.commons.resource";

    /** Constructor.
     * Creates new Instance of JAXBContext based on package.
     */
    private XmlMapper(){
        try {
            ctx = JAXBContext.newInstance(resourcePackage);
        } catch (JAXBException e) {
            LOGGER.error("Create JAXBContext error!", e);
        }
    }

    /** Gets XmlMapper instance*/
    public static XmlMapper getInstance(){
        return xmlMapper;
    }

    /**
     * Converts a resource Java object into resource XML representation.
     * @param object - resource Java object
     * @return resource XML representation
     */
    public String objectToXml(Object object) {
        try {
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            OutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(object, outputStream);

            return outputStream.toString();
        } catch (JAXBException e) {
            LOGGER.error("JAXB marshalling error!", e);
        }
        return null;
    }

    /**
     * Converts a resource XML representation data into resource Java object.
     * @param representation - resource XML representation
     * @return resource Java object
     */
    public Object xmlToObject(String representation) {
        StringReader stringReader = new StringReader(representation);
        try {
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            return unmarshaller.unmarshal(stringReader);
        } catch (JAXBException e) {
            LOGGER.debug("JAXB unmarshalling error!",e);
        } 
        return null;
    }
}
