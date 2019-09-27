package org.service.translate.translate;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;

@Configuration
public class TranslateConfig {

	@Bean(name = "defaultTranslate")
	Translate getDefault(){
		return TranslateOptions.getDefaultInstance().getService();
	}

	@Bean(name = "translateWithKey")
	Translate getWithKey() throws IOException {
		String pathToKey = "C:\\dev\\abc.json";

		TranslateOptions.Builder builder = TranslateOptions.newBuilder();

		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(pathToKey))
				.createScoped("https://www.googleapis.com/auth/cloud-platform");

		builder.setCredentials(credentials);

		return builder.build().getService();
	}
}