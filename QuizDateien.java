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
		JSONObject frage02 = frage("xyz", arr, 2, 25);
		JSONObject frage03 = frage("xyzas", arr, 2, 25);
		JSONObject frage04 = frage("xyaskjdakzgsdz", arr, 2, 25);
		JA.put(frage01);
		JA.put(frage02);
		JA.put(frage03);
		JA.put(frage04);
		write(JA, "quiz01");
		System.out.print(JA.toString());
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
	
	public static void write(JSONArray frage, String name) {
		try(FileWriter fileWriter = new FileWriter(name+".json")) {            
			frage.write(fileWriter);
		    fileWriter.close(); 
		} catch (IOException e) { 
		    System.err.println("IOExeption");
		}
	}
}
