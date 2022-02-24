package dad.multiarcade.snake.model;

import java.util.Random;

import dad.multiarcade.snake.controller.SnakeController;

/**
 * 
 * @author Enrique Luque Pérez, Aarón Pérez Rodríguez y Borja Díaz Ramos
 *
 */
public class Food {

	private static int x;
	private static int y;
	static Random rand = new Random();

	/**
	 * Genera un círculo (comida) dentro de la escena y aumenta la velocidad.
	 */
	public static void newFood() {
		while (true) {
			x = rand.nextInt(SnakeController.getWidth());
			y = rand.nextInt(SnakeController.getHeight());

			for (Body c : SnakeController.getSnake()) {
				if (c.getX() == x && c.getY() == y) {
					continue;
				}
			}
			SnakeController.setFoodcolor(rand.nextInt(5));
			SnakeController.setSpeed(SnakeController.getSpeed() + 1);
			break;

		}
	}

	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		Food.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		Food.y = y;
	}

}
