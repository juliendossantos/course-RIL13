import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MonJSONArray {

	public static void main(String[] args) throws JSONException {
		JSONObject etudiant = new JSONObject();
		etudiant.put("nom", "Dos Santos");
		etudiant.put("prenom", "Julien");

		JSONArray notes = new JSONArray();
		notes.put(12.3);
		notes.put(15);
		notes.put(16);

		etudiant.put("notes", notes);
		System.out.println(etudiant);
		JSONArray n = (JSONArray) etudiant.get("notes");
		System.out.println(n);
		
	}

}
