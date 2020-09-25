package Conversion;

import java.util.ArrayList;
import java.util.Date;

import Data.Vector2;

public class Math 
{
	public static Date scientificNotationToDate(double in)
	{
		Date out = new Date((long)in*1000);
		return out;
	}
	
	public static float calcAverageGradient(ArrayList<Vector2> dataSet)
	{
		float averageGradient;
		ArrayList<Float> grads = new ArrayList<Float>();
		
		int indexA = 0;
		int indexB = indexA + 1;
		while(indexB < dataSet.size())
		{
			Float gradient = calcGradient(dataSet.get(indexA), dataSet.get(indexB));
			grads.add(gradient);
			indexA++;
			indexB++;
		}
		
		float gradSum = 0;
		for(Float grad : grads)
		{
			gradSum += grad;
		}
		averageGradient = gradSum / (grads.size());
		
		return averageGradient;
	}
	
	public static float calcGradient(Vector2 pointA, Vector2 pointB)
	{
		float gradient;
		gradient = (float) ((pointB.y - pointA.y) / (pointB.x - pointA.x));
		return gradient;
	}
}
