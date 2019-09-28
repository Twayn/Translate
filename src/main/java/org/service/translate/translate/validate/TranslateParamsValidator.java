package org.service.translate.translate.validate;

/**
 * Basic interface to test the translation directions.
 */
public interface TranslateParamsValidator {
	boolean translateIsSupported(String from, String to);
}
