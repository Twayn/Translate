package org.service.translate.error;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Service response for malformed requests.
 * e.g. missing parameter or not supported language code.
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public class MalformedRequestMsg {
	@NotNull private final String msg;

	private static String missingParameter =
			"Parameter is missing: %s. Expected request format: /translate?text=text&from=from&to=to";
	private static String badLanguagePair =
			"Unsupported language pair: %s|%s";

	public static MalformedRequestMsg ofMissingPar(String par){
		return MalformedRequestMsg.of(String.format(missingParameter, par));
	}

	public static MalformedRequestMsg ofBadLangPair(String p1, String p2){
		return MalformedRequestMsg.of(String.format(badLanguagePair, p1, p2));
	}

	public static MalformedRequestMsg ofMsg(String msg){
		return MalformedRequestMsg.of(msg);
	}
}
