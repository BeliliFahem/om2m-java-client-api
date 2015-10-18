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
package fr.lissi.belilif.om2m.rest;

// TODO: Auto-generated Javadoc
/**
 * The Class HttpGetSimpleResp.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
public class HttpGetSimpleResp {

	/** The status code. */
	private int statusCode;
	
	/** The result. */
	private String result;

	/**
	 * Instantiates a new http get simple resp.
	 */
	public HttpGetSimpleResp() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(String result) {
		this.result = result;
	}

}