import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class View extends BorderPane implements Observer {


	//The components of the view
	private Group spielGroup = new Group();
	private Scene spielScene = new Scene(spielGroup);
	private HBox listBoxSpieler = new HBox();
	private HBox contBox = new HBox();
	private HBox buttonBox = new HBox(5);
	private HBox backBox = new HBox();
	private VBox allBox = new VBox();
	private ListView<String> alleSpieler = new ListView<>();
	private Button spielerAdd = new Button("Hinzufügen");
	private Button spielerDelete = new Button("Löschen");
	private Button cont = new Button("Weiter");
	private Button closePopup = new Button("  OK  ");
	protected TextField namenEingabe = new TextField();

	static final String buttonIdspielerAdd = "Hinzufügen";
	static final String buttonIdspielerDelete = "Löschen";
	static final String buttonIdcont = "Weiter";
	static final String buttonIdClosePopup = "closePopup";

	Stage popup = new Stage();
	private Group popupGroup = new Group();
	private Scene popupScene = new Scene(popupGroup);
	private Text message = new Text("Something went wrong.");
	private VBox popupMiddleBox = new VBox(15);

	Stage spielerAuswahl = new Stage();
	private Group spielerAuswahlGroup = new Group();
	private Scene spielerAuswahlScene = new Scene(spielerAuswahlGroup);


	public View(Stage background) {
		background.setTitle("KingX"); // Fenstertitel
		spielerAuswahl.setTitle("KingX: Spielerauswahl"); // Fenstertitel
		Image backpic = new Image("file:///C://Users//Pascal//IdeaProjects//KingX//KingX//Pictures//Background.png");
		ImageView iv1 = new ImageView();
		iv1.setImage(backpic);

		// IDs setzen für EventHandler:
		spielerAdd.setId(buttonIdspielerAdd);
		spielerAdd.setOnAction((ActionEvent event) -> {
			String c = namenEingabe.getText();
			if (!(c.equals(""))) {
				alleSpieler.getItems().add(alleSpieler.getItems().size(), c);
				alleSpieler.scrollTo(alleSpieler.getItems().size() - 1);
			}
			else {
				this.setMessage("Kein Name eingegeben.");
				popup.show();
			}
				});

		spielerDelete.setId(buttonIdspielerDelete);
		spielerDelete.setOnAction((ActionEvent event) -> {
			final int selectedIdx = alleSpieler.getSelectionModel().getSelectedIndex();
			if (selectedIdx != -1) {
				final int newSelectedIdx =
						(selectedIdx == alleSpieler.getItems().size() - 1)
								? selectedIdx - 1
								: selectedIdx;
				alleSpieler.getSelectionModel().select(newSelectedIdx);
				alleSpieler.getItems().remove(selectedIdx);
			}
			else {
				this.setMessage("Kein Spieler ausgewählt.");
				popup.show();
			}
				});

		cont.setId(buttonIdcont);
		cont.setOnAction((ActionEvent event) -> {
			int AnzahlSpieler = alleSpieler.getItems().size();
			if (AnzahlSpieler != 0) {
				if (AnzahlSpieler != 1) {
					String[] NamensListe = new String[AnzahlSpieler];
					try (FileOutputStream fo = new FileOutputStream("AktuelleSpieler.xml");
						 XMLEncoder encoder = new XMLEncoder(fo)) {
						encoder.writeObject(NamensListe); // write Object
						encoder.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					this.setMessage("Du willst alleine ein Trinkspiel spielen?");
					popup.show();
				}
			}
			else {
				this.setMessage("Man braucht SPIELER um ein SPIEL zu spielen.");
				popup.show();
			}
		});

		closePopup.setId(buttonIdClosePopup);

		contBox.setPadding(new Insets(5));
		buttonBox.setPadding(new Insets(3));

		buttonBox.getChildren().addAll(spielerAdd, spielerDelete, namenEingabe);
		contBox.getChildren().addAll(cont);
		buttonBox.setAlignment(Pos.TOP_CENTER);
		listBoxSpieler.getChildren().addAll(new VBox(new Text("Alle Spieler:"), alleSpieler));
		listBoxSpieler.setAlignment(Pos.CENTER_LEFT);
		contBox.setAlignment(Pos.BASELINE_CENTER);
		allBox.getChildren().addAll(buttonBox, contBox, listBoxSpieler);
		spielerAuswahlGroup.getChildren().addAll(allBox);

		backBox.getChildren().add(iv1);
		spielGroup.getChildren().addAll(backBox);

		background.setScene(spielScene);
		background.show();
		spielerAuswahl.setScene(spielerAuswahlScene);
		spielerAuswahl.show();

		popupMiddleBox.getChildren().addAll(message, closePopup);
		popupMiddleBox.setAlignment(Pos.CENTER);
		popupMiddleBox.setPadding(new Insets(10));
		popupGroup.getChildren().addAll(popupMiddleBox);
		popup.setScene(popupScene);



	}

	void setMessage(String s) {
		message.setText(s);
	}

	void addEventHandler(EventHandler<ActionEvent> eventHandler) {
		spielerAdd.addEventHandler(ActionEvent.ACTION, eventHandler);
	}

	public void update(Observable o, Object arg) {
	}
}