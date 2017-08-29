import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
	public ArrayList<String> NamensListe = new ArrayList<>();

	public Model() {
		super();
		NamensListe = new ArrayList<String>();
	}



	public void addSpieler(String s) {
		NamensListe.add(s);

		// Mark as changed and inform the observers
		super.setChanged();
		super.notifyObservers();
	}





	public void deleteSpieler(int in) {
		NamensListe.remove(in);

		// Mark as changed and inform the observers
		super.setChanged();
		super.notifyObservers();
	}
	public void saveSpieler () {
		try (FileOutputStream fo = new FileOutputStream("Spielernamen.xml");
			 XMLEncoder encoder = new XMLEncoder(fo)) {
			encoder.writeObject(NamensListe); // write Object
			encoder.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


