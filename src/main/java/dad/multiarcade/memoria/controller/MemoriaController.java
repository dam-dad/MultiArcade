package dad.multiarcade.memoria.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import dad.multiarcade.memoria.model.Imagen;

/**
 * 
 * @author Enrique Luque Pérez, Aarón Pérez Rodríguez y Borja Díaz Ramos
 *
 */
public class MemoriaController implements Initializable {

	@FXML
	private AnchorPane root;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	private static final int CARD_SIZE = 200;

	private static final int NUM_X_CARDS = WIDTH / CARD_SIZE;
	private static final int NUM_Y_CARDS = HEIGHT / CARD_SIZE;

	private static final int NUM_CARDS = NUM_X_CARDS * NUM_Y_CARDS;
	private static final int NUM_OF_PAIRS = NUM_CARDS / 2;

	static ArrayList<Imagen> imagenes = new ArrayList<Imagen>();
	static ArrayList<Imagen> imagenes2 = new ArrayList<Imagen>();

	private static Imagen segundaImagen;
	static int NUM_CLICKS = 2;
	Stage stage;

	public MemoriaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MemoriaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		iniciar();

		stage = new Stage();
		stage.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			if (key.getCode() == KeyCode.N) {
				reset();
			}
		});
		stage.setTitle("Memoria");
		stage.setScene(new Scene(getRoot()));
		stage.setResizable(false);
		stage.getIcons().add(new Image("/img/memoria_logo.png"));
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				reset();

			}
		});
	}

	/**
	 * Mezcla el array de Imagen y ajusta el tamaño de la vista .
	 */
	public void shuffle() {

		Collections.shuffle(imagenes);

		for (int i = 0; i < imagenes.size(); i++) {
			imagenes.get(i).setTranslateX(CARD_SIZE * (i % NUM_X_CARDS));
			imagenes.get(i).setTranslateY(CARD_SIZE * (i / NUM_Y_CARDS));
			root.getChildren().add(imagenes.get(i));
		}

	}

	/**
	 * Inserta imágenes predeterminadas a un array de la clase Imagen.
	 */
	public static void ponerCartas() {

		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/chica.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/chica.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/chico.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/chico.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/mesa.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/mesa.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/peon.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/peon.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/reina.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/reina.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/rey.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/rey.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/tablero.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/tablero.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/torre.png")));
		MemoriaController.getImagenes().add(new Imagen(new Image("/img/memoria/torre.png")));

		MemoriaController.getImagenes().get(0).setId("chica");
		MemoriaController.getImagenes().get(1).setId("chica");
		MemoriaController.getImagenes().get(2).setId("chico");
		MemoriaController.getImagenes().get(3).setId("chico");
		MemoriaController.getImagenes().get(4).setId("mesa");
		MemoriaController.getImagenes().get(5).setId("mesa");
		MemoriaController.getImagenes().get(6).setId("peon");
		MemoriaController.getImagenes().get(7).setId("peon");
		MemoriaController.getImagenes().get(8).setId("reina");
		MemoriaController.getImagenes().get(9).setId("reina");
		MemoriaController.getImagenes().get(10).setId("rey");
		MemoriaController.getImagenes().get(11).setId("rey");
		MemoriaController.getImagenes().get(12).setId("tablero");
		MemoriaController.getImagenes().get(13).setId("tablero");
		MemoriaController.getImagenes().get(14).setId("torre");
		MemoriaController.getImagenes().get(15).setId("torre");

	}

	/**
	 * Elimina el progreso y empieza de nuevo.
	 */
	public void reset() {
		MemoriaController.getImagenes().clear();
		MemoriaController.getImagenes2().clear();
		for (int i = 0; i < MemoriaController.getImagenes().size(); i++) {
			MemoriaController.getImagenes().get(i).close();
		}
		iniciar();
	}

	/**
	 * Inicia el programa.
	 */
	public void iniciar() {
		ponerCartas();
		shuffle();
	}

	/**
	 * Muestra el juego.
	 */
	public void show() {
		stage.show();
	}

	public static ArrayList<Imagen> getImagenes() {
		return imagenes;
	}

	public static void setImagenes(ArrayList<Imagen> imagenes) {
		MemoriaController.imagenes = imagenes;
	}

	public static ArrayList<Imagen> getImagenes2() {
		return imagenes2;
	}

	public static void setImagenes2(ArrayList<Imagen> imagenes2) {
		MemoriaController.imagenes2 = imagenes2;
	}

	public static Imagen getSegundaImagen() {
		return segundaImagen;
	}

	public static void setSegundaImagen(Imagen segundaImagen) {
		MemoriaController.segundaImagen = segundaImagen;
	}

	public static int getNUM_CLICKS() {
		return NUM_CLICKS;
	}

	public static void setNUM_CLICKS(int nUM_CLICKS) {
		MemoriaController.NUM_CLICKS = nUM_CLICKS;
	}

	public AnchorPane getRoot() {
		return root;
	}

	public void setRoot(AnchorPane root) {
		this.root = root;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

	public static int getCardSize() {
		return CARD_SIZE;
	}

	public static int getNumXCards() {
		return NUM_X_CARDS;
	}

	public static int getNumYCards() {
		return NUM_Y_CARDS;
	}

	public static int getNumCards() {
		return NUM_CARDS;
	}

	public static int getNumOfPairs() {
		return NUM_OF_PAIRS;
	}

}
