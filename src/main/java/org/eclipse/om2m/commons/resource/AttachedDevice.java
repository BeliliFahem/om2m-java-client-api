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
 *     Guillaume Garzone - Conception, implementation, test and documentation.
 *     Francois Aissaoui - Conception, implementation, test and documentation.
 ******************************************************************************/

// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.25 at 05:56:27 PM CET
//


package org.eclipse.om2m.commons.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;



/**
 * <p>Java class for AttachedDevice complex type.
 *
 * <p>AttachedDevice resource is used to represent each M2M D' device that is attached to a M2M Gateway. The
 * resource lives only in the NSCL and it shall reside under the {@link AttachedDevices} resource of the
 * corresponding M2M Gateway.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AttachedDevice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://uri.etsi.org/m2m}accessRightID" minOccurs="0"/>
 *         &lt;element ref="{http://uri.etsi.org/m2m}creationTime" minOccurs="0"/>
 *         &lt;element ref="{http://uri.etsi.org/m2m}lastModifiedTime" minOccurs="0"/>
 *         &lt;element ref="{http://uri.etsi.org/m2m}mgmtObjsReference" minOccurs="0"/>
 *         &lt;element ref="{http://uri.etsi.org/m2m}subscriptionsReference" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://uri.etsi.org/m2m}id"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement

public class AttachedDevice extends Resource {
    @XmlSchemaType(name = "dateTime")
    protected String creationTime;
    @XmlSchemaType(name = "dateTime")
    protected String lastModifiedTime;
    @XmlSchemaType(name = "anyURI")
    
    protected String mgmtObjsReference;
    @XmlSchemaType(name = "anyURI")
    
    protected String subscriptionsReference;
    @XmlAttribute(name = "id", namespace = "http://uri.etsi.org/m2m")
    @XmlSchemaType(name = "anyURI")
    protected String id;

    /**
     * Gets the value of the creationTime property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * Sets the value of the creationTime property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCreationTime(String value) {
        this.creationTime = value;
    }

    /**
     * Gets the value of the lastModifiedTime property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * Sets the value of the lastModifiedTime property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLastModifiedTime(String value) {
        this.lastModifiedTime = value;
    }

    /**
     * Gets the value of the mgmtObjsReference property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getMgmtObjsReference() {
    	if (mgmtObjsReference == null && uri != null) {
    		return uri+Refs.MGMTOBJS_REF;
    	} else {
			return mgmtObjsReference;
    	}
    }

    /**
     * Sets the value of the mgmtObjsReference property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setMgmtObjsReference(String value) {
        this.mgmtObjsReference = value;
    }

    /**
     * Gets the value of the subscriptionsReference property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSubscriptionsReference() {
    	if (subscriptionsReference == null && uri != null) {
    		return uri+Refs.SUBSCRIPTIONS_REF;
    	} else {
			return subscriptionsReference;
    	}
    }

    /**
     * Sets the value of the subscriptionsReference property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSubscriptionsReference(String value) {
        this.subscriptionsReference = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

    public String toString() {
        return "AttachedDevice [accessRightID=" + accessRightID
                + ", creationTime=" + creationTime + ", lastModifiedTime="
                + lastModifiedTime + ", mgmtObjsReference=" + mgmtObjsReference
                + ", subscriptionsReference=" + subscriptionsReference
                + ", id=" + id + ", uri=" + uri + "]";
    }
    
}
