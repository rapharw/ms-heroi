package br.com.ey.msheroi.config.handlers;

import br.com.ey.msheroi.exception.DefaultErrorException;
import br.com.ey.msheroi.exception.vo.RestExceptionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	private HttpHeaders getHttpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8");
        return httpHeaders;
    }
    
    @ExceptionHandler(value= {Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
    	
    	log.error("handleException: ", ex);
        return handleExceptionInternal(ex,
        		new RestExceptionVO("Ocorreu um erro interno. Tente novamente mais tarde.", 500),
        		getHttpHeaders(), 
        		HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    
    @ExceptionHandler(value= {DefaultErrorException.class})
    public ResponseEntity<Object> handlerDefaultErrorException(DefaultErrorException ex, WebRequest request) {
        return handleExceptionInternal(ex, 
        		new RestExceptionVO(ex.getMessage(), 500),
        		getHttpHeaders(), 
        		HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


}
