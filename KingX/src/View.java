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
import java.util.*;


public class View extends BorderPane implements Observer {


	private Group spielGroup = new Group();
	private Scene spielScene = new Scene(spielGroup);

	//Komponenten Spielerauswahl
	Stage spielerAuswahl = new Stage();
	private Group spielerAuswahlGroup = new Group();
	private Scene spielerAuswahlScene = new Scene(spielerAuswahlGroup);
	private HBox listBoxSpieler = new HBox();
	private HBox contBox = new HBox();
	private HBox buttonBox = new HBox(5);
	private HBox backBox = new HBox();
	private VBox allBoxSpieler = new VBox();
	public ListView<String> alleSpieler = new ListView<>();
	private Button spielerAdd = new Button("Hinzufügen");
	private Button spielerDelete = new Button("Löschen");
	private Button contSpieler = new Button("Weiter");
	private Button closePopup = new Button("  OK  ");
	protected TextField namenEingabe = new TextField();

	static final String buttonIdspielerAdd = "Hinzufügen";
	static final String buttonIdspielerDelete = "Löschen";
	static final String buttonIdspielerCont = "Weiter";
	static final String buttonIdClosePopup = "OK";


	//Komponenten Regelauswahl
	Stage regelAuswahl = new Stage();
	private Group regelAuswahlGroup = new Group();
	private Scene regelAuswahlScene = new Scene(regelAuswahlGroup);
	protected TextField RegelListenPreset = new TextField();
	private HBox presetbuttonBox = new HBox(5);
	private int[] PresetZahlen = new int[20];

	private VBox contBoxRegeln = new VBox();
	private HBox Regel00Box = new HBox();
	private HBox Regel01Box = new HBox();
	private HBox Regel02Box = new HBox();
	private HBox Regel03Box = new HBox();
	private HBox Regel04Box = new HBox();
	private HBox Regel05Box = new HBox();
	private HBox Regel06Box = new HBox();
	private HBox Regel07Box = new HBox();
	private HBox Regel08Box = new HBox();
	private HBox Regel09Box = new HBox();
	private HBox Regel10Box = new HBox();
	private HBox Regel11Box = new HBox();
	private HBox Regel12Box = new HBox();
	private HBox Regel13Box = new HBox();
	private HBox Regel14Box = new HBox();
	private HBox Regel15Box = new HBox();
	private HBox Regel16Box = new HBox();
	private HBox Regel17Box = new HBox();
	private HBox Regel18Box = new HBox();
	private HBox Regel19Box = new HBox();
	private TextField Regel00Zahl = new TextField();
	private TextField Regel01Zahl = new TextField();
	private TextField Regel02Zahl = new TextField();
	private TextField Regel03Zahl = new TextField();
	private TextField Regel04Zahl = new TextField();
	private TextField Regel05Zahl = new TextField();
	private TextField Regel06Zahl = new TextField();
	private TextField Regel07Zahl = new TextField();
	private TextField Regel08Zahl = new TextField();
	private TextField Regel09Zahl = new TextField();
	private TextField Regel10Zahl = new TextField();
	private TextField Regel11Zahl = new TextField();
	private TextField Regel12Zahl = new TextField();
	private TextField Regel13Zahl = new TextField();
	private TextField Regel14Zahl = new TextField();
	private TextField Regel15Zahl = new TextField();
	private TextField Regel16Zahl = new TextField();
	private TextField Regel17Zahl = new TextField();
	private TextField Regel18Zahl = new TextField();
	private TextField Regel19Zahl = new TextField();
	private VBox listBoxRegeln = new VBox();
	private VBox allBoxRegeln = new VBox();

	private Button contRegeln = new Button("Los gehts!");
	private Button presetSaveRegeln = new Button("Regeln Speichern");
	private Button presetLoadRegeln = new Button("Regeln Laden");
	static final String buttonIdregelnSave = "Speichern";
	static final String buttonIdregelnLoad = "Laden";
	static final String buttonIdregelnCont = "Los gehts!";



	Stage popup = new Stage();
	private Group popupGroup = new Group();
	private Scene popupScene = new Scene(popupGroup);
	private Text message = new Text("Something went wrong.");
	private VBox popupMiddleBox = new VBox(15);





	public View(Stage background) {
		background.setTitle("KingX"); // Fenstertitel
		spielerAuswahl.setTitle("KingX: Spielerauswahl"); // Fenstertitel
		Image backpic = new Image("file:///C://Users//Pascal//IdeaProjects//KingX//KingX//Pictures//Background.png");
		ImageView iv1 = new ImageView();
		iv1.setImage(backpic);

		// IDs setzen für EventHandler:
		spielerAdd.setId(buttonIdspielerAdd);
		spielerDelete.setId(buttonIdspielerDelete);
		contSpieler.setId(buttonIdspielerCont);
		closePopup.setId(buttonIdClosePopup);

		contBox.setPadding(new Insets(5));
		buttonBox.setPadding(new Insets(3));

		buttonBox.getChildren().addAll(spielerAdd, spielerDelete, namenEingabe);
		contBox.getChildren().addAll(contSpieler);
		buttonBox.setAlignment(Pos.TOP_CENTER);
		listBoxSpieler.getChildren().addAll(new VBox(new Text("Alle Spieler:"), alleSpieler));
		listBoxSpieler.setAlignment(Pos.CENTER_LEFT);
		contBox.setAlignment(Pos.BOTTOM_CENTER);
		allBoxSpieler.getChildren().addAll(buttonBox, listBoxSpieler, contBox);
		spielerAuswahlGroup.getChildren().addAll(allBoxSpieler);

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


	public void ViewRegeln() {

		regelAuswahl.setTitle("KingX: Regelauswahl"); // Fenstertitel
		contRegeln.setId(buttonIdregelnCont);
		presetLoadRegeln.setId(buttonIdregelnLoad);
		presetSaveRegeln.setId(buttonIdregelnSave);
		contBoxRegeln.setPadding(new Insets(5));
		listBoxRegeln.setPadding(new Insets(3));

		presetbuttonBox.getChildren().addAll(RegelListenPreset,presetSaveRegeln,presetLoadRegeln);

		Regel00Zahl.setPrefWidth(20);
		Regel01Zahl.setPrefWidth(20);
		Regel02Zahl.setPrefWidth(20);
		Regel03Zahl.setPrefWidth(20);
		Regel04Zahl.setPrefWidth(20);
		Regel05Zahl.setPrefWidth(20);
		Regel06Zahl.setPrefWidth(20);
		Regel07Zahl.setPrefWidth(20);
		Regel08Zahl.setPrefWidth(20);
		Regel09Zahl.setPrefWidth(20);
		Regel10Zahl.setPrefWidth(20);
		Regel11Zahl.setPrefWidth(20);
		Regel12Zahl.setPrefWidth(20);
		Regel13Zahl.setPrefWidth(20);
		Regel14Zahl.setPrefWidth(20);
		Regel15Zahl.setPrefWidth(20);
		Regel16Zahl.setPrefWidth(20);
		Regel17Zahl.setPrefWidth(20);
		Regel18Zahl.setPrefWidth(20);
		Regel19Zahl.setPrefWidth(20);
		Regel00Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel01Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel02Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel03Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel04Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel05Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel06Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel07Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel08Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel09Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel10Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel11Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel12Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel13Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel14Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel15Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel16Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel17Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel18Zahl.setAlignment(Pos.CENTER_RIGHT);
		Regel19Zahl.setAlignment(Pos.CENTER_RIGHT);

		Regel00Box.getChildren().addAll(new Text("2 Schlücke verteilen  "),Regel00Zahl);
		Regel01Box.getChildren().addAll(new Text("3 Schlücke verteilen  "),Regel01Zahl);
		Regel02Box.getChildren().addAll(new Text("4 Schlücke verteilen  "),Regel02Zahl);
		Regel03Box.getChildren().addAll(new Text("5 Schlücke verteilen  "),Regel03Zahl);
		Regel04Box.getChildren().addAll(new Text("Buben trinken  "),Regel04Zahl);
		Regel05Box.getChildren().addAll(new Text("Damen trinken  "),Regel05Zahl);
		Regel06Box.getChildren().addAll(new Text("Kommunity trinkt  "),Regel06Zahl);
		Regel07Box.getChildren().addAll(new Text("Wie Vordermann trinken  "),Regel07Zahl);
		Regel08Box.getChildren().addAll(new Text("Trinkregel  "),Regel08Zahl);
		Regel09Box.getChildren().addAll(new Text("Shot trinken  "),Regel09Zahl);
		Regel10Box.getChildren().addAll(new Text("Shot verteilen  "),Regel10Zahl);
		Regel11Box.getChildren().addAll(new Text("Chinman  "),Regel11Zahl);
		Regel12Box.getChildren().addAll(new Text("Fragemeister  "),Regel12Zahl);
		Regel13Box.getChildren().addAll(new Text("Never Ever  "),Regel13Zahl);
		Regel14Box.getChildren().addAll(new Text("Schere Stein Papier  "),Regel14Zahl);
		Regel15Box.getChildren().addAll(new Text("Kategorie  "),Regel15Zahl);
		Regel16Box.getChildren().addAll(new Text("Richtungswechsel  "),Regel16Zahl);
		Regel17Box.getChildren().addAll(new Text("Wasserfall  "),Regel17Zahl);
		Regel18Box.getChildren().addAll(new Text("Trinkpartner  "),Regel18Zahl);
		Regel19Box.getChildren().addAll(new Text("Wort verbieten  "),Regel19Zahl);
		Regel00Box.setAlignment(Pos.CENTER);
		Regel01Box.setAlignment(Pos.CENTER);
		Regel02Box.setAlignment(Pos.CENTER);
		Regel03Box.setAlignment(Pos.CENTER);
		Regel04Box.setAlignment(Pos.CENTER);
		Regel05Box.setAlignment(Pos.CENTER);
		Regel06Box.setAlignment(Pos.CENTER);
		Regel07Box.setAlignment(Pos.CENTER);
		Regel08Box.setAlignment(Pos.CENTER);
		Regel09Box.setAlignment(Pos.CENTER);
		Regel10Box.setAlignment(Pos.CENTER);
		Regel11Box.setAlignment(Pos.CENTER);
		Regel12Box.setAlignment(Pos.CENTER);
		Regel13Box.setAlignment(Pos.CENTER);
		Regel14Box.setAlignment(Pos.CENTER);
		Regel15Box.setAlignment(Pos.CENTER);
		Regel16Box.setAlignment(Pos.CENTER);
		Regel17Box.setAlignment(Pos.CENTER);
		Regel18Box.setAlignment(Pos.CENTER);
		Regel19Box.setAlignment(Pos.CENTER);


		listBoxRegeln.getChildren().addAll(Regel00Box, Regel01Box, Regel02Box, Regel03Box, Regel04Box, Regel05Box, Regel06Box, Regel07Box, Regel08Box, Regel09Box, Regel10Box, Regel11Box, Regel12Box, Regel13Box,
				Regel14Box, Regel15Box, Regel16Box, Regel17Box, Regel18Box, Regel19Box);
		contRegeln.setAlignment(Pos.BOTTOM_CENTER);
		contBoxRegeln.getChildren().addAll(contRegeln);
		presetbuttonBox.setAlignment(Pos.TOP_CENTER);
		listBoxRegeln.setAlignment(Pos.CENTER);
		contBoxRegeln.setAlignment(Pos.BOTTOM_CENTER);
		allBoxRegeln.getChildren().addAll(presetbuttonBox,listBoxRegeln,contBoxRegeln);
		regelAuswahlGroup.getChildren().addAll(allBoxRegeln);


		regelAuswahl.setScene(regelAuswahlScene);
		regelAuswahl.show();
	}


	void setMessage(String s) {
		message.setText(s);
	}

	void addEventHandler(EventHandler<ActionEvent> eventHandler) {
		spielerAdd.addEventHandler(ActionEvent.ACTION, eventHandler);
		spielerDelete.addEventHandler(ActionEvent.ACTION, eventHandler);
		contSpieler.addEventHandler(ActionEvent.ACTION, eventHandler);
		closePopup.addEventHandler(ActionEvent.ACTION, eventHandler);
		contRegeln.addEventHandler(ActionEvent.ACTION, eventHandler);
		presetSaveRegeln.addEventHandler(ActionEvent.ACTION, eventHandler);
		presetLoadRegeln.addEventHandler(ActionEvent.ACTION, eventHandler);
	}

	int[] getPresetZahlen () {
			PresetZahlen[0] = Integer.parseInt(Regel00Zahl.getText());
			PresetZahlen[1] = Integer.parseInt(Regel01Zahl.getText());
			PresetZahlen[2] = Integer.parseInt(Regel02Zahl.getText());
			PresetZahlen[3] = Integer.parseInt(Regel03Zahl.getText());
			PresetZahlen[4] = Integer.parseInt(Regel04Zahl.getText());
			PresetZahlen[5] = Integer.parseInt(Regel05Zahl.getText());
			PresetZahlen[6] = Integer.parseInt(Regel06Zahl.getText());
			PresetZahlen[7] = Integer.parseInt(Regel07Zahl.getText());
			PresetZahlen[8] = Integer.parseInt(Regel08Zahl.getText());
			PresetZahlen[9] = Integer.parseInt(Regel09Zahl.getText());
			PresetZahlen[10] = Integer.parseInt(Regel10Zahl.getText());
			PresetZahlen[11] = Integer.parseInt(Regel11Zahl.getText());
			PresetZahlen[12] = Integer.parseInt(Regel12Zahl.getText());
			PresetZahlen[13] = Integer.parseInt(Regel13Zahl.getText());
			PresetZahlen[14] = Integer.parseInt(Regel14Zahl.getText());
			PresetZahlen[15] = Integer.parseInt(Regel15Zahl.getText());
			PresetZahlen[16] = Integer.parseInt(Regel16Zahl.getText());
			PresetZahlen[17] = Integer.parseInt(Regel17Zahl.getText());
			PresetZahlen[18] = Integer.parseInt(Regel18Zahl.getText());
			PresetZahlen[19] = Integer.parseInt(Regel19Zahl.getText());
			return PresetZahlen;
		}

		/* catch(NumberFormatException e) {
			setMessage("Es müssen ZAHLEN für die Regeln eingegeben werden!");
			popup.show();
			PresetZahlen[0] = 0;
			PresetZahlen[1] = 0;
			PresetZahlen[2] = 0;
			PresetZahlen[3] = 0;
			PresetZahlen[4] = 0;
			PresetZahlen[5] = 0;
			PresetZahlen[6] = 0;
			PresetZahlen[7] = 0;
			PresetZahlen[8] = 0;
			PresetZahlen[9] = 0;
			PresetZahlen[10] = 0;
			PresetZahlen[11] = 0;
			PresetZahlen[12] = 0;
			PresetZahlen[13] = 0;
			PresetZahlen[14] = 0;
			PresetZahlen[15] = 0;
			PresetZahlen[16] = 0;
			PresetZahlen[17] = 0;
			PresetZahlen[18] = 0;
			PresetZahlen[19] = 0;
			return PresetZahlen;
		} catch(NullPointerException e) {
			setMessage("Es müssen ZAHLEN für die Regeln eingegeben werden!");
			popup.show();
			PresetZahlen[0] = 0;
			PresetZahlen[1] = 0;
			PresetZahlen[2] = 0;
			PresetZahlen[3] = 0;
			PresetZahlen[4] = 0;
			PresetZahlen[5] = 0;
			PresetZahlen[6] = 0;
			PresetZahlen[7] = 0;
			PresetZahlen[8] = 0;
			PresetZahlen[9] = 0;
			PresetZahlen[10] = 0;
			PresetZahlen[11] = 0;
			PresetZahlen[12] = 0;
			PresetZahlen[13] = 0;
			PresetZahlen[14] = 0;
			PresetZahlen[15] = 0;
			PresetZahlen[16] = 0;
			PresetZahlen[17] = 0;
			PresetZahlen[18] = 0;
			PresetZahlen[19] = 0;
			return PresetZahlen;*/

	public void update(Observable o, Object arg) {
	}
}