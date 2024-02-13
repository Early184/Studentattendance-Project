package Models;

import java.util.ArrayList;
import java.util.Comparator;

public class Schuelerliste {
	ArrayList <Schueler> schuelerliste;
	public String name;
	
	
	public Schuelerliste(String nameCon, ArrayList<Schueler> schuelerlisteCon) {
		name = nameCon;
		schuelerliste = schuelerlisteCon;

	}
	
	
	public void hinzufuegen(Schueler schueler) {
		schuelerliste.add(schueler);
	}
	public void entfernen(Schueler schueler) {
		schuelerliste.remove(schueler);
	}
	public void sortieren() {
		//Collections.sort(liste, );
		schuelerliste.sort(Comparator.comparing(Schueler -> Schueler.getVorname()));
	}
}
