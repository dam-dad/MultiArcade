package dad.multiarcade.mainapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dad.multiarcade.buscaminas.controller.BuscaminasController;
import dad.multiarcade.memoria.controller.MemoriaController;
import dad.multiarcade.pong.controller.PongController;
import dad.multiarcade.snake.controller.SnakeController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Enrique Luque Pérez, Aarón Pérez Rodríguez y Borja Díaz Ramos
 *
 */
public class MainController implements Initializable {

	private final int TotalJuegos = 4;
	private int contador = 0;

	private SnakeController sc = new SnakeController();
	private PongController pc = new PongController();
	private BuscaminasController bc = new BuscaminasController();
	private MemoriaController mc = new MemoriaController();

	@FXML
	private Button buscaminasButton;

	@FXML
	private Button memoriaButton;

	@FXML
	private VBox buttonVbox;

	@FXML
	private Label multiArcadeLabel;

	@FXML
	private GridPane root;

	@FXML
	private Button salirButton;

	@FXML
	private Button snakeButton;

	@FXML
	private Button pongButton;

	@FXML
	private Button atrasButton;

	@FXML
	private Button siguienteButton;

	@FXML
	private Button jugarButton;

	@FXML
	private Label juegoLabel;

	@FXML
	private ImageView juegoImg;

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public GridPane getView() {
		return root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		atrasButton.setDisable(true);
		juegoLabel.setText("PONG");


	}

	@FXML
	public void onSiguienteAction(ActionEvent event) {
		contador++;
		atrasButton.setDisable(false);
		if (contador == TotalJuegos) {
			siguienteButton.setDisable(true);
		} else {
			siguienteButton.setDisable(false);
		}
		cambiarJuego();
	}

	@FXML
	public void onAtrasAction(ActionEvent event) {
		contador--;
		siguienteButton.setDisable(false);
		if (contador == 0) {
			atrasButton.setDisable(true);
		} else {
			atrasButton.setDisable(false);
		}
		cambiarJuego();
	}

	@FXML
	public void onJugarAction(ActionEvent event) {
		switch (contador) {
		case 0:
			pc.show();
			break;
		case 1:
			sc.show();
			break;
		case 2:
			bc.show();
			break;
		case 3:
			mc.show();
			break;
		}
	}

	@FXML
	void onBuscaminasAction(ActionEvent event) {
		bc.show();
	}

	@FXML
	void onSnakeAction(ActionEvent event) {
		sc.show();
	}

	@FXML
	void onPongAction(ActionEvent event) {
		pc.show();
	}

	@FXML
	void onMemoriaAction(ActionEvent event) {
		mc.show();
	}

	@FXML
	void onSalirAction(ActionEvent event) {
		System.exit(0);

	}
	
	public void cambiarJuego() {
		switch (contador) {
		case 0:
			juegoImg.setImage(new Image("/img/PongIMG.jpg"));
			juegoLabel.setText("PONG");
			break;
		case 1:
			juegoImg.setImage(new Image("/img/snakeIMG.png"));
			juegoLabel.setText("SNAKE");
			break;
		case 2:
			juegoImg.setImage(new Image("/img/buscaminasIMG.png"));
			juegoLabel.setText("BUSCAMINAS");
			break;
		case 3:
			juegoImg.setImage(new Image("/img/memoriaIMG.png"));
			juegoLabel.setText("MEMORIA");
			break;
		}
	}

}
