import org.json.JSONException;
import org.json.JSONObject;


public class MonJSONObject {

	public static void main(String[] args) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("Promo", "RIL13");
		o.put("Resp", "Stéphane Amet");
		o.put("NbEtudiants", 3);
		System.out.println(o);
		String so = o.toString();

		JSONObject oRecept = new JSONObject(so);
		System.out.println(oRecept.get("Promo") + " : " + oRecept.getInt("NbEtudiants"));
	}

}
