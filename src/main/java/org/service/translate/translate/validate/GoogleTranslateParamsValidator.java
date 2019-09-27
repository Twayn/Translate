package org.service.translate.translate.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;

@Service
public class GoogleTranslateParamsValidator implements TranslateParamsValidator {
	private final List<Language> supportedLanguages;
	private final Translate translate;

	@Autowired
	public GoogleTranslateParamsValidator(@Qualifier("translateWithKey") Translate translate) {
		supportedLanguages = translate.listSupportedLanguages();
		this.translate = translate;
	}

	private boolean languageIsSupported(String code) {
		return supportedLanguages.stream().anyMatch(it -> it.getCode().equals(code));
	}

	@Override
	public boolean translateIsSupported(String from, String to) {
		if (!languageIsSupported(from)) return false;
		if (!languageIsSupported(to)) return false;

		List<Language> languages =
				translate.listSupportedLanguages(Translate.LanguageListOption.targetLanguage(to));
		return languages.stream().anyMatch(it -> it.getCode().equals(from));
	}
}
