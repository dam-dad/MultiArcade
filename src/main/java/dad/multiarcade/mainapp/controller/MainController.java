package dad.multiarcade.mainapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {

	PlayController playController = new PlayController();

	@FXML
	private VBox buttonVbox;

	@FXML
	private Button jugarButton;

	@FXML
	private Label multiArcadeLabel;

	@FXML
	private GridPane root;

	@FXML
	private Button salirButton;

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainFXML/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public GridPane getView() {
		return root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void onJugarAction(ActionEvent event) {
		playController.show();

	}

	@FXML
	void onSalirAction(ActionEvent event) {
		System.exit(0);

	}

}
