package org.service.translate.error;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Service response for malformed requests.
 * e.g. missing parameter or not supported language code.
 */
@Getter
@Setter
@AllArgsConstructor
class MalformedRequestMsg {
	@NotNull private final String error;
}
