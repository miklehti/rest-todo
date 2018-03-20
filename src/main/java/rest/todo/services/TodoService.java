package rest.todo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rest.todo.entities.TodoEntity;
import rest.todo.exceptions.NoContentException;
import rest.todo.repositories.ITodoRepository;

@Service
public class TodoService implements ITodoService{

	@Autowired
	private ITodoRepository todoRepository;
	@Override
	public List<TodoEntity> getAllTodos() {
		return  todoRepository.getAllTodos();
	}

	@Override
	public TodoEntity getTodoById(Long todoId) {	
		return todoRepository.getTodoById(todoId);
	}

	@Override
	public void addTodoEntity(TodoEntity todoEntity) {
       todoRepository.addTodoEntity(todoEntity);
	}

	@Override
	public TodoEntity updateTodoEntity(TodoEntity todoEntity) throws NoContentException {
		return todoRepository.updateTodoEntity(todoEntity);
		
	}

	@Override
	public void deleteTodoEntity(Long todoId) throws NoContentException {
		todoRepository.deleteTodoEntity(todoId);
		
	}

}
