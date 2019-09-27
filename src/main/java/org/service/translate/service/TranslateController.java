package org.service.translate.service;

import java.util.List;

import org.service.translate.history.HistorySaver;
import org.service.translate.translate.validate.TranslateParamsValidator;
import org.service.translate.translate.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslateController {
	private final HistorySaver saver;
	private final Translator translator;
	private final TranslateParamsValidator validator;

	@Autowired
	public TranslateController(HistorySaver saver, Translator translator, TranslateParamsValidator validator) {
		this.saver = saver;
		this.translator = translator;
		this.validator = validator;
	}

	@GetMapping("/translate")
	public List<String> translate(@RequestParam String text, @RequestParam String from, @RequestParam String to) {
		if(validator.translateIsSupported(from, to)){
			List<String> result = translator.translate(text, from, to);
			saver.saveHistory(text, from, to, result);
			return result;
		}

		throw new RuntimeException("Translate from '" + from + "' to '" + to + "' not supported");
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error handleMissingParams(MissingServletRequestParameterException ex) {
		String name = ex.getParameterName();
		return new Error("Parameter is missing: " + name);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error runtime(RuntimeException ex) {
		return new Error(ex.getMessage());
	}
}