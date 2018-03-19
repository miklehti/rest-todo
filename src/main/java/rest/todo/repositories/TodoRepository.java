package rest.todo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rest.todo.entities.TodoEntity;

@Transactional
@Repository
public class TodoRepository implements ITodoRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<TodoEntity> getAllTodos() {
    
		String hql = "FROM TodoEntity as tde ORDER BY tde.getId";
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
	public void updateTodoEntity(TodoEntity todoEntity) {
		TodoEntity tde = getTodoById(todoEntity.getId());
		tde.setTodo(todoEntity.getTodo());
		entityManager.flush();
	}

	@Override
	public void deleteTodoEntity(Long todoId) {
		entityManager.remove(getTodoById(todoId));
	}

}
