package it.polito.tdp.dizionariograph;


import it.polito.tdp.dizionariograph.model.Model;

import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLettere;

    @FXML
    private TextField txtParole;

    @FXML
    private TextArea txtResult;

    @FXML
    void btGradoMax(ActionEvent event) {  	
    	txtResult.appendText(model.findMaxDegree());
    }

    @FXML
    void btnGraph(ActionEvent event) {
    	int numL=0;
    	try {
			numL=Integer.parseInt(txtLettere.getText());
		} catch (NumberFormatException e) {
			txtResult.appendText("Inserire un numero valido nel campo lettere!\n");
			return;
		}
    	
    	model.createGraph(numL);
    	txtResult.appendText("Grafo creato!\n");

    }

    @FXML
    void btnReset(ActionEvent event) {
    	txtResult.clear();
    	txtLettere.clear();
    	txtParole.clear();

    }

    @FXML
    void btnVicini(ActionEvent event) {
    	if(!txtParole.getText().matches("^[a-zA-z]+$")) {
    		txtResult.appendText("Devi inserire una parola valida!\n");
    		return;
    	}
    	List<String> ris=model.displayNeighbours(txtParole.getText());
    	txtResult.appendText("I vicini della parola "+txtParole.getText()+" sono:\n");
    	txtResult.appendText(ris.toString()+"\n");
    }

    @FXML
    void initialize() {
        assert txtLettere != null : "fx:id=\"txtLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParole != null : "fx:id=\"txtParole\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }
    
    public void setModel(Model model) {
		this.model=model;
	}
}

