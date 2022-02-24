package dad.multiarcade.buscaminas.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.multiarcade.buscaminas.model.Mina;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * 
 * @author Enrique Luque Pérez, Aarón Pérez Rodríguez y Borja Díaz Ramos
 *         <p>
 *         Controlador del Buscaminas
 *         </p>
 */
public class BuscaminasController implements Initializable {

	@FXML
	private BorderPane root;

	private GridPane view;

	// root
	private static int filas = 10;
	private static int columnas = 10;
	private static int numeroMinas = 12;
	private Mina casilla[][] = new Mina[columnas][filas];
	Stage stage;
	Scene scene;

	public BuscaminasController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BuscaminasView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		view = new GridPane();
		view.setStyle(getClass().getResource("/css/BuscaminasCSS.css").toExternalForm());
		view.setAlignment(Pos.CENTER);
		root.setCenter(view);
		stage = new Stage();
		// creamos escena y asignamos a escenario
		stage.setTitle("Buscaminas");
		stage.setScene(new Scene(root));
		stage.getIcons().add(new Image("/img/buscaminas_mina.png"));
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				jugarPartida();
			}
		});

	}

	/**
	 * Inicia la partida añadiendo a cada ranura de un GridPane un botón de tipo
	 * Mina y colocando las minas en lugares aleatorios.
	 */
	public void jugarPartida() {
		// Inserci�n de botones
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				casilla[i][j] = new Mina();
				casilla[i][j].setPrefSize(45, 45);
				view.add(casilla[i][j], i, j);

			}
		}

		// Creamos minas aleatorias
		int minaFila;
		int minaColumna;

		for (int i = 0; i < numeroMinas; i++) {
			do {
				minaFila = (int) (Math.random() * filas);
				// System.out.println("Fila"+minaFila);
				minaColumna = (int) (Math.random() * columnas);
				// System.out.println("Columna: "+minaColumna);
			} while (casilla[minaColumna][minaFila].isEsMina() == true);

			casilla[minaColumna][minaFila].setEsMina(true);
		}

		// Comprobar si es mina
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				final int a = i;
				final int b = j;
				casilla[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {

					public void handle(MouseEvent e) {
						if (e.getButton() == MouseButton.PRIMARY && casilla[a][b].getEstado() == 0) {
							actualizarMina(a, b);
							casilla[a][b].setCasillaVisible(true);
							;
							calcularResultado();
						} else if (e.getButton() == MouseButton.SECONDARY && !casilla[a][b].isCasillaVisible()) {
							marcarMina(a, b);
						}
					}
				});
			}
		}
	}

	/**
	 * Comprueba si la casilla seleccionada es una mina o no.
	 * 
	 * @param a número entero que indica la columna de la casilla.
	 * @param b número entero que indica la fila de la casilla.
	 */
	public void actualizarMina(int a, int b) {
		if (casilla[a][b].isEsMina() == true) {
			for (int i = 0; i < columnas; i++) {
				for (int j = 0; j < filas; j++) {
					if (casilla[i][j].isEsMina() == true) {
						ImageView imageView = new ImageView();
						imageView.setPreserveRatio(true);
						imageView.setFitWidth(25);
						imageView.setImage(new Image("/img/buscaminas_mina.png"));
						casilla[i][j].setGraphic(imageView);

					}
				}
			}
			finPartida("Pisaste una mina, ¡HAS PERDIDO!");
		} else {
			casilla[a][b].setStyle("-fx-background-color: #c7caf3;");
			contarAlrededor(a, b);
			casilla[a][b].setText(Integer.toString(casilla[a][b].getContador()));
		}
	}

	/**
	 * Muestra o elimina la bandera y la interrogación en el caso correspondiente.
	 * 
	 * @param i número entero que indica la columna de la casilla.
	 * @param j número entero que indica la fila de la casilla.
	 */
	public void marcarMina(int i, int j) {
		ImageView imageView = new ImageView();
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(25);
		casilla[i][j].setGraphic(imageView);
		switch (casilla[i][j].getEstado()) {
		case 0:
			imageView.setImage(new Image("/img/buscaminas_bandera.png"));
			casilla[i][j].cambiarEstado();
			break;
		case 1:
			imageView.setImage(new Image("/img/buscaminas_interrogacion.png"));
			casilla[i][j].cambiarEstado();
			break;
		case 2:
			casilla[i][j].setGraphic(null);
			casilla[i][j].setStyle("");
			casilla[i][j].cambiarEstado();
			break;
		}
	}

	/**
	 * Cuenta las minas que se encuentran alrededor de la casilla.
	 * 
	 * @param columna número entero que indica la columna de la casilla.
	 * @param fila    número entero que indica la fila de la casilla.
	 */
	public void contarAlrededor(int columna, int fila) {
		casilla[columna][fila].setContador(0);
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				if ((i == columna || i == (columna - 1) || i == (columna + 1))
						&& (j == fila || j == (fila - 1) || j == (fila + 1))) {
					if (casilla[i][j].isEsMina()) {
						casilla[columna][fila].setContador(casilla[columna][fila].getContador() + 1);
					}
				}
			}
		}

//		 System.out.println(casilla[columna][fila].getContador());

		/*
		 * if (casilla[columna][fila].getContador() == 0) { // expandirse(columna,fila);
		 * for (int i = 0; i < columnas; i++) { for (int j = 0; j < filas; j++) { if ((i
		 * == columna || i == (columna - 1) || i == (columna + 1)) && (j == fila || j ==
		 * (fila - 1) || j == (fila + 1))) {
		 * 
		 * for (int i1 = i - 1; i1 < i + 1; i1++) { for (int j1 = j - 1; j1 < j + 1;
		 * j++) { if (casilla[i1][j1].getContador() == 0) { if
		 * (casilla[i1][j1].isEsMina()) {
		 * casilla[i][j].setContador(casilla[i][j].getContador() + 1); } } } } } } } }
		 */
	}

//	public static void expandirse(int y, int x) {
//		for (int i = 0; i < columnas; i++) {
//			for (int j = 0; j < filas; j++) {
//				if ((i == y || i == (y - 1) || i == (y + 1)) && (j == x || j == (x - 1) || j == (x + 1))) {
//					
//					  if(!(i == y && j == x)) contarAlrededor(i, j);
//					 
//
//				}
//			}
//		}
//	}
	/**
	 * Cuenta las casillas despejadas para saber si todavía quedan más.
	 */
	public void calcularResultado() {
		int contador = 0;
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				if (casilla[i][j].isCasillaVisible()) {
					contador++;
				}
			}
		}
		if ((columnas * filas - contador - numeroMinas) == 0) {
			finPartida("Has conseguido evitar todas las minas. ¡¡ENHORABUENA!!");
		}
	}

	/**
	 * Muestra la alerta que indica el final de la partida
	 * 
	 * @param mensaje puede ser el mensaje de victoria o el de derrota.
	 */
	private void finPartida(String mensaje) {
		List<String> choices = new ArrayList<String>();
		choices.add("Nueva partida");
		choices.add("Cerrar el juego");

		ChoiceDialog<String> dialog = new ChoiceDialog<String>("Nueva partida", choices);
		dialog.setTitle("Fin de la partida");
		dialog.setHeaderText(mensaje);
		dialog.setContentText("¿Qué desea hacer?");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			if (result.get().compareTo("Nueva partida") == 0) {
				jugarPartida();
			} else {
				stage.close();
				jugarPartida();
			}
		} else {
			stage.close();
			jugarPartida();
		}
	}

	/**
	 * Devuelve la vista del GridPane.
	 * 
	 * @return GridPane con la vista.
	 */
	public GridPane getView() {
		return view;
	}

	public Stage getStage() {
		// TODO Auto-generated method stub
		return stage;
	}

	/**
	 * Devuelve la vista del BorderPane.
	 * 
	 * @return BorderPane con la vista.
	 */
	public BorderPane getRoot() {
		return root;
	}

	/**
	 * Muestra y da inicio al juego.
	 */
	public void show() {
		jugarPartida();
		stage.show();

	}

}
