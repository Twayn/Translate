package org.service.translate.translate;

import static com.google.cloud.translate.Translate.TranslateOption.sourceLanguage;
import static com.google.cloud.translate.Translate.TranslateOption.targetLanguage;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.service.translate.translate.split.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.translate.Translate;

/**
 * Translates text word by word using Google Translate.
 */
@Service
public class GoogleTranslator implements Translator {
	private final Splitter splitter;
	private final Translate translate;

	@Autowired
	public GoogleTranslator(Splitter splitter, Translate translate) {
		this.splitter = splitter;
		this.translate = translate;
	}

	@Override
	public List<String> translate(String text, String from, String to) {
		return splitter.split(text).stream()
				.map(word -> translateWord(word, from, to))
				.collect(toList());
	}

	private String translateWord(String word, String from, String to) {
		return translate.translate(word, sourceLanguage(from), targetLanguage(to)).getTranslatedText();
	}
}
