package it.polito.tdp.lab04;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import it.polito.tdp.lab04.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> cbxCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnOk;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnClear;
    
    private ObservableList<Corso> list = FXCollections.observableArrayList();

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtRisultato.clear();
    	int matricola;
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	} catch (NumberFormatException e) {
    		txtRisultato.setText("Devi inserire una matricola");
    		return ;
    	}
    	
    	Studente s = new Studente(matricola);
    	if (!this.model.esisteStudente(matricola)) {
    		txtRisultato.setText("Matricola non esistente");
    		return ;
    	} else 
    		for(Corso c : this.model.getCorsiStudente(s))
    			txtRisultato.appendText(c.toLongerString() + "\n");
    	
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtRisultato.clear();
    	Corso c = cbxCorsi.getValue();
    	if (c.getNomeCorso().compareTo("") != 0) {
    		for(Studente s : this.model.getIscrittiCorso(c)) {
        		txtRisultato.appendText(s.toString()+"\n");
        	}
    	} else {
    		txtRisultato.setText("Corso non selezionato");
    	}
    }

    @FXML
    void doClear(ActionEvent event) {
    	txtCognome.clear();
    	txtNome.clear();
    	txtMatricola.clear();
    	txtRisultato.clear();
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	Corso c = cbxCorsi.getValue();
    	int matricola;
    	List<Integer> lista = new LinkedList<>();
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	} catch (NumberFormatException e) {
    		txtRisultato.setText("Devi inserire una matricola");
    		return ;
    	}
    	if (c.getNomeCorso().compareTo("") != 0) {
    		for(Studente s : this.model.getIscrittiCorso(c)) {
    			lista.add(s.getMatricola());
    		}
    	}
    	Studente s = this.model.restituisciStudente(matricola);
    	if (lista.contains(matricola)) {
    		txtRisultato.setText("Lo studente è già iscritto al corso");
    	} else {
    		if (this.model.inscriviStudenteACorso(s, c) == true)
    			txtRisultato.setText("Studente iscritto al corso!");
    	}
    }

    @FXML
    void doOk(ActionEvent event) {
    	
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	Studente s = this.model.restituisciStudente(matricola);
    	if (s != null) {
    		txtNome.setText(s.getNome());
        	txtCognome.setText(s.getCognome());
    	}
    	else
    		txtRisultato.setText("Matricola non trovata");
    }

    @FXML
    void initialize() {
        assert cbxCorsi != null : "fx:id=\"cbxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnOk != null : "fx:id=\"btnOk\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        list.addAll(this.model.getElencoCorsi());
    	cbxCorsi.setItems(list);
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }

}
