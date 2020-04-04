package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		/*
		 * 	Write here your test model
		 */
		for (Corso c : model.getElencoCorsi())
			System.out.println(c);
	}

}
