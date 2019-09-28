package org.service.translate.error;

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
