package com.sap.cloudwave.caspott.DataObjectsHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ModelProvider {

	static ModelLogData logData;

	private static List<CodeArtifact> codeArtifacts;

	public static List<CodeArtifact> getCodeArtifacts() {
		return codeArtifacts;
	}

	public static void addCodeArtifacts(RawLogData rawLogData) {

		ModelLogData logData = getModelDataFromRawData(rawLogData);

		codeArtifacts = new ArrayList<CodeArtifact>();

		for (ModelLogDataHandler temp : logData.logdata) {
			long averageResponseTime = (temp.get_source()).getResponseTime() / (temp.get_source()).getNumberOfCalls();

			codeArtifacts.add(new CodeArtifact((temp.get_source()).getProjectName(), (temp.get_source()).getClassName(),
					(temp.get_source()).getProcedureName(), (temp.get_source()).getLineNumber(), averageResponseTime,
					(temp.get_source()).getNumberOfCalls(), (temp.get_source()).getNumberOfErrors()));
		}

	}

	private static ModelLogData getModelDataFromRawData(RawLogData rawLogData) {
		ModelLogData modelLogData = new ModelLogData();

		List<ModelLogDataHandler> modelLogDataHandlerList = new ArrayList<ModelLogDataHandler>();
		Map<String, Long> codeArtifactNoOfCallsMap = new HashMap<String, Long>();
		Map<String, Long> codeArtifactResponseTimeMap = new HashMap<String, Long>();
		Map<String, ModelSource> codeArtifactModelSourceMap = new HashMap<String, ModelSource>();

		for (RawLogDataHandler temp : rawLogData.getRawLogdata()) {

			String message = (temp.get_source()).getMessage();
			String[] parsedMsg = message.split(",");

			ModelSource modelSource = new ModelSource();
			// TODO: Hardcoded but has to be fixed
			modelSource.setProjectName("java-auth");
			modelSource.setClassName(parsedMsg[0].substring(parsedMsg[0].indexOf("@Class: ") + "@Class: ".length()));
			// TODO: Hardcoded but has to be fixed
			modelSource.setProcedureName(parsedMsg[1].substring("@Procedure: ".length()));
			modelSource.setLineNumber(1);
			Long responseTime = Long.parseLong(parsedMsg[2].substring("@ResponseTime: ".length()));
			// modelSource.setNumberOfErrors(Long.parseLong(parsedMsg[6].substring("@NumberOfErrors:
			// ".length() + 1)));

			Long numberOfCalls = null;

			String fullCodeArtifactName = getFullArtifactName(modelSource);
			if (codeArtifactNoOfCallsMap.containsKey(fullCodeArtifactName)) {
				numberOfCalls = codeArtifactNoOfCallsMap.get(fullCodeArtifactName) + 1;
				responseTime = codeArtifactResponseTimeMap.get(fullCodeArtifactName) + responseTime;
			} else {
				numberOfCalls = (long) 1;
			}
			codeArtifactNoOfCallsMap.put(fullCodeArtifactName, numberOfCalls);
			codeArtifactResponseTimeMap.put(fullCodeArtifactName, responseTime);
			modelSource.setNumberOfCalls(numberOfCalls);
			modelSource.setResponseTime(responseTime);
			codeArtifactModelSourceMap.put(fullCodeArtifactName, modelSource);
		}

		for (Entry<String, ModelSource> modelSourceEntry : codeArtifactModelSourceMap.entrySet()) {
			ModelLogDataHandler modelLogDataHandler = new ModelLogDataHandler();
			modelLogDataHandler.set_source(modelSourceEntry.getValue());
			modelLogDataHandlerList.add(modelLogDataHandler);
		}

		modelLogData.setLogdata(modelLogDataHandlerList);

		return modelLogData;
	}

	/**
	 * Concatenates the project name, class name and the procedure name of the
	 * given model
	 * 
	 * @param modelSource
	 *            a model source
	 * @return Concatenation of the project name, class name and the procedure
	 *         name of the given model
	 */
	private static String getFullArtifactName(ModelSource modelSource) {
		return modelSource.getProjectName().concat(modelSource.getClassName()).concat(modelSource.getProcedureName());
	}

	public static void parseJSON(String JSON) {
		try {

			System.out.println(JSON);

			GsonBuilder profileJSON = new GsonBuilder();
			profileJSON.setPrettyPrinting().serializeNulls();
			Gson gson = profileJSON.create();
			// Gson gson = new Gson();
			// JsonReader.setLenient(true);

			// logData = gson.fromJson(JSON, ModelLogDataHandler.class);
			logData = gson.fromJson(JSON, ModelLogData.class);

			System.out.println("Printing the Object");
			System.out.println(logData);
			// addCodeArtifacts(logData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
