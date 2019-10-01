package org.service.translate.translate;

import static com.google.cloud.translate.Translate.TranslateOption.sourceLanguage;
import static com.google.cloud.translate.Translate.TranslateOption.targetLanguage;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.service.translate.translate.split.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translation;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TranslatorTest {
	@Autowired
	Splitter splitter;

	private Map<String, String> enRuTranslations =
			Map.of(
					"fish", "рыба"
			);

	private Map<String, String> ruEnTranslations =
			Map.of(
					"повтор", "replay",
					"песни", "songs"
			);

	@Test
	public void translationShouldBeDone() {
		Translator translator = new GoogleTranslator(splitter, translateMock());
		assertEquals(translator.translate("fish", "en", "ru"), List.of("рыба"));
		assertEquals(translator.translate("повтор песни", "ru", "en"), List.of("replay", "songs"));
	}

	private Translate translateMock() {
		Translate translate = mock(Translate.class);
		enRuTranslations.forEach((word, transl) ->
				addEnRuTranslation(translate, word, translationMock(transl)));
		ruEnTranslations.forEach((word, transl) ->
				addRuEnTranslation(translate, word, translationMock(transl)));
		return translate;
	}

	private static Translation translationMock(String res) {
		Translation translation = mock(Translation.class, RETURNS_DEEP_STUBS);
		when(translation.getTranslatedText()).thenReturn(res);
		return translation;
	}

	private void addRuEnTranslation(Translate translate, String word, Translation res) {
		when(translate.translate(word, sourceLanguage("ru"), targetLanguage("en")))
				.thenReturn(res);
	}

	private void addEnRuTranslation(Translate translate, String word, Translation res) {
		when(translate.translate(word, sourceLanguage("en"), targetLanguage("ru")))
				.thenReturn(res);
	}
}
