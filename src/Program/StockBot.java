package Program;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import Data.Vector2;
import Network.Format;
import Network.HTTP;

public class StockBot 
{
	public ArrayList<Vector2> pullChartData()
	{
		ArrayList<Vector2> vectors = null;
		try
		{
			String jsonString = HTTP.get(HTTP.ADDRESS_2WEEKS_BTC_DAILY);
			System.out.println(jsonString);
			JSONObject dataObj = Format.stringToJSON(jsonString);
			JSONArray valueArr = dataObj.getJSONArray("values");
			vectors = new ArrayList<Vector2>();
			for(int n = 0; n < valueArr.length(); n++)
			{
				Vector2 instance = new Vector2(valueArr.getJSONObject(n));
				vectors.add(instance);
			}
		}
		catch(Exception e)
		{
			System.err.println("Err: " + e.getStackTrace().toString());
		}
		
		return vectors;
	}
	
	public ArrayList<Vector2> calcSimpleFloatingAverage(ArrayList<Vector2> dataSet)
	{
		System.out.println("Calculating Simple Floating Average (3rd grade)...");
		ArrayList<Vector2> simpleFloatingAverage = new ArrayList<Vector2>();
		double factor = 1d / 3d;
		double sum = 0d;
		for(int i = 0; i < dataSet.size(); i++)
		{
			System.out.println("	i = " + i + " Calculating for: " + dataSet.get(i).y);
			for(int n = i; n < i + 3; n++)
			{
				if(n < dataSet.size())
				{
					sum += dataSet.get(n).y;	
				}
				else
				{
					break;
				}
			}
			Vector2 floatVector = new Vector2(i, factor * sum);
			simpleFloatingAverage.add(floatVector);
			sum = 0;
			System.out.println("		Result = " + floatVector.y);
		}
		
		return simpleFloatingAverage;
	}
}
