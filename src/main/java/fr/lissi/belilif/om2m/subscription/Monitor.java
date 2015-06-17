package fr.lissi.belilif.om2m.subscription;


/*******************************************************************************
 * Copyright (c) 2013-2014 LAAS-CNRS (www.laas.fr)
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
 
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;



public class Monitor {
	
	/** The port. */
	private static int port = 1400;
	
	/** The context. */
	private static String context = "/monitor";


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	@SuppressWarnings("restriction")
	public static void main(String[] args) throws Exception {
		System.out.println("Starting server..");
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		server.createContext(context, new MyHandler());
		server.start();
		System.out.println("The server is now listening on\nPort: "+ port+"\non context: "+context+"\n");
	}
	@SuppressWarnings("restriction")
	static class MyHandler implements HttpHandler {
		
		/* (non-Javadoc)
		 * @see com.sun.net.httpserver.HttpHandler#handle(com.sun.net.httpserver.HttpExchange)
		 */
		@SuppressWarnings("restriction")
		public void handle(HttpExchange t) throws IOException {
			System.out.println("Received notification:");
			String body = "";
			int i;
			char c;
			try {
				InputStream is = t.getRequestBody();
				while ((i = is.read()) != -1) {
					c = (char) i;
					body = (String) (body + c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(body);
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			try {

				DocumentBuilder dBuilder = dbf.newDocumentBuilder();
				Document notifyDoc = dBuilder.parse(new InputSource(new ByteArrayInputStream(body.getBytes("utf-8"))));

				String contentInstance64 = notifyDoc.getElementsByTagName("om2m:representation").item(0).getFirstChild().getTextContent();
				System.out.println("ContentInstance (Base64-encoded):\n"+contentInstance64+"\n");

				String contentInstance = new String(DatatypeConverter.parseBase64Binary(contentInstance64));			
				System.out.println("ContentInstance:\n"+contentInstance+"\n");
		
				Document instanceDoc = dBuilder.parse(new InputSource(new ByteArrayInputStream(contentInstance.getBytes("utf-8"))));
				Node content64 = instanceDoc.getElementsByTagName("om2m:content").item(0).getFirstChild();
				System.out.println("Content (Base64-encoded):\n"+content64.getTextContent()+"\n");

				final String content = new String(DatatypeConverter.parseBase64Binary(content64.getTextContent()));
				System.out.println("Content:\n"+content+"\n");				
				
				new Thread(){
					public void run(){
						javax.swing.JOptionPane.showMessageDialog(null,"Received event:\n"+content); 
					};
				}.start();
				
				t.sendResponseHeaders(204, -1);

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
				t.sendResponseHeaders(501, -1);
			} catch (SAXException e) {
				e.printStackTrace();
				t.sendResponseHeaders(501, -1);
			} catch (IOException e) {
				e.printStackTrace();
				t.sendResponseHeaders(501, -1);
			}
		}
	}
}
