package rest.todo.managers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rest.todo.exceptions.NoContentException;
import rest.todo.mappers.TodoMapper;
import rest.todo.models.Todo;
import rest.todo.models.TodoRequest;
import rest.todo.services.ITodoService;

@Component
public class TodoManager {

    @Autowired
    ITodoService todoService;

    public Todo getTodo(Long id) throws NoContentException {
        Todo palautettava = TodoMapper.map(todoService.getTodoById(id));
        if (palautettava == null) {
            throw new NoContentException("Haku tuotti tyhjän todon");
        }
        return palautettava;
    }

    public List<Todo> getAllTodos() {
        return TodoMapper.mapList(todoService.getAllTodos());
    }

    public void saveTodo(TodoRequest todoRequest) {
        todoService.addTodoEntity(TodoMapper.mapRequest(todoRequest));
    }

    public Todo updateTodo(Todo todo) throws NoContentException {
        return TodoMapper.map(todoService.updateTodoEntity(TodoMapper.mapToEntity(todo)));
    }

    public String deleteTodo(Long id) throws NoContentException {
        todoService.deleteTodoEntity(id);
        return "poistettu id " + id.toString();
    }

}
