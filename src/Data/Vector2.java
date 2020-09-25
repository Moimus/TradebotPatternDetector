package Data;

import org.json.JSONException;
import org.json.JSONObject;

public class Vector2 extends Model
{
	public double x;
	public double y;

	public Vector2(JSONObject json)
	{
		try 
		{
			this.x = json.getDouble("x");
			this.y = json.getDouble("y");
		} 
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Vector2(JSONObject json, String xKey, String yKey)
	{
		try 
		{
			this.x = json.getDouble(xKey);
			this.y = json.getDouble(yKey);
		} 
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Vector2(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public static double Distance(Vector2 A, Vector2 B)
	{
		double dist = -1;
		try
		{	
			double aX = A.x;
			double aY = A.y;
			
			double bX = B.x;
			double bY = B.y;
			
			// D = sqrt((xb - xa)^2 + (yb - ya)^2)
			
			double dX = (bX - aX) * (bX - aX);
			double dY = (bY - aY) * (bY - aY);
			double dXY = dX + dY;
			
			dist = (double)Math.sqrt((double)dXY);	
		}
		catch(NullPointerException ne)
		{
			//System.out.println("Point out of range");
		}
		
		return dist;
	}

	@Override
	public String toJSON() 
	{
		JSONObject json = new JSONObject();
		try 
		{
			json.put("x", y);
			json.put("y", y);

		} 
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
}
