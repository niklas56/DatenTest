/* Programm von Felix, Niklas, Till, Alexander H. */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.JSONArray;
import org.json.JSONObject;

public class QuizDateien {
	public static void main(String [] args) {
		JSONArray JA = new JSONArray();
		String [] arr = {"a", "b", "c", "d"};
		JSONObject frage01 = frage("xy", arr, 0, 30);
		JA.put(frage01);
		write(JA, "quiz01");
		JSONArray JA2 = new JSONArray(read(new File("quiz01.json")));
	}
	
	public static JSONObject frage(String frage, String [] antworten, int loesung, int zeit) {
		JSONObject JO = new JSONObject();
		JO.put("frage", frage);
		JO.put("antworten", antworten);
		JO.put("loesung", loesung);
		JO.put("zeit", zeit);
		return JO;
	}

	public static String read(File f) {
		try {
			return Files.readString(Path.of(f.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}return "";
	}
	
	public static void write(JSONArray JA, String name) {
		try(FileWriter fileWriter = new FileWriter(name+".json")) {            
			JA.write(fileWriter);
		    fileWriter.close(); 
		} catch (IOException e) { 
		    System.err.println("IOExeption");
		}
	}
}
