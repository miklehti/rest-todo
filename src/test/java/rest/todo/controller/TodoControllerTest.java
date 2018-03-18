package rest.todo.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerTest {
	
	 @LocalServerPort
	 private int port;
	 
	    private URL base;

	    @Autowired
	    private TestRestTemplate template;
	    
	    @Before
	    public void setUp() throws Exception {
	        this.base = new URL("http://localhost:" + port + "/alltodos");
	    }

	    @Test
	    public void getHello() throws Exception {
	        ResponseEntity<String> response = template.getForEntity(base.toString(),
	                String.class);
	        assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
	    }

}
