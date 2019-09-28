package org.service.translate.translate.validate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TranslateParamsValidatorTest {
	@Autowired
	TranslateParamsValidator validator;

	@Test
	public void translateDirectionsShouldBeSupported() {
		assertTrue(validator.translateIsSupported("ru", "en"));
		assertTrue(validator.translateIsSupported("en", "ru"));
		assertTrue(validator.translateIsSupported("es", "fr"));
	}

	@Test
	public void translateDirectionsShouldNotBeSupported() {
		assertFalse(validator.translateIsSupported("rus", "en"));
		assertFalse(validator.translateIsSupported("ru", "eng"));

		assertFalse(validator.translateIsSupported("eng", "ru"));
		assertFalse(validator.translateIsSupported("en", "rus"));

		assertFalse(validator.translateIsSupported("ar", "ba"));
	}
}