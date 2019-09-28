package org.service.translate.history;

import java.util.List;

/**
 * Basic interface for saving service access requests.
 */
public interface HistorySaver {
	void saveHistory(String text, String from, String to, List<String> result);
}
