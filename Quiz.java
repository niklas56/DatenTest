import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Autoren: Felix, Niklas, Till, Alexander H
 * Anfangsdatum: 14.02.2023
 * 
 * Funktion der Klasse:
 * Diese Klasse stellt einen Datentypen zur Repraesentation von Quizzen dar.
 * Dabei ist ein Quiz prinzipiell als Array aus Fragen zur verstehen, wobei eine Frage
 * durch einen Text, die Antwortmoeglichkeiten, die Loesung und das Zeitlimit charakterisiert wird.
 * Um das Speichern und Versenden von Quizzen zu erreichen wird hier auf das 
 * JSON-Datenformat zurückgegriffen.
 */

public class Quiz {
    //Das Fragen-Array ist ein Attribut der Quiz-Klasse
    private JSONArray fragen;

    //Konstruktor zum Erstellen von einem leeren Quiz.
    //Dieser wird bei der neuen Erstellung eines Quiz gebraucht.
    public Quiz() {
        fragen = new JSONArray();
    }

    //Dieser Konstruktor generiert basierend auf einer bestehenden Quiz-Datei ein neues Objekt.
    //Hiermit wird das Laden von vorhandene Quiz realisiert.
    public Quiz(File quizDatei) {
        try {
			String JSONtext = Files.readString(Path.of(quizDatei.getPath()));
            
            fragen = new JSONArray(JSONtext);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    //Mit dieser Methode kann dem Quiz(JSONArray) eine neue Frage (JSONObject) angefuegt
    //Die Methode bekommt dafuer die oben beschriebenen Charakteristika uebergeben.
    public void addFrage(String text, String [] antworten, int loesung, int zeit) {
        JSONObject frage = new JSONObject();
        frage.put("text", text);
		frage.put("antworten", antworten);
		frage.put("loesung", loesung);
		frage.put("zeit", zeit);
        fragen.put(frage);
    }
    
    //Diese Methode speichert das Quiz (letztlich wird das JSONObject als String repraesentiert) als .json-Datei.
    //Das uebergebene File-Objekt wird durch den Nutzer ausgewaehlt, wenn dieser einen Speicherort auswaehlt.
    public void save(File file) {
		try {
            FileWriter fileWriter = new FileWriter(file);            
			fragen.write(fileWriter);
		    fileWriter.close(); 
		} catch (IOException e) { 
		    e.printStackTrace();
		}
	}

    public JSONObject getFrage(int index) {
        return (JSONObject) fragen.get(index);
    }

    public int getLength() {
        return fragen.length();
    }
}
