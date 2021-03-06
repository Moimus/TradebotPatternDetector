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
	public ArrayList<Vector2> dataPoints = new ArrayList<Vector2>();
	public ArrayList<Bullflag> bullflags = new ArrayList<Bullflag>();
	private ArrayList<Vector2> workingList = new ArrayList<Vector2>();
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
			//System.out.println();
		}
	}
	
	//scans dataPoints for bullflag patterns
	public void searchBullflags()
	{
		//copy dataList
		for(Vector2 point : dataPoints)
		{
			workingList.add(point);
		}
		
		int pointer = 0;
		while(pointer < workingList.size() && pointer != -1)
		{
			Bullflag bf = new Bullflag(workingList);
			for(int n = bf.flagEndIndex; n > 0; n--)
			{
				workingList.remove(n);
			}
			pointer = bf.flagEndIndex;
			if(bf.isABullflag())
			{
				bullflags.add(bf);
			}
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
	
	public void exportBullflags()
	{
		if(bullflags.size() > 0)
		{
			File f = new File(System.getProperty("user.dir") + Monitor.storageDir + pair + "_bullFlag_export.csv");
			Path file = Paths.get(System.getProperty("user.dir") + Monitor.storageDir + pair + "_bullFlag_export.csv");
			StringBuilder sb = new StringBuilder();
			for(Bullflag flag : bullflags)
			{
				if(flag.isABullflag())
				{
					sb.append(flag.toJSON() + ";");
				}
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
	
	//Debug function, DONT USE THIS!
	public void exportBullFlagCoordsCSV()
	{
		StringBuilder sb;
		int counter = 0;
		for(Bullflag flag : bullflags)
		{
			File f = new File(System.getProperty("user.dir") + Monitor.storageDir + "DataDump/" + pair + "_bullFlag_" + counter +".csv");
			Path file = Paths.get(System.getProperty("user.dir") + Monitor.storageDir + "DataDump/" + pair + "_bullFlag_" + counter +".csv");
			sb = new StringBuilder();
			//add y-coords
			sb.append(flag.flagPoleStart.y + ";");
			for(Vector2 vector : flag.flagpoleMid)
			{
				sb.append(vector.y + ";");
			}
			sb.append(flag.flagPoleEnd.y + ";");
			sb.append(flag.flagStart.y + ";");
			for(Vector2 vector : flag.flagMid)
			{
				sb.append(vector.y + ";");
			}
			sb.append(flag.flagEnd.y + ";");
			
			sb.deleteCharAt(sb.lastIndexOf(";"));
			
			sb.append("\n");
			
			//add x-coords (timestamps)
			sb.append(Conversion.Math.scientificNotationToDate(flag.flagPoleStart.x) + ";");
			for(Vector2 vector : flag.flagpoleMid)
			{
				sb.append(Conversion.Math.scientificNotationToDate(vector.x) + ";");
			}
			sb.append(Conversion.Math.scientificNotationToDate(flag.flagPoleEnd.x) + ";");
			sb.append(Conversion.Math.scientificNotationToDate(flag.flagStart.x) + ";");
			for(Vector2 vector : flag.flagMid)
			{
				sb.append(Conversion.Math.scientificNotationToDate(vector.x) + ";");
			}
			sb.append(Conversion.Math.scientificNotationToDate(flag.flagEnd.x) + ";");
			
			sb.deleteCharAt(sb.lastIndexOf(";"));
			
			try 
			{
				Files.write(file, sb.toString().getBytes(), StandardOpenOption.CREATE);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			counter++;
		}
	}

}
