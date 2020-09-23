package Data;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class Bullflag extends Model
{
	public Vector2 flagPoleStart = null;
	private int flagPoleStartIndex = -1;
	private int flagPoleEndIndex = -1;
	public ArrayList<Vector2> flagpoleMid = new ArrayList<Vector2>();
	public Vector2 flagPoleEnd = null;
	public double flagPoleSize = -1d;
	
	public Vector2 flagStart = null;
	public ArrayList<Vector2> flagMid = new ArrayList<Vector2>();
	public Vector2 flagEnd = null;
	public int flagEndIndex = -1;
	
	public double breakoutLevel = -1d;
	
	public Bullflag(ArrayList<Vector2> dataSet)
	{
		searchFlagpole(dataSet);
		searchFlag(dataSet);
		System.out.println("Bullflag done!");
	}
	
	private void searchFlagpole(ArrayList<Vector2> dataSet)
	{
		for(int n = 0; n < dataSet.size(); n++)
		{
			//ist der punkt größer als der vorherige && kleiner als der nächste?
			if(n > 0 && n + 1 < dataSet.size() && dataSet.get(n).y > dataSet.get(n - 1).y && dataSet.get(n).y < dataSet.get(n + 1).y)
			{
				//existiert schon ein flagpole?
				if(flagPoleStart == null)
				{
					flagPoleStart = dataSet.get(n - 1);
					flagPoleStartIndex = n - 1;
				}
				else
				{
					flagpoleMid.add(dataSet.get(n));
				}
			}
			//ist der nächste punkt kleiner als der aktuelle aber trotdem größer als der vorherige(ende des flagpoles)?
			else if(n > 0 && n + 1 < dataSet.size() && dataSet.get(n).y > dataSet.get(n + 1).y && dataSet.get(n).y > dataSet.get(n - 1).y)
			{
				if(flagPoleEnd == null && flagPoleStart != null)
				{
					flagPoleEnd = dataSet.get(n);
					flagPoleEndIndex = n;
					breakoutLevel = flagPoleEnd.y;
				}
			}
		}
		
		flagPoleSize = Vector2.Distance(flagPoleStart, flagPoleEnd);
	}
	
	private void searchFlag(ArrayList<Vector2> dataSet)
	{
		try
		{
			double upperLimit = flagPoleEnd.y;
			
			for(int n = flagPoleEndIndex + 1; n < dataSet.size(); n++)
			{
				if(dataSet.get(n).y < breakoutLevel)
				{
					flagMid.add(dataSet.get(n));
				}
				else if(dataSet.get(n).y > breakoutLevel)
				{
					flagEndIndex = n;
					break;
				}
			}
			
			if(flagMid.size() > 0)
			{
				flagStart = flagMid.get(0);
				flagEnd = flagMid.get(flagMid.size() - 1);
			}
		}
		catch(Exception e)
		{
			System.out.println("BullFlag: Construction failed");
		}

	}
	
	public Boolean isABullflag()
	{
		Boolean isFlag = false;
		if(breakoutLevel != -1 && flagPoleStartIndex != -1 && flagPoleEndIndex != -1 && flagEndIndex != -1)
		{
			isFlag = true;
		}
		return isFlag;
	}
	
	@Override
	public String toJSON()
	{
		JSONObject json = new JSONObject();
		try 
		{
			json.put("flagPoleStart", flagPoleStart.toJSON());
			
			ArrayList<String> flagpoleMidStrings = new ArrayList<String>();
			for(Vector2 vec : flagpoleMid)
			{
				flagpoleMidStrings.add(vec.toJSON());
			}
			json.put("flagPoleMid", flagpoleMidStrings);
			
			json.put("flagPoleEnd", flagPoleEnd.toJSON());
			
			json.put("flagStart", flagStart.toJSON());
			
			ArrayList<String> flagMidStrings = new ArrayList<String>();
			for(Vector2 vec : flagMid)
			{
				flagMidStrings.add(vec.toJSON());
			}
			json.put("flagMid", flagMidStrings);
			
			json.put("flagEnd", this.flagEnd.toJSON());
			
			json.put("breakoutLevel", breakoutLevel);
		} 
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
}
