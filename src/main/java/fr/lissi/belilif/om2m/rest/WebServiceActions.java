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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The Class WebServiceActions.
 *
 * @author Belili Fahem - belili.fahem@gmail.com
 */
public class WebServiceActions {

	/** The Constant LOGGER. */
	protected static final Logger LOGGER = LogManager.getLogger(WebServiceActions.class.getName());

	/**
	 * Do get.
	 *
	 * @param uri
	 *            the uri
	 * @param headers
	 *            the headers
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public static String doGet(URI uri, HashMap<String, String> headers) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String respString = null;
		try {
			/*
			 * HttpClient provides URIBuilder utility class to simplify creation and modification of request URIs.
			 * 
			 * URI uri = new URIBuilder() .setScheme("http") .setHost("hc.apache.org/") // .setPath("/search") // .setParameter("q",
			 * "httpclient") // .setParameter("btnG", "Google Search") // .setParameter("aq", "f") // .setParameter("oq", "") .build();
			 */

			HttpGet httpGet = new HttpGet(uri);

			for (String key : headers.keySet()) {
				httpGet.addHeader(key, headers.get(key));
			}

			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			// The underlying HTTP connection is still held by the response object
			// to allow the response content to be streamed directly from the network socket.
			// In order to ensure correct deallocation of system resources
			// the user MUST call CloseableHttpResponse#close() from a finally clause.
			// Please note that if response content is not fully consumed the underlying
			// connection cannot be safely re-used and will be shut down and discarded
			// by the connection manager.

			try {
				System.out.println(response1.getStatusLine());
				HttpEntity entity = response1.getEntity();
				// do something useful with the response body
				if (entity != null) {
					respString = EntityUtils.toString(entity);
				}
				// and ensure it is fully consumed
				EntityUtils.consume(entity);
			} finally {
				response1.close();
			}
		} finally {
			httpclient.close();
		}
		return respString;
	}

	/**
	 * Do get.
	 *
	 * @param uri
	 *            the uri
	 * @param headers
	 *            the headers
	 * @return the string
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws HttpResponseException
	 *             the http response exception
	 */
	public static HttpGetSimpleResp doGet(String uri, HashMap<String, String> headers)
			throws ClientProtocolException, IOException, HttpResponseException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGetSimpleResp resp = new HttpGetSimpleResp();
		try {
			HttpGet httpGet = new HttpGet(uri);
			for (String key : headers.keySet()) {
				httpGet.addHeader(key, headers.get(key));
			}
			CloseableHttpResponse response = httpclient.execute(httpGet);
			resp.setStatusCode(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				try {
					HttpEntity entity = response.getEntity();

					if (entity != null) {
						// TODO to use for performance in the future
						// ResponseHandler<String> handler = new BasicResponseHandler();
						resp.setResult(new BasicResponseHandler().handleResponse(response));
					}
					EntityUtils.consume(entity);
				} finally {
					response.close();
				}
			} else {
				// TODO optimiz (repeating code)
				throw new HttpResponseException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
			}
		} finally {
			httpclient.close();
		}
		return resp;
	}

	/**
	 * Do post.
	 *
	 * @param uri
	 *            the uri
	 * @param body
	 *            the body
	 * @param headers
	 *            the headers
	 * @return the int
	 * @throws ClientProtocolException
	 *             the client protocol exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws HttpResponseException
	 *             the http response exception
	 */
	public static int doPost(String uri, String body, HashMap<String, String> headers)
			throws ClientProtocolException, IOException, HttpResponseException {
		// TODO delete before commit
		// System.out.println("doPost>> uri:"+uri +"\nbody:"+body+"\n");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		int resp = -1;
		try {
			HttpPost httpPost = new HttpPost(uri);
			for (String key : headers.keySet()) {
				httpPost.addHeader(key, headers.get(key));
				// System.out.println("header:"+key+"/"+headers.get(key));

			}
			// System.out.println("doPost<<");
			httpPost.setEntity(new StringEntity(body));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			resp = response.getStatusLine().getStatusCode();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
				response.close();
			} else {
				throw new HttpResponseException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
			}
		} finally {
			httpclient.close();
		}
		return resp;
	}

	/**
	 * Do delete.
	 *
	 * @param uri the uri
	 * @param headers the headers
	 * @return the http get simple resp
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws HttpResponseException the http response exception
	 */
	public static HttpGetSimpleResp doDelete(String uri, HashMap<String, String> headers)
			throws ClientProtocolException, IOException, HttpResponseException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGetSimpleResp resp = new HttpGetSimpleResp();
		try {
			HttpDelete httpDelete = new HttpDelete(uri);
			for (String key : headers.keySet()) {
				httpDelete.addHeader(key, headers.get(key));
			}
			CloseableHttpResponse response = httpclient.execute(httpDelete);

			resp.setStatusCode(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT) {
				try {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						// TODO to use for performance in the future
						resp.setResult(new BasicResponseHandler().handleResponse(response));
					}
					EntityUtils.consume(entity);
				} finally {
					response.close();
				}
			} else {
				// TODO optimiz (repeating code)
				throw new HttpResponseException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
			}
		} finally {
			httpclient.close();
		}
		return resp;
	}

	/**
	 * Checks if is reachable.
	 *
	 * @param uri the uri
	 * @param headers the headers
	 * @return true, if is reachable
	 */
	public static boolean isReachable(String uri, HashMap<String, String> headers) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			// note : you may also need
			// HttpURLConnection.setInstanceFollowRedirects(false)
			HttpURLConnection myURLConnection = (HttpURLConnection) new URL(uri).openConnection();
			myURLConnection.setRequestMethod("HEAD");
			for (String key : headers.keySet()) {
				myURLConnection.setRequestProperty("Authorization", headers.get(key));
			}
			LOGGER.info(myURLConnection.getResponseCode()+" / "+myURLConnection.toString());
			return (myURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Checks if a resource is reachable.
	 *
	 * @param uri the uri
	 * @return true, if is reachable
	 */
	public static boolean isReachable(String uri) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection myURLConnection = (HttpURLConnection) new URL(uri).openConnection();
			myURLConnection.setRequestMethod("HEAD");
			return (myURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
