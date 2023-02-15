package dad.multiarcade.whackAMole.model;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mole extends Button implements Initializable {

	private BooleanProperty desactivadoProperty = new SimpleBooleanProperty(true);
	private IntegerProperty puntosProperty = new SimpleIntegerProperty();

	private Button moleButton;

	private ImageView moleImg;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		moleButton.setDisable(true);
		moleImg = new ImageView(new Image("/img/agujero.jpg"));
		moleButton.setGraphic(moleImg);
		this.disableProperty().bindBidirectional(desactivadoProperty);
		desactivadoProperty.addListener((o, ov, nv) -> onActivar(o, ov, nv));

		moleButton.setOnAction(e -> onMoleAction(e));

	}

	public void onMoleAction(ActionEvent e) {
		puntosProperty.add(1);
		moleButton.setDisable(true);
	}

	public Object onActivar(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv) {

		if (desactivadoProperty.get() == true) {
			moleImg.setImage(new Image("/img/agujero.jpg"));
		} else {
			moleImg.setImage(new Image("/img/peepoSad.png"));
		}
		return null;
	}

	public final IntegerProperty puntosPropertyProperty() {
		return this.puntosProperty;
	}

	public final int getPuntosProperty() {
		return this.puntosPropertyProperty().get();
	}

	public final void setPuntosProperty(final int puntosProperty) {
		this.puntosPropertyProperty().set(puntosProperty);
	}

	public final BooleanProperty activadoPropertyProperty() {
		return this.desactivadoProperty;
	}

	public final boolean getActivadoProperty() {
		return this.activadoPropertyProperty().get();
	}

	public final void setActivadoProperty(final boolean activadoProperty) {
		this.activadoPropertyProperty().set(activadoProperty);
	}

}
