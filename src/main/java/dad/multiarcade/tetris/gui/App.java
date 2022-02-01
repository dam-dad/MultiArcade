package dad.multiarcade.tetris.gui;

import dad.multiarcade.tetris.app.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TetrisResources/gameLayout.fxml"));
		Parent root = fxmlLoader.load();
		GuiController c = fxmlLoader.getController();

		primaryStage.setTitle("TetrisJFX");
		Scene scene = new Scene(root, 400, 510);
		primaryStage.setScene(scene);
		primaryStage.show();
		new GameController(c);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
