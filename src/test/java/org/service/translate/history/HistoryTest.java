package org.service.translate.history;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HistoryTest {
	@Autowired
	TranslateHistoryRepository repository;

	@Test
	@Transactional
	public void historyItemShouldBeSaved() {
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
}