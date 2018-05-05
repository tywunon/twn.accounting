package twn.accounting.res;

import java.io.InputStream;
import java.net.URL;

public class ResourceLoader {	
	public static URL load(String resourceName){
		return ResourceLoader.class.getResource(resourceName);
	}

	public static InputStream loadAsStream(String resourceName){
		return ResourceLoader.class.getResourceAsStream(resourceName);
	}
	
	public static InputStream icons8_bearbeiten(){
		return loadAsStream("icons8_bearbeiten.png");
	}
	
	public static InputStream icons8_bestellung(){
		return loadAsStream("icons8_bestellung.png");
	}
	
	public static InputStream icons8_drucken(){
		return loadAsStream("icons8_drucken.png");
	}
	
	public static InputStream icons8_fenster_schließen(){
		return loadAsStream("icons8_fenster_schließen.png");
	}
	
	public static InputStream icons8_kontobuch(){
		return loadAsStream("icons8_kontobuch.png");
	}
		
	public static InputStream icons8_lkw(){
		return loadAsStream("icons8_lkw.png");
	}
	public static InputStream icons8_müll(){
		return loadAsStream("icons8_müll.png");
	}
	public static InputStream icons8_neu_erstellen(){
		return loadAsStream("icons8_neu_erstellen.png");
	}
	public static InputStream icons8_ordner_öffnen(){
		return loadAsStream("icons8_ordner_öffnen.png");
	}
	
	public static InputStream icons8_rechnung(){
		return loadAsStream("icons8_rechnung.png");
	}
	public static InputStream icons8_speichern_als(){
		return loadAsStream("icons8_speichern_als.png");
	}
	public static InputStream icons8_speichern(){
		return loadAsStream("icons8_speichern.png");
	}
	public static InputStream icons8_stornieren(){
		return loadAsStream("icons8_stornieren.png");
	}
	public static InputStream icons8_vergangenheit(){
		return loadAsStream("icons8_vergangenheit.png");
	}
	public static InputStream icons8_webcam_mann(){
		return loadAsStream("icons8_webcam_mann.png");
	}
	public static InputStream tywunon_trans_png(){
		return loadAsStream("tywunon_trans.png");
	}
	
}