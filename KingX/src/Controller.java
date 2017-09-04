import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;


public class Controller implements EventHandler<javafx.event.ActionEvent> {

	private Model model;
	private View view;
	
	public Controller() { 
		
	}

	/**
	 * Reads the data from the view and adds it to the model. 
	 */


	public void link(Model model, View view){
		this.model=model;
		this.view=view;
		this.model.addObserver(view);
		view.addEventHandler(this);
		
	}
	@Override
	public void handle(ActionEvent event) {
		String o = "XX";    // button ID
		Object eventSource = event.getSource();
		if (eventSource.getClass().equals(Button.class)) {  // Event stammt von einem Button
			Button eventButton = (Button) eventSource;
			o = eventButton.getId();
		}
		switch (o) {
			case View.buttonIdspielerAdd:
				//Hinzufügen-Button:
				String c = view.namenEingabe.getText();
				if (!(c.equals(""))) {
					view.alleSpieler.getItems().add(view.alleSpieler.getItems().size(), c);
					view.alleSpieler.scrollTo(view.alleSpieler.getItems().size() - 1);
					model.addSpieler(c);
				} else {
					view.setMessage("Kein Name eingegeben.");
					view.popup.show();
				}
				break;
			case View.buttonIdspielerDelete:
				//Löschen-Button:
				final int selectedIdx = view.alleSpieler.getSelectionModel().getSelectedIndex();
				if (selectedIdx != -1) {
					final int newSelectedIdx =
							(selectedIdx == view.alleSpieler.getItems().size() - 1)
									? selectedIdx - 1
									: selectedIdx;
					view.alleSpieler.getSelectionModel().select(newSelectedIdx);
					view.alleSpieler.getItems().remove(selectedIdx);
					model.deleteSpieler(selectedIdx);
				} else {
					view.setMessage("Kein Spieler ausgewählt.");
					view.popup.show();
				}
				break;
			case View.buttonIdClosePopup:
				//Ok-Button:
				okButtonClicked();
				break;
			case View.buttonIdspielerCont:
				//Weiter-Button:
				int AnzahlSpieler = view.alleSpieler.getItems().size();
				if (AnzahlSpieler != 0) {
					if (AnzahlSpieler != 1) {
						model.saveSpieler();
						view.spielerAuswahl.close();
						view.ViewRegeln();
					} else {
						view.setMessage("Du willst alleine ein Trinkspiel spielen?");
						view.popup.show();
					}
				} else {
					view.setMessage("Man braucht SPIELER um ein SPIEL zu spielen.");
					view.popup.show();
				}
				break;
			case View.buttonIdregelnSave:
				//preset speichern:
				String presetEingabe = view.RegelListenPreset.getText();
				if (!(presetEingabe.equals(""))) {
					try {
						view.getPresetZahlen();
						view.setMessage(model.savePreset(presetEingabe, view.getPresetZahlen()));
						view.popup.show();
					} catch(NullPointerException e) {
						view.setMessage("Es müssen ZAHLEN für die Regeln eingegeben werden!");
						view.popup.show();
					} catch(NumberFormatException e) {
						view.setMessage("Es müssen ZAHLEN für die Regeln eingegeben werden!");
						view.popup.show();
					}
				}
				else {
					view.setMessage("Du musst dem Preset doch schon einen Namen geben :-(");
					view.popup.show();
				}

		}
	}
	public void okButtonClicked(){
			view.popup.close();
	}
}