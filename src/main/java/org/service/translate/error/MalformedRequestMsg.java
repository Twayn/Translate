package org.service.translate.error;

/**
 * Service response for malformed requests.
 * e.g. missing parameter or not supported language code.
 */
public class MalformedRequestMsg {
	private String error;

	public MalformedRequestMsg(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
