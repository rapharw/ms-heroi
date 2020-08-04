package br.com.ey.msheroi.common.exception;

public class DefaultErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    private Exception exception;
    private static final String MESSAGE_DEFAULT = "Ocorreu um erro interno. Tente novamente mais tarde.";

	public DefaultErrorException(String message){
        super(message);
    }

    public DefaultErrorException(Exception exception){
        super(exception);
        this.exception = exception;
    }
	
	public DefaultErrorException(){
        super(MESSAGE_DEFAULT);
    }

    public String getException(){
	    return exception.getMessage();
    }

}