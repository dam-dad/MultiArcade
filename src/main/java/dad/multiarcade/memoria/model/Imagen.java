package dad.multiarcade.memoria.model;

import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import dad.multiarcade.memoria.controller.MemoriaController;

/**
 * 
 * @author Enrique Luque Pérez, Aarón Pérez Rodríguez y Borja Díaz Ramos
 *         <p>
 *         Clase que hereda de javafx.scene.layout.StackPane
 *         </p>
 *
 */
public class Imagen extends StackPane {
	Image image;
	Rectangle perimeter;
	ImageView iv;
	MemoriaController mc;

	/**
	 * Constructor de la clase Imagen.
	 * 
	 * @param image ubicación de la imagen.
	 */
	public Imagen(Image image) {
		this.image = image;

		perimeter = new Rectangle(MemoriaController.getCardSize(), MemoriaController.getCardSize());
		perimeter.setStroke(Color.GRAY);
		perimeter.setStrokeWidth(3);

		getChildren().addAll(perimeter, iv = new ImageView(image));

		setOnMouseClicked(e -> {
			if (MemoriaController.getNUM_CLICKS() == 0
					|| MemoriaController.getImagenes().size() == MemoriaController.getImagenes2().size() || isOpen())
				return;
			MemoriaController.setNUM_CLICKS(MemoriaController.getNUM_CLICKS() - 1);

			// 1st click
			if (MemoriaController.getSegundaImagen() == null) {
				MemoriaController.setSegundaImagen(this);
				open(() -> {
				}); // open with empty runnable (dont do anything)
			}
			// 2nd click
			else {
				open(() -> {
					if (!hasSameInside(MemoriaController.getSegundaImagen())) {
						MemoriaController.getSegundaImagen().close();
						this.close();
					} else {
						MemoriaController.getImagenes2().add(this);
						MemoriaController.getImagenes2().add(MemoriaController.getSegundaImagen());

					}
					MemoriaController.setSegundaImagen(null);
					MemoriaController.setNUM_CLICKS(2);
				});
			}

		});

		close();
	}

	public boolean isOpen() {
		return this.perimeter.getFill() == Color.MEDIUMPURPLE;
	}

	/**
	 * Verifica si las dos casillas seleccionadas contienen la misma imagen.
	 * 
	 * @param secondClickedCard la segunda casilla seleccionada.
	 * @return true si son la misma, false si no.
	 */
	public boolean hasSameInside(Imagen secondClickedCard) {
		return (this.getId().equals(secondClickedCard.getId()));
	}

	/**
	 * Muestra el contenido de una casilla añadiéndole una transición en el proceso.
	 * 
	 * @param runnable
	 */
	public void open(Runnable runnable) {
		perimeter.setFill(Color.AQUAMARINE);
		FadeTransition ft = new FadeTransition(Duration.seconds(0.5), iv);
		ft.setToValue(1);
		ft.setOnFinished(e -> runnable.run());
		ft.play();
	}

	/**
	 * Vuelve a ocultar el contenido de una casilla añadiéndole una transición en el
	 * proceso.
	 */
	public void close() {
		perimeter.setFill(Color.DEEPSKYBLUE);
		FadeTransition ft = new FadeTransition(Duration.seconds(0.5), iv);
		ft.setToValue(0);
		ft.play();
	}

}
