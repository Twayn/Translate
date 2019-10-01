package org.service.translate.translate;

import static com.google.cloud.translate.Translate.TranslateOption.sourceLanguage;
import static com.google.cloud.translate.Translate.TranslateOption.targetLanguage;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.service.translate.translate.split.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translation;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TranslatorTest {
	@Autowired
	Splitter splitter;

	private static Translation fish;
	private static Translation replay;
	private static Translation song;

	@BeforeClass
	public static void setup() {
		fish = translationMock("рыба");
		replay = translationMock("replay");
		song = translationMock("songs");
	}

	@Test
	public void enRuTranslationShouldBeDone() {
		Translator translator = new GoogleTranslator(splitter, fishMock());
		assertEquals(translator.translate("fish", "en", "ru"), List.of("рыба"));
	}

	@Test
	public void ruEnTranslationShouldBeDone() {
		Translator translator = new GoogleTranslator(splitter, replaySongsMock());
		assertEquals(translator.translate("повтор песни", "ru", "en"), List.of("replay", "songs"));
	}

	private static Translation translationMock(String res){
		Translation translation = mock(Translation.class);
		when(translation.getTranslatedText()).thenReturn(res);
		return translation;
	}

	private Translate fishMock(){
		Translate translate = mock(Translate.class);
		when(translate.translate("fish", sourceLanguage("en"), targetLanguage("ru")))
				.thenReturn(fish);
		return translate;
	}

	private Translate replaySongsMock(){
		Translate translate = mock(Translate.class);
		when(translate.translate("повтор", sourceLanguage("ru"), targetLanguage("en")))
				.thenReturn(replay);
		when(translate.translate("песни", sourceLanguage("ru"), targetLanguage("en")))
				.thenReturn(song);

		return translate;
	}
}
