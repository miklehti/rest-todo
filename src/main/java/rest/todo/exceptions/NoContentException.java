package rest.todo.exceptions;

import org.springframework.http.HttpStatus;

public class NoContentException extends Exception{

	
	private static final long serialVersionUID = 1L;
	
	public NoContentException(String mesage) {
		super(mesage);
	}
	
	public final HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}
	

}
