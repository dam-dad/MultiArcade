package dad.multiarcade.pong.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.multiarcade.mainapp.App;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PongController implements Initializable {

	@FXML
	private AnchorPane root;

	@FXML
	private Label scoreBotLabel;

	@FXML
	private Label scorePlayerLabel;

	Stage stage;
	Rectangle player, bot;
	Circle ball;
	Line line;
	AnimationTimer timer;
	private final int WIDTH = 1000, HEIGHT = 400;
	private int speedX = 1, speedY = 1, dv = speedX, dy = speedY;

	public PongController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PongView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		newGame();
		timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				gameUpdate();
				score();

			}
		};

		stage = new Stage();

		stage.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			if ((key.getCode() == KeyCode.UP) || (key.getCode() == KeyCode.W)) {
				if (player.getLayoutY() <= 0) {
					player.setLayoutY(player.getLayoutY());
				} else
					player.setLayoutY(player.getLayoutY() - 20);
			}
			if (key.getCode() == KeyCode.DOWN || (key.getCode() == KeyCode.S)) {
				if (player.getLayoutY() + 80 >= HEIGHT) {
					player.setLayoutY(player.getLayoutY());
				} else
					player.setLayoutY(player.getLayoutY() + 20);
			}
			if (key.getCode() == KeyCode.N) {
				reset();
			}

		});
		stage.setTitle("Pong");
		stage.setScene(new Scene(getRoot()));
		stage.setResizable(false);
		stage.getIcons().add(new Image("/img/logo_pong.png"));
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				reset();
				timer.stop();
			}
		});

	}

	/**
	 * Asignar bordes de la escena para los rebotes de la bola y el bot.
	 */
	private void gameUpdate() {

		double x = ball.getLayoutX();
		double y = ball.getLayoutY();

		if (x <= 10 && y > bot.getLayoutY() && y < bot.getLayoutY() + 80) {
			dv = speedX;
		}
		if (x >= WIDTH - 12.5 && y > player.getLayoutY() && y < player.getLayoutY() + 80) {
			speedX++;
			dv = -speedX;
		}
		if (y <= 0) {
			dy = speedY;
		}
		if (y >= HEIGHT - 5) {
			dy = -speedY;
		}

		// direccion Pelota
		ball.setLayoutX(ball.getLayoutX() + dv);
		ball.setLayoutY(ball.getLayoutY() + dy);

		// movimiento del bot
		if (x < WIDTH / 2 && bot.getLayoutY() > y) {
			bot.setLayoutY(bot.getLayoutY() - 5);
		}
		if (x < WIDTH / 2 && bot.getLayoutY() + 80 < y) {
			bot.setLayoutY(bot.getLayoutY() + 5);
		}
	}

	/**
	 * Marcar puntos y marcador.
	 */
	private void score() {
		if (ball.getLayoutX() >= WIDTH) {
			speedX = 1;
			speedY = 1;
			dv = speedX;
			dy = speedY;
			ball.setLayoutX(WIDTH / 2);
			ball.setLayoutY(HEIGHT / 2);

			bot.setLayoutY(HEIGHT / 2 - 40);
			player.setLayoutY(HEIGHT / 2 - 40);

			int scoreBot = Integer.parseInt(scoreBotLabel.getText());
			scoreBot++;
			scoreBotLabel.setText("" + scoreBot);

		}
		if (ball.getLayoutX() < -15) {
			speedX = 1;
			speedY = 1;
			dv = speedX;
			dy = speedY;

			ball.setLayoutX(WIDTH / 2);
			ball.setLayoutY(HEIGHT / 2);

			bot.setLayoutY(HEIGHT / 2 - 40);
			player.setLayoutY(HEIGHT / 2 - 40);

			int scorePlayer = Integer.parseInt(scorePlayerLabel.getText());
			scorePlayer++;
			scorePlayerLabel.setText("" + scorePlayer);

		}
	}

	/**
	 * Genera una nueva partida e inicializa todo en su lugar.
	 */
	private void newGame() {
		line = new Line(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
		line.setStroke(Color.WHITE);

		bot = new Rectangle(10, 80, Color.RED);
		bot.setLayoutX(0);
		bot.setLayoutY(HEIGHT / 2 - 40);

		player = new Rectangle(10, 80, Color.GREENYELLOW);
		player.setLayoutX(WIDTH - 10);
		player.setLayoutY(HEIGHT / 2 - 40);

		ball = new Circle(5);
		ball.setFill(Color.WHITE);
		ball.setLayoutX(WIDTH / 2);
		ball.setLayoutY(HEIGHT / 2);
		speedX = 1;
		speedY = 1;

		root.getChildren().addAll(line, bot, player, ball);

	}

	/**
	 * Reinicia la partida
	 */
	private void reset() {
		scorePlayerLabel.setText("0");
		scoreBotLabel.setText("0");

		bot.setLayoutX(0);
		bot.setLayoutY(HEIGHT / 2 - 40);

		player.setLayoutX(WIDTH - 10);
		player.setLayoutY(HEIGHT / 2 - 40);

		ball.setLayoutX(WIDTH / 2);
		ball.setLayoutY(HEIGHT / 2);
		speedX = 1;
		speedY = 1;
		dv = speedX;
		dy = speedY;
	}

	// getters
	public AnchorPane getRoot() {
		return root;
	}

	public Stage getStage() {
		return stage;
	}

	public void show() {
		stage.show();
		timer.start();

	}

}
