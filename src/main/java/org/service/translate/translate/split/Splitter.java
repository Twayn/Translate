package org.service.translate.translate.split;

import java.util.List;

/**
 * Basic interface for splitting text into words.
 */
public interface Splitter {
	List<String> split(String text);
}
