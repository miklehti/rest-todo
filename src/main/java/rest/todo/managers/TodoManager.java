package rest.todo.managers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import rest.todo.models.Todo;
import rest.todo.models.TodoRequest;

@Component
public class TodoManager {
    public Todo getTodo(@PathVariable("id") long id) {
    	Todo todo = new Todo();
    	todo.setId(1L);
    	todo.setTodo("eka taski");
        return todo;
    }

	public List<Todo> getAllTodos() {
    	Todo todo = new Todo();
    	todo.setId(1L);
    	todo.setTodo("eka taski");
    	Todo todo2 = new Todo();
    	todo2.setId(1L);
    	todo2.setTodo("toka taski");
		List<Todo> lista = new ArrayList<Todo>();
		lista.add(todo2);
		lista.add(todo);
		return lista;
	}

	public Todo saveTodo(TodoRequest todoRequest) {
		Todo todo = new Todo();
    	todo.setTodo(todoRequest.getTodo());
    	todo.setId(2L);
        return todo;

	}

	public Todo updateTodo(Todo todo) {
    	todo.setTodo("päivitetty todo");
        return todo;
	}

	public String deleteTodo(Todo todo) {
		
		return "todo poistettu";
	}

}
