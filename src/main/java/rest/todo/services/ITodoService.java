package rest.todo.services;

import java.util.List;

import rest.todo.entities.TodoEntity;

public interface ITodoService {

 	List<TodoEntity> getAllTodos();
 	
 	TodoEntity getTodoById(Long todoId);
 	
 	void addTodoEntity(TodoEntity todoEntity);
    
 	void updateTodoEntity(TodoEntity todoEntity);
    
    void deleteTodoEntity(Long todoId);
}
