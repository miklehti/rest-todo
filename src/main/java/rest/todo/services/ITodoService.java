package rest.todo.services;

import java.util.List;

import rest.todo.entities.TodoEntity;
import rest.todo.models.Todo;

public interface ITodoService {

 	List<TodoEntity> getAllTodos();
 	
 	TodoEntity getTodoById(Long todoId);
 	
 	void addTodoEntity(TodoEntity todoEntity);
    
 	TodoEntity updateTodoEntity(TodoEntity todoEntity);
    
    void deleteTodoEntity(Long todoId);
}
