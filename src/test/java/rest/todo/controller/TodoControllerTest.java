package rest.todo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import rest.todo.models.Todo;
import rest.todo.models.TodoRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@Sql(executionPhase= Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"/datasets/DeleteAll.sql", "/datasets/init.sql"} )

public class TodoControllerTest {
	
	 @LocalServerPort
	 private int port;
	 
	    private URL base;
	    
	    private String baseUrl;
	    
	    @Autowired
	    private TestRestTemplate template;
	    
	    HttpHeaders headers = new HttpHeaders();
	    
	    @Before
	    public void setUp() throws Exception {	    	
	       this.baseUrl = "http://localhost:" + port + "/v1";
	    }
	    
	    @Test
		public void addTodo() {

	    	String urli = this.baseUrl + "/tallenna";
	    	TodoRequest todo = new TodoRequest();
	    	todo.setTodo("toka Todo");
			HttpEntity<TodoRequest> entity = new HttpEntity<TodoRequest>(todo, headers);
			ResponseEntity<?> response = template.exchange(urli, HttpMethod.POST, entity, Object.class, Object.class);
			assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		}

	    @Test
	    public void getTodo() throws Exception {
	    	 this.base = new URL(baseUrl + "/todo/1");
	    	
	        ResponseEntity<Todo> response = template.getForEntity(base.toString(),
	                Todo.class);
	        assertThat(response.getBody().getId(), equalTo(1L));
	        assertThat(response.getBody().getTodo(), equalTo("ekkatodo"));
	        assertThat(response.getBody().getAikaleima().getTime(), is(1521583200000L));
	        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	    }
	    
	    @Test
	    public void updateTodo() throws Exception {
	    	
	    	 	this.base = new URL(baseUrl + "/todo/1");	    	
		        ResponseEntity<Todo> response = template.getForEntity(base.toString(),
		                Todo.class);
		        assertThat(response.getBody().getTodo(), equalTo("ekkatodo"));
		        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		   
		    	Todo todo = response.getBody();
		    	todo.setTodo("eiku eka uusiksi");	
		    	String urli = baseUrl + "/paivita";
				HttpEntity<Todo> entity = new HttpEntity<Todo>(todo, headers);
				ResponseEntity<Todo> response2 = template.exchange(urli, HttpMethod.PUT, entity, Todo.class, Object.class);
				assertThat(response.getBody().getId(), equalTo(1L));
			    assertThat(response.getBody().getTodo(), equalTo("eiku eka uusiksi"));
				assertThat(response2.getStatusCode(), equalTo(HttpStatus.OK));

	    }
	   
	    
	    @Test
		public void getAllTodos() throws MalformedURLException {

	    	String urli = this.baseUrl + "/tallenna";
	    	TodoRequest todo = new TodoRequest();
	    	todo.setTodo("toka 2 Todo");
			HttpEntity<TodoRequest> entity = new HttpEntity<TodoRequest>(todo, headers);
			ResponseEntity<?> response = template.exchange(urli, HttpMethod.POST, entity, Object.class, Object.class);
			assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
			
			 this.base = new URL(baseUrl + "/alltodos");
		     ResponseEntity<List<Todo>> response2 = template.exchange(this.base.toString(), HttpMethod.GET,null,new ParameterizedTypeReference<List<Todo>>() {
	            });

			List<Todo> lista = (List<Todo>) response2.getBody();
			Todo eka = lista.get(0);
			Todo toka = lista.get(1);
			
			 assertThat(eka.getId(), equalTo(1L));
			 assertThat(toka.getId(), equalTo(2L));
			 assertThat(eka.getTodo(), equalTo("ekkatodo"));
			 assertThat(toka.getTodo(), equalTo("toka 2 Todo"));
		     assertThat(response2.getStatusCode(), equalTo(HttpStatus.OK));
		}
	    
	    @Test
		public void deleteTodo() throws MalformedURLException {

	    	String urli = this.baseUrl + "/tallenna";
	    	TodoRequest todo = new TodoRequest();
	    	todo.setTodo("toka 2 Todo");
			HttpEntity<TodoRequest> entity = new HttpEntity<TodoRequest>(todo, headers);
			ResponseEntity<?> response = template.exchange(urli, HttpMethod.POST, entity, Object.class, Object.class);
			assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
			
			 this.base = new URL(baseUrl + "/alltodos");
		     ResponseEntity<List<Todo>> response2 = template.exchange(this.base.toString(), HttpMethod.GET,null,new ParameterizedTypeReference<List<Todo>>() {
	            });

			List<Todo> lista = (List<Todo>) response2.getBody();
			Todo eka = lista.get(0);
			Todo toka = lista.get(1);
			
			 assertThat(eka.getId(), equalTo(1L));
			 assertThat(toka.getId(), equalTo(3L));
			 assertThat(eka.getTodo(), equalTo("ekkatodo"));
			 assertThat(toka.getTodo(), equalTo("toka 2 Todo"));
		     assertThat(response2.getStatusCode(), equalTo(HttpStatus.OK));

               
			String urli2 = this.baseUrl + "/poista/3";

			ResponseEntity<String> response4 = template.exchange(urli2, HttpMethod.POST, null, String.class, Object.class);
			assertThat(response4.getStatusCode(), equalTo(HttpStatus.OK));
			assertThat(response4.getBody(), equalTo("poistettu id 3"));
			
			
			
			 this.base = new URL(baseUrl + "/alltodos");
		     ResponseEntity<List<Todo>> response3 = template.exchange(this.base.toString(), HttpMethod.GET,null,new ParameterizedTypeReference<List<Todo>>() {
	            });

			List<Todo> lista2 = (List<Todo>) response3.getBody();
			Todo eka2 = lista2.get(0);
			 assertThat(lista2.size(), equalTo(1));
			 assertThat(eka2.getId(), equalTo(1L));
			 assertThat(eka2.getTodo(), equalTo("ekkatodo"));
		     assertThat(response3.getStatusCode(), equalTo(HttpStatus.OK));
		}



	    
	    

}
