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
package fr.lissi.belilif.om2m.exception;

/**
 * The Class UnsupportedMethodException.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
public class UnsupportedMethodException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5798280588383147402L;

	/**
	 * Instantiates a new unsupported method exception.
	 */
	public UnsupportedMethodException() {
	}

	/**
	 * Instantiates a new unsupported method exception.
	 *
	 * @param message the message
	 */
	public UnsupportedMethodException(String message) {
		super(message);
	}
	
    /**
     * Instantiates a new unsupported method exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public UnsupportedMethodException (String message, Throwable cause) {
        super (message, cause);
    }

}
