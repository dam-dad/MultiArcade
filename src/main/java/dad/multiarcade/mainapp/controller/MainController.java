package dad.multiarcade.mainapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.multiarcade.buscaminas.controller.BuscaminasController;
import dad.multiarcade.memoria.controller.MemoriaController;
import dad.multiarcade.pong.controller.PongController;
import dad.multiarcade.snake.controller.SnakeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {

	SnakeController sc = new SnakeController();
	PongController pc=new PongController();
	BuscaminasController bc =new BuscaminasController();
	MemoriaController mc=new MemoriaController();

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

}
