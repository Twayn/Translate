package org.service.translate.translate.validate;

public interface TranslateParamsValidator {
	boolean translateIsSupported(String from, String to);
}
