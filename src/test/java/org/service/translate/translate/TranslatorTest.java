package org.service.translate.translate;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TranslatorTest {
	@Autowired
	Translator translator;

	@Test
	public void translationShouldBeDone() {
		assertEquals(translator.translate("fish", "en", "ru"), List.of("рыбы"));
		assertEquals(translator.translate("повтор песни", "ru", "en"), List.of("replay", "songs"));
	}
}