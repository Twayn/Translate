package org.service.translate.translate.split;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Splits text to words using regular expression.
 * Split occurs by one or more not alphanumeric symbols.
 */
@Service
public class SplitByNotAlphanumeric implements Splitter {
	@Override
	public List<String> split(String text) {
		return Arrays.asList(text.split("[\\p{Z}\\p{S}\\p{P}\\p{C}]+"));
	}
}
