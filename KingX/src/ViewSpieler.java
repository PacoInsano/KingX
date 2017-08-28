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

import java.util.Observable;
import java.util.Observer;


public class ViewSpieler extends BorderPane implements Observer {


	//The components of the view
	private Group spielerGroup = new Group();
	private Scene spielerScene = new Scene(spielerGroup);
	private HBox listBoxSpieler = new HBox();
	private HBox contBox = new HBox();
	private HBox buttonBox = new HBox(5);
	private VBox allBox = new VBox();
	private ListView<String> alleSpieler = new ListView<>();
	private Button spielerAdd = new Button("Hinzufügen");
	private Button spielerDelete = new Button("Löschen");
	private Button cont = new Button("Weiter");
	private Button closePopup = new Button("  OK  ");
	protected TextField namenEingabe = new TextField();
	private ListView<String> spielerNamen = new ListView<>();

	static final String buttonIdspielerAdd = "Hinzufügen";
	static final String buttonIdspielerDelete = "Löschen";
	static final String buttonIdcont = "Weiter";
	static final String buttonIdClosePopup = "closePopup";

	Stage popup = new Stage();
	private Group popupGroup = new Group();
	private Scene popupScene = new Scene(popupGroup);
	private Text message = new Text("Something went wrong.");
	private VBox popupMiddleBox = new VBox(15);

	public ViewSpieler(Stage spielerAuswahl) {
		spielerAuswahl.setTitle("KingX: Spielerauswahl"); // Fenstetitel

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
		spielerGroup.getChildren().addAll(allBox);

		spielerAuswahl.setScene(spielerScene);
		spielerAuswahl.show();

		popupMiddleBox.getChildren().addAll(message, closePopup);
		popupMiddleBox.setAlignment(Pos.CENTER);
		popupMiddleBox.setPadding(new Insets(10));
		popupGroup.getChildren().addAll(popupMiddleBox);
		popup.setScene(popupScene);



	}
	public ListView<String> getSpielerNamen() {
		return spielerNamen;
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