package org.springframework.samples.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

@SpringBootApplication
@ImportRuntimeHints(PetClinicRuntimeHints.class)
public class PetClinicApplication {

	public static void main(String[] args) {
		loadDbSecretsFromFile();

		SpringApplication.run(PetClinicApplication.class, args);
	}

	private static void loadDbSecretsFromFile() {
		setPropFromFile("DB_HOST", "DB_HOST_FILE");
		setPropFromFile("DB_USER", "DB_USER_FILE");
		setPropFromFile("DB_PASSWORD", "DB_PASSWORD_FILE");
		setPropFromFile("DB_NAME", "DB_NAME_FILE");
	}

	private static void setPropFromFile(String propName, String fileEnv) {
		String filePath = System.getenv(fileEnv);

		if (filePath == null) {
			System.err.println("Env not found: " + fileEnv);
			return;
		}

		try {
			String value = Files.readString(Path.of(filePath)).trim();
			System.setProperty(propName, value);
			System.out.println("Loaded " + propName + " from " + filePath);
		}
		catch (IOException e) {
			System.err.println("Failed to read " + filePath + ": " + e.getMessage());
		}
	}
}
