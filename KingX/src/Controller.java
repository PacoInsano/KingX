import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;


public class Controller implements EventHandler<javafx.event.ActionEvent> {

	private Model model;
	private ViewSpieler view;
	
	public Controller() { 
		
	}

	/**
	 * Reads the data from the view and adds it to the model. 
	 */


	public void link(Model model, ViewSpieler view){
		this.model=model;
		this.view=view;
		this.model.addObserver(view);
		view.addEventHandler(this);
		
	}
	public void handle(ActionEvent event) {
		String o = "XX";    // button ID
		Object eventSource = event.getSource();
		if (eventSource.getClass().equals(Button.class)) {  // Event stammt von einem Button
			Button eventButton = (Button) eventSource;
			o = eventButton.getId();
		}
		switch (o) {
			case ViewSpieler.buttonIdspielerAdd:   // eingegebenen Namen eintragen
				commitButtonClicked();
				break;

			case ViewSpieler.buttonIdspielerDelete:

			case ViewSpieler.buttonIdClosePopup:
				view.popup.close();
				break;
		}
	}
	public void commitButtonClicked(){

	}
}