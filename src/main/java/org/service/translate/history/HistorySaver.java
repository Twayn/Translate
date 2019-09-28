package org.service.translate.history;

import java.util.List;

/**
 * Basic interface for saving service access requests.
 */
public interface HistorySaver {
	/**
	 * Save translate request and result.
	 * @param text translation source text.
	 * @param from source language code.
	 * @param to target language code.
	 * @param result result of translation.
	 */
	void saveHistory(String text, String from, String to, List<String> result);
}
