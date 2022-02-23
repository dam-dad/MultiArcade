package dad.multiarcade.snake.controller;

import dad.multiarcade.informes.SnakeDataProvider;
import dad.multiarcade.snake.model.*;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class SnakeController implements Initializable {
	// variables
	private static int speed = 5;
	private static int foodColor = 0;
	private static int width = 20;
	private static int height = 20;
	private static int cornerSize = 25;
	private static List<Body> snake = new ArrayList<>();
	private Direccion direction = Direccion.WAIT;
	private boolean gameOver = false;
	private static int score;
	@FXML
	private Label gameOverLabel;

	@FXML
	private Canvas mainCanvas;

	@FXML
	private AnchorPane root;

	@FXML
	private Label scoreLabel;
	@FXML
	private Button exportScoreButton;

	public static final String JRXML_FILE = "/reports/snakeReport.jrxml";
	public static final String PDF_FILE = "/pdf/SnakeScoreReport.pdf";

	private Stage stage;
	private AnimationTimer timer;

	public SnakeController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SnakeView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public AnchorPane getView() {
		return root;
	}

	public void initialize(URL location, ResourceBundle resources) {

		Food.newFood();

		GraphicsContext gc = mainCanvas.getGraphicsContext2D();

		timer = new AnimationTimer() {
			long at = 0;

			public void handle(long now) {
				if (at == 0) {
					at = now;
					instrucciones(gc);
					gameOver();
					return;
				}

				if (now - at > 1000000000 / speed) {
					at = now;
					instrucciones(gc);
					gameOver();
				}
			}

		};
		stage = new Stage();

		// movimientos
		stage.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			if (key.getCode() == KeyCode.W || key.getCode() == KeyCode.UP) {
				setDirection(Direccion.UP);
			}
			if (key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) {
				setDirection(Direccion.LEFT);
			}
			if (key.getCode() == KeyCode.S || key.getCode() == KeyCode.DOWN) {
				setDirection(Direccion.DOWN);
			}
			if (key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) {
				setDirection(Direccion.RIGHT);
			}
			if (key.getCode() == KeyCode.N) {
				reset();
				iniciar();
			}
		});

		stage.setTitle("Snake");
		stage.setScene(new Scene(getView()));
		stage.getIcons().add(new Image("/img/snake_logo.png"));
		stage.setResizable(false);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				reset();
				timer.stop();

			}
		});

	}

	public void show() {
		iniciar();
		timer.start();
		stage.show();

	}

	public void instrucciones(GraphicsContext gc) {

		// codigo para que la cola te siga
		for (int i = snake.size() - 1; i >= 1; i--) {
			snake.get(i).setX(snake.get(i - 1).getX());
			snake.get(i).setY(snake.get(i - 1).getY());
		}

		// direcciones y bordes
		switch (direction) {
		case UP:
			snake.get(0).setY(snake.get(0).getY() - 1);
			if (snake.get(0).getY() < 0) {
				gameOver = true;

			}
			break;
		case DOWN:
			snake.get(0).setY(snake.get(0).getY() + 1);
			if (snake.get(0).getY() > 19) {
				gameOver = true;
			}
			break;
		case LEFT:
			snake.get(0).setX(snake.get(0).getX() - 1);
			if (snake.get(0).getX() < 0) {
				gameOver = true;
			}
			break;
		case RIGHT:
			snake.get(0).setX(snake.get(0).getX() + 1);
			if (snake.get(0).getX() > 19) {
				gameOver = true;
			}
			break;
		default:
			break;

		}

		// Codigo para comer y añadir nueva pieza
		if (Food.getX() == snake.get(0).getX() && Food.getY() == snake.get(0).getY()) {
			snake.add(new Body(-1, -1));
			Food.newFood();
			score++;
			scoreLabel.setText("" + score);
		}

		// Codigo para comerse a si mismo
		for (int i = 1; i < snake.size(); i++) {
			if (snake.get(0).getX() == snake.get(i).getX() && snake.get(0).getY() == snake.get(i).getY()) {
				gameOver = true;
			}
		}

		// fondo
		gc.setFill(Color.LIGHTGREY);
		gc.fillRect(0, 0, width * cornerSize + 70, height * cornerSize);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width * cornerSize, height * cornerSize);

		// Color de comida random
		Color cc = Color.WHITE;

		switch (foodColor) {
		case 0:
			cc = Color.AQUA;
			break;
		case 1:
			cc = Color.CRIMSON;
			break;
		case 2:
			cc = Color.LIGHTSEAGREEN;
			break;
		case 3:
			cc = Color.MEDIUMSLATEBLUE;
			break;
		case 4:
			cc = Color.PAPAYAWHIP;
			break;
		}
		gc.setFill(cc);
		gc.fillOval(Food.getX() * cornerSize, Food.getY() * cornerSize, cornerSize, cornerSize);

		// Color serpiente

		for (Body c : snake) {
			gc.setFill(Color.LIGHTGREEN);
			gc.fillRect(c.getX() * cornerSize, c.getY() * cornerSize, cornerSize - 1, cornerSize - 1);
			gc.setFill(Color.GREEN);
			gc.fillRect(c.getX() * cornerSize, c.getY() * cornerSize, cornerSize - 2, cornerSize - 2);

		}
		gc.setFill(Color.SALMON);
		gc.fillRect(snake.get(0).getX() * cornerSize, snake.get(0).getY() * cornerSize, cornerSize - 1, cornerSize - 1);
		gc.setFill(Color.RED);
		gc.fillRect(snake.get(0).getX() * cornerSize, snake.get(0).getY() * cornerSize, cornerSize - 2, cornerSize - 2);

	}

	@FXML
	void onExportScoreButton(ActionEvent event) throws JRException, IOException {
		JasperReport report = JasperCompileManager.compileReport(SnakeController.class.getResourceAsStream(JRXML_FILE));
		// mapa de parámetros para el informe
		Map<String, Object> parameters = new HashMap<String, Object>();
		

		// generamos el informe (combinamos el informe compilado con los datos)
		JasperPrint print = JasperFillManager.fillReport(report, parameters,
				new JRBeanCollectionDataSource(SnakeDataProvider.getScore()));

		// exporta el informe a un fichero PDF
		JasperExportManager.exportReportToPdfFile(print, PDF_FILE);

	}

	public void gameOver() {
		if (gameOver) {
			gameOverLabel.visibleProperty().set(true);
			timer.stop();
		}
	}

	public void iniciar() {
		score = 0;
		scoreLabel.setText("" + score);
		snake.add(new Body(10, 10));
	}

	public void reset() {
		speed = 5;
		setDirection(Direccion.WAIT);
		gameOver = false;
		gameOverLabel.visibleProperty().set(false);
		snake.clear();

	}

	public int getCornersize() {
		return cornerSize;
	}

	public Direccion getDirection() {
		return direction;
	}

	public void setDirection(Direccion direction) {
		this.direction = direction;
	}

	public static List<Body> getSnake() {
		return snake;
	}

	public int getFoodcolor() {
		return foodColor;
	}

	public static void setFoodcolor(int foodcolor) {
		SnakeController.foodColor = foodcolor;
	}

	public void setSnake(List<Body> snake) {
		SnakeController.snake = snake;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		SnakeController.speed = speed;
	}

	public static int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		SnakeController.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		SnakeController.height = height;
	}

	public static int getScore() {
		return score;
	}

	public void setScore(int score) {
		SnakeController.score = score;
	}

}
