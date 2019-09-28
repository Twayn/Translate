package org.service.translate.translate;

import java.util.List;

/**
 * Basic interface to translate text word by word.
 */
public interface Translator {
	/**
	 * Make text translation.
	 * @param text source text for translation.
	 * @param from source language code.
	 * @param to target language code.
	 * @return list of translated words from text.
	 */
	List<String> translate(String text, String from, String to);
}