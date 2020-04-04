package it.polito.tdp.lab04.model;

import java.util.List;
import it.polito.tdp.lab04.DAO.*;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
	}
	
	public List<Corso> getElencoCorsi() {
		return corsoDao.getTuttiICorsi();
	}
	
	public List<Studente> getElencoStudenti() {
		return studenteDao.getTuttiGliStudenti();
	}
	
	public Studente restituisciStudente(int matricola) {
    	for (Studente s : this.getElencoStudenti())
    		if (s.getMatricola() == matricola)
    			return s;
    	return null;
	}
	
	public List<Studente> getIscrittiCorso(Corso corso) {
		return corsoDao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiStudente(Studente studente) {
		return studenteDao.getCorsoStudente(studente);
	}
	
	public boolean esisteStudente(int matricola) {
		return studenteDao.esisteStudente(matricola);
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return corsoDao.inscriviStudenteACorso(studente, corso);
	}
}
