package org.service.translate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.service.translate.history.TranslateHistory;
import org.service.translate.history.TranslateHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TranslateApplicationTests {
	@Autowired
	TranslateHistoryRepository repository;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Transactional
	public void historyItemShoudBeSaved() {
		TranslateHistory item = new TranslateHistory();
		item.setText("home");
		item.setFrom("en");
		item.setTo("ru");
		item.setResult(List.of("Дыня"));

		repository.save(item);

		TranslateHistory savedItem = repository.findById(item.getId()).orElseThrow();
		Assert.assertEquals(item.getText(), savedItem.getText());
		Assert.assertEquals(item.getFrom(), savedItem.getFrom());
		Assert.assertEquals(item.getTo(), savedItem.getTo());
		Assert.assertEquals(item.getResult(), savedItem.getResult());
	}

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(restTemplate.getForObject("http://localhost:8080/translate/?text=abc,aad&from=jhg&to=ggg",
				String.class)).contains("[\"abc\",\"aad\"]");

//		http://localhost:8080/translate/?text=home,get&from=en&to=ru
	}
}
