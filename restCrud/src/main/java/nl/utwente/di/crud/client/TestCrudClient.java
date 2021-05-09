package nl.utwente.di.crud.client;

import java.net.URI;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import nl.utwente.di.crud.model.Todo;

public class TestCrudClient {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(getBaseURI());
		String url_pattern = "rest";
		String path = "todos";

		// create one todo
		Todo todo = new Todo("3", "Blabla");
		
		System.out.println("URL: " + url_pattern + "/"+ path + "/"+ todo.getId());

		Response response = target.path(url_pattern).path(path)
		        .path(todo.getId()).request(MediaType.APPLICATION_XML)
		        .put(Entity.entity(todo,MediaType.APPLICATION_XML));	
		// Return code should be 201 == created resource
		System.out.println(response.getStatus());
		// Get the Todos
		System.out.println(target.path(url_pattern).path(path)
				.request(MediaType.TEXT_XML).get(String.class));
		// Get JSON for application
		System.out.println(target.path(url_pattern).path(path)
				.request(MediaType.APPLICATION_JSON).get(String.class));
		// Get XML for application
		System.out.println(target.path(url_pattern).path(path)
				.request(MediaType.APPLICATION_XML).get(String.class));

		// Get the Todo with id 1
		System.out.println(target.path(url_pattern).path(path +"/1")
				.request(MediaType.APPLICATION_XML).get(String.class));
		// get Todo with id 1
		target.path(url_pattern).path(path + "/1").request().delete();
		// Get the all todos, id 1 should be deleted
		System.out.println(target.path(url_pattern).path(path)
				.request(MediaType.APPLICATION_XML).get(String.class));

		// create a Todo
		Form form = new Form();
		form.param("id", "4");
		form.param("summary", "Demonstration of the client lib for forms");
		response = target.path(url_pattern).path(path).request(MediaType.TEXT_HTML)
				.post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		System.out.println("Form response: " + response.readEntity(String.class));
		// Get the all todos, id 4 should be created
		System.out.println(target.path("rest").path("todos")
				.request(MediaType.APPLICATION_XML).get(String.class));
		
		// Return to initial situation
		target.path(url_pattern).path(path +"/3").request().delete();
		target.path(url_pattern).path(path +"/4").request().delete();
		// Get the all todos, ids 3 and 4 should be deleted
		System.out.println(target.path("rest").path("todos")
				.request(MediaType.APPLICATION_XML).get(String.class));
		
		// Create new id 1
		todo = new Todo("1", "Returning to valid initial situation");

		target.path(url_pattern).path(path)
		        .path(todo.getId()).request(MediaType.APPLICATION_XML)
		        .put(Entity.entity(todo,MediaType.APPLICATION_XML));
		
		// Get the all todos, id 1 should be created
		System.out.println(target.path("rest").path("todos")
				.request(MediaType.APPLICATION_XML).get(String.class));
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/restCrud").build();
	}
}