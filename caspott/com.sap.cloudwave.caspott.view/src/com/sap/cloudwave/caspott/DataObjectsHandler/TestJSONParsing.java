package com.sap.cloudwave.caspott.DataObjectsHandler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestJSONParsing {

	static ModelLogDataHandler logData;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String JSON;
		try {
			JSON = readFile("C:/EclipseMars64/eclipse/workspace/com.sap.cloudwave.caspott.view/src/input.json",
					StandardCharsets.UTF_8);

			System.out.println(JSON);

			GsonBuilder profileJSON = new GsonBuilder();
			profileJSON.setPrettyPrinting().serializeNulls();
			Gson gson = profileJSON.create();
			// Gson gson = new Gson();
			// JsonReader.setLenient(true);

			logData = gson.fromJson(JSON, ModelLogDataHandler.class);

			System.out.println("Printing the Object");
			System.out.println(logData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
