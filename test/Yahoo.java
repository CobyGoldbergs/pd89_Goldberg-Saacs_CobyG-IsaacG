import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.Proxy;
import java.net.InetSocketAddress;

public class Yahoo {

	private static String baseURL = "http://download.finance.yahoo.com/d/quotes.csv?s="; // base url for the api
    // http://www.gummy-stuff.org/Yahoo-data.htm list of acronyms for yahoo api
	private static String baseURLend = "&f=b2b3c6l1&e=.csv"; // b2 b3 are ask and bid real time

	public static void getData(String ticker) {
		try {
			URL interWebs = new URL(baseURL + ticker + baseURLend); // create a URL instance of the API
			

			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("149.89.1.30", 3128));
			
			URLConnection interwebConnect = interWebs.openConnection(proxy); // make a URL connection out of the URL 
			//System.out.println("1st"); // prints
			InputStreamReader isr = new InputStreamReader(interwebConnect.getInputStream()); // Retrieve the csv into a InputStreamReader
			//System.out.println("1st"); // doesnt print
			BufferedReader in = new BufferedReader(isr); // For efficiency, thank you oracle API
			System.out.println( baseURL + ticker + baseURLend );

			System.out.println();

			System.out.println(in.readLine()); // Print the ask and bid about ticker
		} catch (Exception e) {
		    System.out.println(e); // this should be more verbose
		}


	}

	public static void main(String[] args) {
		getData("AAPL");
	}

}
