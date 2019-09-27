package org.service.translate.history;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class TranslateHistory extends BaseEntity {
	@Column(name = "text_param")
	private String text;

	@Column(name = "from_param")
	private String from;

	@Column(name = "to_param")
	private String to;

	@Column(name = "result")
	@ElementCollection
	private List<String> result;

	@Column(name = "access_date")
	private ZonedDateTime accessTime;

	public TranslateHistory() {
		this.accessTime = ZonedDateTime.now();
	}

	public ZonedDateTime getAccessTime() {
		return accessTime;
	}

	public String getText() {
		return text;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public List<String> getResult() {
		return result;
	}

	public void setAccessTime(ZonedDateTime accessTime) {
		this.accessTime = accessTime;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setResult(List<String> result) {
		this.result = result;
	}
}
