package org.service.translate.translate;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.service.translate.translate.split.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.Translation;

@Service
public class GoogleTranslator implements Translator {
	private final Splitter splitter;
	private final Translate translate;

	@Autowired
	public GoogleTranslator(Splitter splitter, @Qualifier("translateWithKey") Translate translate) {
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
		Translation translation =
				translate.translate(
						word,
						TranslateOption.sourceLanguage(from),
						TranslateOption.targetLanguage(to));

		return translation.getTranslatedText();
	}
}
