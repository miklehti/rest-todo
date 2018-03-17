package rest.todo.models;

import java.io.Serializable;


public class TodoRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	private String todo;


	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

}
