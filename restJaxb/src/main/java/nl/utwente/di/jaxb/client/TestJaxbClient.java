package nl.utwente.di.jaxb.client;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


public class TestJaxbClient {
	
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(getBaseURI());
		String url_pattern = "rest";
		String path = "todo";
		
		// Get TEXT/XML
		System.out.println("--> GET text/xml message from rest/todo:");
		System.out.println(target.path(url_pattern).path(path).request(
				MediaType.TEXT_XML).get(String.class));
		// Get APPLICATION/XML
		System.out.println("\n--> GET application/xml message from rest/todo:");
		System.out.println(target.path(url_pattern).path(path).request(
				"application/xml").get(String.class));
		// Get APPLICATION/JSON
		System.out.println("\n--> GET application/json message from rest/todo:");
		System.out.println(target.path(url_pattern).path(path).request(
				"application/json").get(String.class));
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/restJaxb").build();
	}

}
