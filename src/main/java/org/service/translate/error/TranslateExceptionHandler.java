package org.service.translate.error;

import static org.service.translate.error.MalformedRequestMsg.ofMissingPar;
import static org.service.translate.error.MalformedRequestMsg.ofMsg;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class TranslateExceptionHandler {
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public MalformedRequestMsg handleMissingParams(MissingServletRequestParameterException ex) {
		String name = ex.getParameterName();
		return ofMissingPar(name);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public MalformedRequestMsg runtime(RuntimeException ex) {
		return ofMsg(ex.getMessage());
	}
}
