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
// Generated on: 2013.06.24 at 02:41:52 AM CEST
//


package org.eclipse.om2m.commons.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java Class for LongPollingChannelData complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="LongPollingChannelData">
 *   &lt;complexContent>
 *     &lt;extension base="{http://uri.etsi.org/m2m}ChannelData">
 *       &lt;sequence>
 *         &lt;element ref="{http://uri.etsi.org/m2m}longPollingURI"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LongPollingChannelData", propOrder = {
        "longPollingURI"
})

public class LongPollingChannelData extends ChannelData {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String longPollingURI;

    /**
     * Gets the value of the property longPollingURI.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLongPollingURI() {
        return longPollingURI;
    }

    /**
     * Sets the value of the property longPollingURI.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLongPollingURI(String value) {
        this.longPollingURI = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((longPollingURI == null) ? 0 : longPollingURI.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		LongPollingChannelData other = (LongPollingChannelData) obj;
		if (longPollingURI == null) {
			if (other.longPollingURI != null) {
				return false;
			}
		} else if (!longPollingURI.equals(other.longPollingURI)) {
			return false;
		}
		return true;
	}

}
