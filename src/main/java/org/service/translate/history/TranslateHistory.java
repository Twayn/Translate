package org.service.translate.history;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for representing request and result when service calling.
 */
@Getter
@Setter
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

	@Column(name = "access_time")
	private ZonedDateTime accessTime;

	public TranslateHistory() {
		this.accessTime = ZonedDateTime.now();
	}
}
