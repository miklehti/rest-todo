package rest.todo.models;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class TodoRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(min=4, max=64)
	private String todo;


	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

}
