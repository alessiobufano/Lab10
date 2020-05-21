package it.polito.tdp.bar;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;
    

    @FXML
    void doSimulation(ActionEvent event) {

    	this.model.simulate();
    	this.txtResult.setText(this.model.getSimulationResults());
    }
    
    @FXML
    void doOptimizedSimulation(ActionEvent event) {

    	this.model.optmizeSimulation();
    	this.txtResult.appendText("\n"+this.model.getSimulationResults());
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}

