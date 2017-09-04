import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
	public ArrayList<String> NamensListe = new ArrayList<>();
	public ArrayList<Integer> Regelzaehler = new ArrayList<>();
	int[] RegelZahlen = new int[20];

	public Model() {
		super();
		NamensListe = new ArrayList<>();
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

		// // Serialize ////
		try (FileOutputStream fo = new FileOutputStream("Spielernamen.xml");
			 XMLEncoder encoder = new XMLEncoder(fo)) {
			encoder.writeObject(NamensListe); // write Object
			encoder.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	String savePreset(String presetname, int[] zahlen) {

		// // Serialize ////
		try (FileOutputStream fo = new FileOutputStream(presetname+".xml");
			 XMLEncoder encoder = new XMLEncoder(fo)) {
			encoder.writeObject(zahlen); // write Object
			encoder.flush();
			return "Speichern erfolgreich!";
		} catch (IOException e) {
			e.printStackTrace();
			return "Beim Speichern ist ein Ups passiert :-(";
		}
	}

}


