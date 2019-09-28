package org.service.translate.service;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.service.translate.error.MalformedRequestMsg.ofBadLangPair;
import static org.service.translate.error.MalformedRequestMsg.ofMissingPar;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(TranslateController.class)
public class TranslateControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private TranslateController controller;

	@Test
	public void enRuTranslationShouldBeDone() throws Exception {
		List<String> expected = List.of("Главная", "получить");

		given(controller.translate("home,get", "en", "ru")).willReturn(expected);

		mvc.perform(get("/translate/?text=home,get&from=en&to=ru")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0]", is(expected.get(0))))
				.andExpect(jsonPath("$[1]", is(expected.get(1))));
	}

	@Test
	public void ruEnTranslationShouldBeDone() throws Exception {
		List<String> expected = List.of("different", "sound");

		given(controller.translate("разный,звук", "ru", "en")).willReturn(expected);

		mvc.perform(get("/translate/?text=разный,звук&from=ru&to=en")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0]", is(expected.get(0))))
				.andExpect(jsonPath("$[1]", is(expected.get(1))));
	}

	@Test
	public void requestWithMissingParameterShouldReturnErrorMsg() throws Exception {
		mvc.perform(get("/translate/?text=home,get&from=en")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.msg", containsString(ofMissingPar("to").getMsg())));
	}

	@Test
	public void requestWithNonSupportedLanguageShouldReturnErrorMsg() throws Exception {
		Exception expected = new RuntimeException(ofBadLangPair("en", "rus").getMsg());

		given(controller.translate("home,get", "en", "rus")).willThrow(expected);

		mvc.perform(get("/translate/?text=home,get&from=en&to=rus")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.msg", is(expected.getMessage())));
	}
}
