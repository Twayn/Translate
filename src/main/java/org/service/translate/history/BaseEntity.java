package org.service.translate.history;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
class BaseEntity {
	@Id
	@GeneratedValue
	@Getter
	@Setter
	private Long id;
}