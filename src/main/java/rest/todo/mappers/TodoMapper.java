package rest.todo.mappers;

import java.util.ArrayList;
import java.util.List;

import rest.todo.entities.TodoEntity;
import rest.todo.models.Todo;
import rest.todo.models.TodoRequest;

public class TodoMapper {

	public static Todo map(TodoEntity todoEntity) {
		Todo todo = new Todo();
		todo.setId(todoEntity.getId());
		todo.setTodo(todoEntity.getTodo());
		return todo;
	}

	public static List<Todo> mapList(List<TodoEntity> allTodos) {
		
		List<Todo> lista = new ArrayList<Todo>();
		for(TodoEntity todoEntity:allTodos) {
			Todo todo = map(todoEntity);
			lista.add(todo);
		}
		return lista;
	}

	public static TodoEntity mapRequest(TodoRequest todoRequest) {
		TodoEntity todo = new TodoEntity();
		todo.setTodo(todoRequest.getTodo());
		return todo;
	}

	public static TodoEntity mapToEntity(Todo todo) {
		TodoEntity todoEntity = new TodoEntity();
		todoEntity.setId(todo.getId());
		todoEntity.setTodo(todo.getTodo());
		return todoEntity;
	}
}
