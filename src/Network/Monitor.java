package Network;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

public class Monitor 
{
	public static String storageDir = "\\Data\\";
	public Date lastPulled = null;
	public static float pullIntervalInMillis = 60000f;
	public static String xKey = "timestamp";
	public static String yKey = "last";
	
	public static HashMap<String, String> pairs = new HashMap<String, String>()
	{
		{
			put("BTC-USD", "https://cex.io/api/ticker/BTC/USD");
			put("ETH-USD", "https://cex.io/api/ticker/ETH/USD");
			put("NEO-USD", "https://cex.io/api/ticker/NEO/USD");
		}
	};
	
	public Monitor()
	{
		lastPulled = Calendar.getInstance().getTime();
		createStorageFiles();
		pullData();
	}
	
	public void periodicDataPull()
	{
		float timeSinceLastPull = Float.valueOf((Calendar.getInstance().getTime().getTime() - lastPulled.getTime()));
		if(timeSinceLastPull > pullIntervalInMillis)
		{
			pullData();
		}
	}
	
	private void pullData()
	{
		System.out.println("Monitor: pulling data...");
		Set<String> setOfPairNames = pairs.keySet();
		for(String pair : setOfPairNames)
		{
			String jsonString = HTTP.get(pairs.get(pair));
			appendDataPoint(pair, jsonString);
			System.out.println(jsonString);
		}
		lastPulled = Calendar.getInstance().getTime();	
		System.out.println("Monitor: pulling data done!");
	}
	
	private void createStorageFiles()
	{
		Set<String> setOfPairNames = pairs.keySet();
		for(String pair : setOfPairNames)
		{
			File f = new File(System.getProperty("user.dir") + storageDir + pair + ".json");
			if(!f.exists())
			{
				Path file = Paths.get(System.getProperty("user.dir") + storageDir + pair + ".json");
				try 
				{
					Files.write(file, ("").getBytes(), StandardOpenOption.CREATE);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	private void appendDataPoint(String pair ,String json)
	{
		File f = new File(System.getProperty("user.dir") + storageDir + pair + ".json");
		if(f.exists())
		{
			Path file = Paths.get(System.getProperty("user.dir") + storageDir + pair + ".json");
			try 
			{
				Files.write(file, (json + "\n").getBytes(), StandardOpenOption.APPEND);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
			
}
