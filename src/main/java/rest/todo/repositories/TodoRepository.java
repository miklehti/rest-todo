package rest.todo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rest.todo.entities.TodoEntity;
import rest.todo.exceptions.NoContentException;

@Transactional
@Repository
public class TodoRepository implements ITodoRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<TodoEntity> getAllTodos() {
    
		String hql = "FROM TodoEntity";
		return (List<TodoEntity>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public TodoEntity getTodoById(Long todoId) {	
		return entityManager.find(TodoEntity.class, todoId);
	}

	@Override
	public void addTodoEntity(TodoEntity todoEntity) {
		entityManager.persist(todoEntity);
	}

	@Override
	public TodoEntity updateTodoEntity(TodoEntity todoEntity) throws NoContentException {
		TodoEntity tde = getTodoById(todoEntity.getId());
		if(tde == null) {
			throw new NoContentException ("Päivitettävää tietoa ei löytynyt");
		}
		tde.setTodo(todoEntity.getTodo());
		 entityManager.flush();
		 return tde;
	}

	@Override
	public void deleteTodoEntity(Long todoId) throws NoContentException {
		TodoEntity tde = getTodoById(todoId);
		if(tde == null) {
			throw new NoContentException ("Poistettavaa tietoa ei löytynyt");
		}
		entityManager.remove(getTodoById(todoId));
	}

}
