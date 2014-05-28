import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Yahoo {

	private static String baseURL = "http://download.finance.yahoo.com/d/quotes.csv?s="; // base url for the api
	private static String baseURLend = "&f=ab&e=.csv"; // ab are the codes for ask and bid

	public static void getData(String ticker) {
		try {
			URL interWebs = new URL(baseURL + ticker + baseURLend); // create a URL instance of the API
			URLConnection interwebConnect = interWebs.openConnection(); // make a URL connection out of the URL
			InputStreamReader isr = new InputStreamReader(interwebConnect.getInputStream()); // Retrieve the csv into a InputStreamReader
			BufferedReader in = new BufferedReader(isr); // For efficiency, thank you oracle API
			System.out.println( baseURL + ticker + baseURLend );

			System.out.println();

			System.out.println(in.readLine()); // Print the ask and bid about ticker
		} catch (IOException e) {
			System.out.println(e); // this should be more verbose
		}


	}

	public static void main(String[] args) {
		getData("AAPL");
	}

}
