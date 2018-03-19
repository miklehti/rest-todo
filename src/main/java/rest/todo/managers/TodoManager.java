package rest.todo.managers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import rest.todo.entities.TodoEntity;
import rest.todo.mappers.TodoMapper;
import rest.todo.models.Todo;
import rest.todo.models.TodoRequest;
import rest.todo.services.ITodoService;

@Component
public class TodoManager {
	
	@Autowired
	ITodoService todoService;
	
    public Todo getTodo(Long id) {
    	return TodoMapper.map(todoService.getTodoById(id));
    }

	public List<Todo> getAllTodos() {
		return TodoMapper.mapList(todoService.getAllTodos());
	}

	public void saveTodo(TodoRequest todoRequest) {
		todoService.addTodoEntity(TodoMapper.mapRequest(todoRequest));
	}

	public void updateTodo(Todo todo) {
		todoService.updateTodoEntity(TodoMapper.mapToEntity(todo));
	}

	public String deleteTodo(Todo todo) {
		todoService.deleteTodoEntity(todo.getId());
		return "poistettu id " + todo.getId().toString();
	}

}
