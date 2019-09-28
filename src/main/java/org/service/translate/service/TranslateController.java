package org.service.translate.service;

import static org.service.translate.error.MalformedRequestMsg.ofBadLangPair;

import java.util.List;

import org.service.translate.history.HistorySaver;
import org.service.translate.translate.Translator;
import org.service.translate.translate.validate.TranslateParamsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides API for text translation.
 * Accepts GET requests on /translate.
 * Params:
 * - text: text for translation.
 * - from: source language code (en, ru, fr, es ...).
 * - to: target language code.
 *
 * Returns:
 * - Text translated word by word (as a JSON).
 * - If at least one of languages or a direction itself not supported
 *   or some parameters are missing returns error message.
 */
@RestController
public class TranslateController {
	private final HistorySaver saver;
	private final Translator translator;
	private final TranslateParamsValidator validator;

	@Autowired
	public TranslateController(HistorySaver saver,
							   Translator translator,
							   TranslateParamsValidator validator) {
		this.saver = saver;
		this.translator = translator;
		this.validator = validator;
	}

	@GetMapping("/translate")
	public List<String> translate(@RequestParam String text,
								  @RequestParam String from,
								  @RequestParam String to) {
		if(validator.translateIsSupported(from, to)) {
			var result = translator.translate(text, from, to);
			saver.saveHistory(text, from, to, result);
			return result;
		} else {
			var msg = ofBadLangPair(from, to).getMsg();
			saver.saveHistory(text, from, to, List.of(msg));
			throw new RuntimeException(msg);
		}
	}
}