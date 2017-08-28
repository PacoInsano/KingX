import javafx.application.Application;
import javafx.stage.Stage;

public class MainClass extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		Model model = new Model();
		ViewSpieler view = new ViewSpieler(primaryStage);
		Controller controller = new Controller();
		controller.link(model, view);
	}

}
