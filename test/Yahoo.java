import java.util.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Yahoo {

	private static String baseURL = "http://download.finance.yahoo.com/d/quotes.csv?s=";
	private static String baseURLend = "&f=ab&e=.csv";

	public static void getData(String ticker) {
		try {
			URL interWebs = new URL(baseURL + ticker + baseURLend);
			URLConnection interwebConnect = interWebs.openConnection();
			InputStreamReader isr = new InputStreamReader(interwebConnect.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			System.out.println( baseURL + ticker + baseURLend );

			System.out.println();

			System.out.println(in.readLine());
		} catch (IOException e) {
			System.out.println(e);
		}


	}

	public static void main(String[] args) {
		getData("GOOG");
	}

}