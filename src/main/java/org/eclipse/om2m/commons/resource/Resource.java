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
package org.eclipse.om2m.commons.resource;

import javax.xml.bind.annotation.XmlTransient;


public abstract class Resource {
	
	
    protected String uri;
	
    protected SearchStrings searchStrings;
    protected String accessRightID;

	/**
     * Gets the value of the property uri.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @XmlTransient
    public String getUri() {
        return uri;
    }

    /**
     * Sets the value of the property uri.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Gets the value of the property accessRightID.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAccessRightID() {
        return accessRightID;
    }

    /**
     * Sets the value of the property accessRightID.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAccessRightID(String value) {
        this.accessRightID = value;
    }

    /**
     * Gets the value of the searchStrings property.
     *
     * @return possible object is {@link SearchStrings }
     *
     */
    public SearchStrings getSearchStrings() {
        return searchStrings;
    }

    /**
     * Sets the value of the searchStrings property.
     *
     * @param value
     *            allowed object is {@link SearchStrings }
     *
     */
    public void setSearchStrings(SearchStrings value) {
        this.searchStrings = value;
    }
    
}
