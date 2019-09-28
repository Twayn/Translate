package org.service.translate.translate.validate;

import static com.google.cloud.translate.Translate.LanguageListOption.targetLanguage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;

/**
 * Test the possibility of translate in requested direction (by Google Translate).
 * Both languages and direction itself must be supported by Google.
 */
@Service
public class GoogleTranslateParamsValidator implements TranslateParamsValidator {
	private final List<Language> supported;
	private final Translate translate;

	@Autowired
	public GoogleTranslateParamsValidator(Translate translate) {
		supported = translate.listSupportedLanguages();
		this.translate = translate;
	}

	private boolean containsCode(List<Language> list, String code) {
		return list.stream().anyMatch(it -> it.getCode().equals(code));
	}

	@Override
	public boolean translateIsSupported(String from, String to) {
		if (!containsCode(supported, from)) return false;
		if (!containsCode(supported, to)) return false;

		var languages = translate.listSupportedLanguages(targetLanguage(to));
		return containsCode(languages, from);
	}
}
