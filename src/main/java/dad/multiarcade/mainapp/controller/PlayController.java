package dad.multiarcade.mainapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.multiarcade.mainapp.*;
import dad.multiarcade.tetris.controller.TetrisController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlayController implements Initializable {

	private Stage stage;

	TetrisController guiController = new TetrisController();

	@FXML
	private Button buscaminasButton;

	@FXML
	private Button juego3Button;

	@FXML
	private Button juego4Button;

	@FXML
	private VBox rootPlay;

	@FXML
	private Button tetrisButton;

	@FXML
	private Button volverButton;

	public PlayController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainFXML/PlayView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public VBox getView() {
		return rootPlay;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stage = new Stage();
		stage.setTitle("Jugar");

		stage.setScene(new Scene(getView()));
		stage.getIcons().add(new Image("/img/logo_arcade.png"));
		stage.initOwner(App.getPrimaryStage());
		stage.initModality(Modality.APPLICATION_MODAL);

	}

	public void show() {
		stage.showAndWait();

	}

	@FXML
	void onBuscaminasAction(ActionEvent event) {
		// stage.close();
	}

	@FXML
	void onTetrisAction(ActionEvent event) {
		guiController.show();
	}

	@FXML
	void onVolverAction(ActionEvent event) {
		stage.close();

	}

}
