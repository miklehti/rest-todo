package rest.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rest.todo.models.Todo;
import rest.todo.managers.TodoManager;
import rest.todo.models.TodoRequest;

@RequestMapping("v1/")

@RestController


@Api
public class TodoController {
	
	@Autowired
	private TodoManager todoManager;
	
	 @ApiOperation(value = "Haetaan todo id:llä", response = Todo.class)
	 @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
	    public ResponseEntity<Todo> getTodo(@PathVariable("id") long id) {
	        return new ResponseEntity<>(todoManager.getTodo(id), HttpStatus.OK);
	    }
	 
	 @ApiOperation(value = "Haetaan kaikki todot")
	 @RequestMapping(value = "/alltodos", method = RequestMethod.GET)
	    public List<Todo> getAllTodos() {
	        return todoManager.getAllTodos();
	    }
	 
	 @ApiOperation(value = "Tallenna todo")
	 @RequestMapping(value = "/tallenna", method = RequestMethod.POST)
	    public ResponseEntity<Todo> saveTodo(@RequestBody TodoRequest todo) {
		    Todo tallennettu = todoManager.saveTodo(todo);
	        return new ResponseEntity<>(tallennettu, HttpStatus.OK);
	    }
	 
	 @ApiOperation(value = "Päivitä todo")
	 @RequestMapping(value = "/paivita", method = RequestMethod.PUT)
	    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
		    Todo paivitetty = todoManager.updateTodo(todo);
	        return new ResponseEntity<>(paivitetty, HttpStatus.OK);
	    }
	 
	 @ApiOperation(value = "Poista todo")
	 @RequestMapping(value = "/poista", method = RequestMethod.POST)
	    public String deleteTodo(@RequestBody Todo todo) {
	        return  todoManager.deleteTodo(todo);
	    }
	 
	 

}
