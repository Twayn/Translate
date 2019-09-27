package org.service.translate.history;

import java.util.List;

public interface HistorySaver {
	void saveHistory(String text, String from, String to, List<String> result);
}
