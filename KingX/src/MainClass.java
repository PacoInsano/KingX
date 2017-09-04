import javafx.application.Application;
import javafx.stage.Stage;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		Model model = new Model();
		View view = new View(primaryStage);
		Controller controller = new Controller();
		controller.link(model, view);
		loadOldList(model, view);
	}
	public void loadOldList(Model model, View view) {
		// // Deserialize ////

		model.NamensListe = null;

		try (FileInputStream fi = new FileInputStream("Spielernamen.xml");
			 XMLDecoder decoder = new XMLDecoder(fi)) {
			model.NamensListe = (ArrayList<String>) decoder.readObject(); // Read Object
			final int i = model.NamensListe.size();
			for (int j = 0; j < i; j++) {
				view.alleSpieler.getItems().add(view.alleSpieler.getItems().size(), model.NamensListe.get(j));
				view.alleSpieler.scrollTo(view.alleSpieler.getItems().size() - 1);
			}
		} catch (NullPointerException | IOException e) {
			view.setMessage("Keine alte Spieler-Liste gefunden, erstelle eine neue!");
			view.popup.show();
		}
	}

}
