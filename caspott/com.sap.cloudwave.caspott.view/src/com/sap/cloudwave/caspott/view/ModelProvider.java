package com.sap.cloudwave.caspott.view;

import java.util.ArrayList;
import java.util.List;

public enum ModelProvider {
	INSTANCE;

	private List<Person> persons;
	private List<CodeArtifact> codeArtifacts;

	private ModelProvider() {
		persons = new ArrayList<Person>();
		// Image here some fancy database access to read the persons and to
		// put them into the model
		persons.add(new Person("Rainer", "Zufall", "male", true));
		persons.add(new Person("Reiner", "Babbel", "male", true));
		persons.add(new Person("Marie", "Dortmund", "female", false));
		persons.add(new Person("Holger", "Adams", "male", true));
		persons.add(new Person("Juliane", "Adams", "female", true));

		codeArtifacts = new ArrayList<CodeArtifact>();
		codeArtifacts.add(new CodeArtifact("Class:TestFunction:getPurchaseOrder", 2344, 10));
		codeArtifacts.add(new CodeArtifact("Function:getPurchaseOrerItems", 52, 2));
		codeArtifacts.add(new CodeArtifact("Function:getCustomers", 500, 1));
		codeArtifacts.add(new CodeArtifact("Function:getVendors", 645, 58));
	}

	public List<Person> getPersons() {
		return persons;
	}

	public List<CodeArtifact> getCodeArtifacts() {
		return codeArtifacts;
	}

}
