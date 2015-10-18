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

/**
 *The Content attribute is the real opaque content of an instance. This may for
 * example be an image taken by a security camera, or a temperature measurement
 * taken by a temperature sensor.
 *
 */

public class Content extends Resource {

    private Base64Binary value;

    /**
     * Gets the value
     * @return the Base64Binary value
     */
    public Base64Binary getValue() {
        return value;
    }

    /**
     * Sets the value
     * @param the value to set
     */
    public void setValue(Base64Binary value) {
        this.value = value;
    }

    public String toString() {
        return "Content [value=" + value + "]";
    }

}
