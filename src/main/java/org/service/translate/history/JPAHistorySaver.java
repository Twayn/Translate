package org.service.translate.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * JPA implementation for saving service access requests and responses.
 */
@Component
public class JPAHistorySaver implements HistorySaver {
	private final TranslateHistoryRepository repository;

	@Autowired
	public JPAHistorySaver(TranslateHistoryRepository repository) {
		this.repository = repository;
	}

	@Override
	public void saveHistory(String text, String from, String to, List<String> result) {
		TranslateHistory historyItem = new TranslateHistory();
		historyItem.setText(text);
		historyItem.setFrom(from);
		historyItem.setTo(to);
		historyItem.setResult(result);
		repository.save(historyItem);
	}
}

