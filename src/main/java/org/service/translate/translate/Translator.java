package org.service.translate.translate;

import java.util.List;

public interface Translator {
	List<String> translate(String text, String from, String to);
}