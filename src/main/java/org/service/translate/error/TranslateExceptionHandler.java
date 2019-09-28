package org.service.translate.error;

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
		return new MalformedRequestMsg("Parameter is missing: " + name + ". " +
				"Expected request format: /translate?text=text&from=from&to=to");
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public MalformedRequestMsg runtime(RuntimeException ex) {
		return new MalformedRequestMsg(ex.getMessage());
	}
}
