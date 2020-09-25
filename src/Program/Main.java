package Program;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import Data.Bullflag;
import Data.Chart;
import Data.Vector2;
import Mock.MockData;
import Network.Format;
import Network.HTTP;
import Network.Monitor;

public class Main 
{

	public static void main(String[] args) 
	{
		/*try
		{
			String jsonString = HTTP.get(HTTP.ADDRESS_2WEEKS_BTC_DAILY);
			JSONObject obj = Format.stringToJSON(jsonString);
			JSONArray arr = obj.getJSONArray("values");
			System.out.println("Main Exit");
		}
		catch(Exception e)
		{
			System.err.println("Err: " + e.getStackTrace().toString());
		}*/
		
		StockBot stockBot = new StockBot();
		ArrayList<Vector2> vec = stockBot.pullChartData();
		ArrayList<Vector2> floatingAverage = stockBot.calcSimpleFloatingAverage(vec);
		Monitor mon = new Monitor();
		//System.out.println("testGradient: " + Conversion.Math.calcGradient(new Vector2(0d, 3d), new Vector2(1d,2d)));
		Conversion.Math.calcAverageGradient(MockData.getSampleGrapgh());
		while(true)
		{
			mon.periodicDataPull();
			Chart chart = new Chart("BTC-USD");
			//Bullflag bf = new Bullflag(chart.dataPoints);
			chart.searchBullflags();
			chart.exportCSV();
			chart.exportBullflags();
			chart.exportBullFlagCoordsCSV();
			for(Bullflag bf : chart.bullflags)
			{
				if(bf.isNewestofDataSet(chart.dataPoints))
				{
					System.out.println(bf.isNewestofDataSet(chart.dataPoints));
					System.out.println("flag is last");
				}
			}

		}

	}

}
