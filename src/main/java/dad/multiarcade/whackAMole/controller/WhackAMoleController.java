package dad.multiarcade.whackAMole.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.multiarcade.whackAMole.model.Mole;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class WhackAMoleController implements Initializable {

	public static Mole agujero[][] = new Mole[3][3];
	private IntegerProperty puntosProperty = new SimpleIntegerProperty();
	Stage stage;

	@FXML
	private Label puntosLabel;

	@FXML
	private BorderPane view;

	@FXML
	private GridPane moleGridPane;

	@FXML
	private Button empezarButton;


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
		stage.getIcons().add(new Image("/img/whack_icon.png"));
		stage.setScene(new Scene(getView()));

		puntosLabel.textProperty().bindBidirectional(puntosProperty, new NumberStringConverter());

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				final int a = i;
				final int b = j;
				agujero[i][j] = new Mole();
				agujero[i][j].setPrefSize(45, 45);
				agujero[i][j].setearImagen();
				agujero[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent e) {
						if (e.getButton() == MouseButton.PRIMARY && agujero[a][b].isActivado() == true) {
							agujero[a][b].setMoleImg(new Image("/img/WhackAMole/agujero.jpg"));
							agujero[a][b].setActivado(false);
							puntosProperty.set(puntosProperty.getValue()+1);
						}
					}
				});
				moleGridPane.add(agujero[i][j], i, j);

			}
		}

	}

	@FXML
	void onEmpezarAction(ActionEvent event) {
		WhackIniciarThread thread = new WhackIniciarThread();
		thread.run();
	}

	public void finPartida() {
		List<String> choices = new ArrayList<String>();
		choices.add("Nueva partida");
		choices.add("Cerrar el juego");

		ChoiceDialog<String> dialog = new ChoiceDialog<String>("Nueva partida", choices);
		dialog.setTitle("Fin de la partida");
		dialog.setHeaderText("Ha obtenido un total de: " + puntosProperty.get() + " puntos.");
		dialog.setContentText("¿Qué desea hacer?");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			if (result.get().compareTo("Nueva partida") == 0) {
				WhackIniciarThread thread = new WhackIniciarThread();
				thread.run();
			} else {
				stage.close();
			}
		} else {
			stage.close();
		}
	}

	public BorderPane getView() {
		return view;
	}

	public void show() {
		stage.show();
	}

	public void iniciar() {

	}

	public final IntegerProperty puntosPropertyProperty() {
		return this.puntosProperty;
	}

	public final int getPuntosProperty() {
		return this.puntosPropertyProperty().get();
	}

	public final void setPuntosProperty(final int puntosProperty) {
		this.puntosPropertyProperty().set(puntosProperty);
	}

}
