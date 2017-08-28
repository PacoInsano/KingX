import javax.swing.text.html.ListView;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
	private ArrayList<String> strings;

	public Model() {
		super();
		strings = new ArrayList<String>();
	}

	public void addString(String s) {
		strings.add(s);

		// Mark as changed and inform the observers
		super.setChanged();
		super.notifyObservers();
	}


	public void delete() {
		strings.clear();

		// Mark as changed and inform the observers
		super.setChanged();
		super.notifyObservers();
	}
	public void saveSpieler (ListView sl) {
		try ( FileOutputStream fo = new FileOutputStream ( "Spielernamen.xml " ) ;
		XMLEncoder encoder = new XMLEncoder (fo))
		{
			encoder.writeObject(sl);
			encoder.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
