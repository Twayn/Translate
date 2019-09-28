package org.service.translate.translate;

import static com.google.auth.oauth2.GoogleCredentials.fromStream;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;

/**
 * Defines a {@link com.google.cloud.translate.Translate} bean
 * depends on {@code google.key.from.environment property}.
 * If property set, key is taken from environment variable "GOOGLE_APPLICATION_CREDENTIALS"
 * else from path specified in {@code google.key.path property}.
 */
@Configuration
@PropertySource("classpath:translate.properties")
public class TranslateConfig {
	private final String key;

	public TranslateConfig(@Value("${google.key.path}") String key){
		this.key = key;
	}

	@Bean
	@ConditionalOnProperty(prefix = "google.key.from",
						   value = "environment",
						   havingValue = "true",
						   matchIfMissing = true)
	Translate envVarKeyLocation() {
		return TranslateOptions.getDefaultInstance().getService();
	}

	@Bean
	@ConditionalOnProperty(prefix = "google.key.from",
						   value = "environment",
						   havingValue = "false")
	Translate systemPathKeyLocation() throws IOException {
		var builder = TranslateOptions.newBuilder();
		var credentials = fromStream(new FileInputStream(key))
				.createScoped("https://www.googleapis.com/auth/cloud-platform");
		builder.setCredentials(credentials);
		return builder.build().getService();
	}
}