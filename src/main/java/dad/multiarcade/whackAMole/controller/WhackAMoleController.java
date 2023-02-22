package dad.multiarcade.whackAMole.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.multiarcade.whackAMole.model.Mole;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WhackAMoleController implements Initializable{
	
	Stage stage;

	@FXML
    private Mole cincoMole;

    @FXML
    private Mole cuatroMole;

    @FXML
    private Mole dosMole;

    @FXML
    private Mole nueveMole;

    @FXML
    private Mole ochoMole;

    @FXML
    private Label puntosLabel;

    @FXML
    private Mole seisMole;

    @FXML
    private Mole sieteMole;

    @FXML
    private Mole tresMole;

    @FXML
    private Mole unoMole;

    @FXML
    private BorderPane view;
	
	
	public WhackAMoleController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/WhackAMole.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		stage = new Stage();
		stage.setTitle("Whack A Mole");
		stage.setScene(new Scene(getView()));
		
	}
	
	public BorderPane getView() {
		return view;
	}
	
	public void show() {
		stage.show();

	}

}
