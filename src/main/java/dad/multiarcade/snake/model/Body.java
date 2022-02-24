package dad.multiarcade.snake.model;

/**
 * 
 * @author Enrique Luque Pérez, Aarón Pérez Rodríguez y Borja Díaz Ramos
 *
 */
public class Body {
	private int x;
	private int y;

	public Body(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
