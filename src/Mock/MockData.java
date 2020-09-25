package Mock;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import Data.Vector2;

public class MockData 
{
	public static ArrayList<Vector2> getSampleData()
	{
		ArrayList<Vector2> sampleData = new ArrayList<Vector2>();
		try 
		{
			sampleData.add(new Vector2(new JSONObject("{\"x\":1599436800,\"y\":10254.93}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1599523200,\"y\":10367.74}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1599609600,\"y\":10121.52}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1599696000,\"y\":10227.83}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1599782400,\"y\":10352.66}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1599868800,\"y\":10395.44}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1599955200,\"y\":10446.44}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1600041600,\"y\":10330.77}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1600128000,\"y\":10274.64}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1600214400,\"y\":10385.62}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1600300800,\"y\":10248.43}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1600387200,\"y\":10643.89}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1600473600,\"y\":10931.79}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1600560000,\"y\":11081.43}")));
			sampleData.add(new Vector2(new JSONObject("{\"x\":1600646400,\"y\":10919.65}")));
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return sampleData;
	}

	//test gradient calculation
	public static ArrayList<Vector2> getSampleGrapgh()
	{
		ArrayList<Vector2> sampleData = new ArrayList<Vector2>()
		{
			{
				add(new Vector2(0d,3d));
				add(new Vector2(1d,2d));
				add(new Vector2(2d,1d));
				add(new Vector2(3d,1d));
				add(new Vector2(4d,2d));
				add(new Vector2(5d,4d));
				add(new Vector2(6d,5d));
				add(new Vector2(7d,4d));
			}
		};
		
		return sampleData;
	}

}
