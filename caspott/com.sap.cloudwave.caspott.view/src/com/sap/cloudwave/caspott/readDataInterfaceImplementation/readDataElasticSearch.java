package com.sap.cloudwave.caspott.readDataInterfaceImplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sap.cloudwave.caspott.readDataInterface.CaspottDataInterface;

public class readDataElasticSearch implements CaspottDataInterface {

	public String readJSONData() {

		String link = "";
		String output = "", temp;
		URL url;
		try {
			url = new URL(link);
			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			if (conn.getResponseCode() != 200) {
				System.out.println("Failed : HTTP error code : " + conn.getResponseCode());
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				System.out.println("Output from Server .... \n");
				while ((temp = br.readLine()) != null) {
					output = output + temp;
				}
			}

			conn.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output;
	}

}
