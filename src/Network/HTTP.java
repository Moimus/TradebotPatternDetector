package Network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.URL;

public class HTTP 
{
	public static String ADDRESS_2WEEKS_BTC_DAILY = "https://api.blockchain.info/charts/market-price?timespan=2weeks&format=json";
	
    private static HttpURLConnection getConnection(String URLString)
    {
        HttpURLConnection con = null;
        try
        {
            URL url = new URL(URLString);
            con = (HttpURLConnection) url.openConnection();
        }
        catch (Exception e)
        {
            System.err.println("Err: " + "getConnection failed!");
            System.err.println("Err: " + e.getStackTrace().toString());
        }
        return con;
    }
    
    public static String get(String Address)
    {
        HttpURLConnection con = null;
        String response = null;
        try
        {
            con = getConnection(Address);
            con.setRequestMethod("GET");
            con.setRequestProperty("User-agent", "Mozilla/5.0 (Windows; U; Win98; en-US; rv:1.7.2) Gecko/20040803");
            con.setRequestProperty("Content-Language","en-US");
            con.setUseCaches(false);
            con.setDoOutput(true);

            InputStream inputStream = con.getInputStream();
            InputStreamReader inReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inReader);
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                line = line.replace("%2C",",");
                responseBuilder.append(line);
            }
            reader.close();
            response = responseBuilder.toString();
        }
        catch (NoRouteToHostException nrhe)
        {
            String msg = nrhe.toString() + "\n" + nrhe.getStackTrace()[0];
            System.err.println("Err: " +"GET failed!");
            System.err.println("Err: " + msg);
        }
        catch (Exception e)
        {
            String msg = e.toString() + "\n" + e.getStackTrace()[0];
            System.err.println("Err: " + "GET failed!");
            System.err.println("Err: " + msg);
        }
        finally
        {
            if (con != null)
            {
                con.disconnect();
            }
        }

        return response;

    }

}
