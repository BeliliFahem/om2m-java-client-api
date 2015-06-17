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
/**
 * @author Belili Fahem - belili.fahem@gmail.com
 *
 */
@XmlSchema(
    namespace = "http://uri.etsi.org/m2m",
    elementFormDefault = XmlNsForm.QUALIFIED,
    xmlns={@XmlNs(prefix="om2m", namespaceURI="http://uri.etsi.org/m2m")}) 

package fr.lissi.belilif.om2m.model;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
