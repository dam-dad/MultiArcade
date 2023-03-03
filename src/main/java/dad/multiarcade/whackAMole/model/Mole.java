package dad.multiarcade.whackAMole.model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mole extends Button{

	private boolean activado= false;
	private ImageView moleImg;
	//private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

	public void setearImagen() {
		//moleImg.setImage(new Image("/img/WhackAMole/agujero.jpg"));
		moleImg = new ImageView(Mole.class.getResource("/img/WhackAMole/agujero.jpg").toExternalForm());
		moleImg.maxHeight(45);
		moleImg.maxWidth(45);
		moleImg.setPreserveRatio(true);
		this.setGraphic(moleImg);
	}

	public boolean isActivado() {
		return activado;
	}

	public void setActivado(boolean activado) {
		this.activado = activado;
	}

	public Image getMoleImg() {
		return moleImg.getImage();
	}

	public void setMoleImg(Image moleImg) {
		this.moleImg.setImage(moleImg);
	}

}
