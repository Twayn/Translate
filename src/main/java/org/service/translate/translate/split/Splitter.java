package org.service.translate.translate.split;

import java.util.List;

/**
 * Split text into words basic interface
 */
public interface Splitter {
	List<String> split(String text);
}
