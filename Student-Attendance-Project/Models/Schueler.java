package Models;

public class Schueler {
	private String vorname;
	private String nachname;
	
	
	public Schueler(String vornamecon, String nachnamecon) {
		this.vorname = vornamecon;
		this.nachname = nachnamecon;
		
	}
	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String Vorname) {
		this.vorname = Vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String Nachname) {
		this.nachname = Nachname;
	}
	
}
