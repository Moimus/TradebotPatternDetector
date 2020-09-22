package Network;

import org.json.JSONException;
import org.json.JSONObject;

public class Format 
{
	public static JSONObject stringToJSON(String jsonString)
	{
		JSONObject obj = null;
		try
		{
			obj = new JSONObject(jsonString);
		}
		catch(JSONException je)
		{
			
		}
		
		return obj;

	}
}
