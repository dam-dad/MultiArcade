package dad.multiarcade.whackAMole.model;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class Mole extends BorderPane implements Initializable {

	private BooleanProperty desactivadoProperty = new SimpleBooleanProperty(true);
	private IntegerProperty puntosProperty = new SimpleIntegerProperty();

	@FXML
    private Button moleButton;

    @FXML
    private ImageView moleImg;

	public Mole() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Mole.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.setDisable(true);
		this.disableProperty().bindBidirectional(desactivadoProperty);
		desactivadoProperty.addListener((o, ov, nv) -> onActivar(o, ov, nv));
	}

	 @FXML
	    void onMoleAction(ActionEvent event) {
		puntosProperty.add(1);
		this.setDisable(true);
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

	public final BooleanProperty desactivadoPropertyProperty() {
		return this.desactivadoProperty;
	}

	public final boolean getDesactivadoProperty() {
		return this.desactivadoPropertyProperty().get();
	}

	public final void setDesactivadoProperty(final boolean desactivadoProperty) {
		this.desactivadoPropertyProperty().set(desactivadoProperty);
	}

}
