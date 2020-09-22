package Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import Network.Format;
import Network.Monitor;

public class Chart 
{
	public ArrayList <Vector2> dataPoints = new ArrayList<Vector2>();
	public String pair = "";
	
	public Chart(String pair)
	{
		this.pair = pair;
		List<String> fileLines = null;
		ArrayList<Vector2> vectors = new ArrayList<Vector2>();
		File f = new File(System.getProperty("user.dir") + Monitor.storageDir + pair + ".json");
		if(f.exists())
		{
			Path file = Paths.get(System.getProperty("user.dir") + Monitor.storageDir + pair + ".json");
			try 
			{
				fileLines = Files.readAllLines(file.toAbsolutePath(), StandardCharsets.UTF_8);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			for(String json : fileLines)
			{
				Vector2 v = new Vector2(Format.stringToJSON(json), Monitor.xKey, Monitor.yKey);
				vectors.add(v);
			}
			dataPoints = vectors;
			System.out.println();
		}
	}
	
	public void exportCSV()
	{
		File f = new File(System.getProperty("user.dir") + Monitor.storageDir + pair + "_export.csv");
		Path file = Paths.get(System.getProperty("user.dir") + Monitor.storageDir + pair + "_export.csv");
		StringBuilder sb = new StringBuilder();
		for(Vector2 vector : dataPoints)
		{
			sb.append(vector.y + ";");
		}
		sb.deleteCharAt(sb.lastIndexOf(";"));
		
		try 
		{
			Files.write(file, sb.toString().getBytes(), StandardOpenOption.CREATE);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
