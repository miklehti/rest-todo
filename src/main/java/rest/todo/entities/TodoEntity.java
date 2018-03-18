package rest.todo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;;


public class TodoEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final Logger loki = LogManager.getLogger();


	private Long id;
	

	private String todo;

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try
		{
			return ow.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			loki.debug("Jotain meni pieleen parsittaessa jsonia");
			return null;
		}
	}
}
