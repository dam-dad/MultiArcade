package dad.multiarcade.mainapp;

import dad.multiarcade.mainapp.controller.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 
 * @author Enrique Luque Pérez, Aarón Pérez Rodríguez y Borja Díaz Ramos
 *
 */
public class App extends Application {
	private MainController mainController;

	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		App.primaryStage = primaryStage;

		mainController = new MainController();

		Scene scene = new Scene(mainController.getView());

		primaryStage.setTitle("MultiArcade");
		primaryStage.getIcons().add(new Image("/img/logo_arcade.png"));
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static Stage getPrimaryStage() {
		return primaryStage;

	}

	public static void main(String[] args) {
		launch(args);

	}

}
