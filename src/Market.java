import java.util.*;

public class Market{

    private double gsIndexPrice; // total value of goldberg and saacs index = sum of all prices
    private LinkedList<Stock> stocks; // will contain all stocks on market
    private double marketStrength; // market strength of whole market
    private QuickSortStocks qs; // quick sort to be used when needed
    private Stack<News> news;
    private String ticker;
    private long time;
    private long time1;
    
    public Market(){
	fillMarket(); // method to add stocks to market
	gsIndexPrice = 0;
	updateIndexVal(); // method to update the value of the market
	qs = new QuickSortStocks();
	news = new Stack<News>();
	createNews(); // creates news items
	time = System.currentTimeMillis();
	time1 = System.currentTimeMillis();
    }

    // INITIALIZE HELPERS

    public void fillMarket(){
	stocks = new LinkedList<Stock>();
	Stock stock1 = new Stock("AAPL", 633.00, .74, .08, .3);
	// ticker, price, beta, vol, market strength
	stocks.add(stock1);
	Stock stock2 = new Stock("TSLA", 133.22, .23, 1.3, .3);
	stocks.add(stock2);
	Stock stock3 = new Stock("GS", 150.10, .05, .9, .3);
	stocks.add(stock3);
	Stock stock4 = new Stock("JPM", 57.04, .07, .1, .3);
	stocks.add(stock4);
	Stock stock5 = new Stock("GOOG", 551.76, .22, .89, .3);
	stocks.add(stock5);
	Stock stock6 = new Stock("MSFT", 41.23, 1.1, .3, .3);
	stocks.add(stock6);
	Stock stock7 = new Stock("BAC", 15.44, .19, .82, .3);
	stocks.add(stock7);
	Stock stock8 = new Stock("TWTR", 36.90, -.84, .9, .3);
	stocks.add(stock8);
	Stock stock9 = new Stock("FB", 64.50, .28, .6, .3);
	stocks.add(stock9);
	Stock stock10 = new Stock("RTN", 96.59, .32, .61, .3);
	stocks.add(stock10);

    }

// initializes all news
    public void createNews(){
	String info1 = "Apple invents new iPhone that does your comp sci project";
	News n1 = new News(true, info1, new String[]{"AAPL"});
	news.push(n1);
	String info2 = "Federal reserves announces it will contract money supply. Investors be warned";
	News n2 = new News(false, info2, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	news.push(n2);
	String info3 = "Government will begin prosecuting several major financial CEOs for fraud";
	News n3 = new News(false, info3, new String[]{"GS", "JPM", "BAC"});
	news.push(n3);
	String info4 = "Tesla CEO Elon Musk announces car that runs on love";
	News n4 = new News(true, info4, new String[]{"TSLA"});
	news.push(n4);
	String info5 = "Goldman Sacs opens a goat trading divsion, reaps record profits";
	News n5 = new News(true, info5, new String[]{"GS"});
	news.push(n5);
	String info6 = "Apple sued for having actually stolen every single one of its ideas";
	News n6 = new News(false, info6, new String[]{"AAPL"});
	news.push(n6);
	String info7 = "JP Morgan announces major losses due to bad investments in the Mets, upwards of 20 billion lost.";
	News n7 = new News(false, info7, new String[]{"JPM"});
	news.push(n7);
	String info8 = "University of Michigan announces 87.3 Consumer Confidence Index, up 4 points from previous month.";
	News n8 = new News(true, info8, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	news.push(n8);
	String info9 = "Apple announces new product, iLive, lives life for you. Initial sale in the trillions of pesos.";
	News n9 = new News(true, info9, new String[]{"AAPL"});
	news.push(n9);
	String info10 = "Tesla to open new factory in Central African Republic. Investors very excited about prospects";
	News n10 = new News(true, info10, new String[]{"TSLA"});
	news.push(n10);
	String info11 = "Turns out there is a civil war in Central African Republican. Tesla factory screwed.";
	News n11 = new News(false, info11, new String[]{"TSLA"});
	news.push(n11);
	String info12 = "Government shutodwn potent in thirty days";
	News n12 = new News(false, info12, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	news.push(n12);
	String info13 = "France announced 300 billion dollar suit against Google for evaded taxes.";
	News n13 = new News(false, info13, new String[]{"GOOG"});
	news.push(n13);
	String info14 = "Facebook announces major user growth in Asian markets.";
	News n14 = new News(true, info14, new String[]{"FB"});
	news.push(n14);
	String info15 = "Twitter losing membership fast in North America as tweeter gains popularity";
	News n15 = new News(false, info15, new String[]{"TWTR"});
	news.push(n15);
	String info16 = "Republicans in House will not back down on shutdown, says leadership";
	News n16 = new News(false, info16, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	news.push(n16);
	String info17 = "North Korean markets now open to all social media, Kim Jung Ill announces";
	News n17 = new News(true, info17, new String[]{"FB", "TWTR"});
	news.push(n17);
	String info18 = "New 'Cat of Wall Street' makes record gains for JP Morgan";
	News n18 = new News(true, info18, new String[]{"JPM"});
	news.push(n18);
	String info19 = "Obama announces budget deal, funds government for four months";
	News n19 = new News(true, info19, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	news.push(n19);
	String info20 = "Raytheon announces deal as 'Official weapons provider of the USA'";
	News n20 = new News(true, info20, new String[]{"RTN"});
	news.push(n20);
	String info21 = "Tesla's 'Love Car' sales through the roof, draws praise from environmental activists";
	News n21 = new News(true, info21, new String[]{"TSLA"});
	news.push(n21);
	String info22 = "Job growth falls 100,000 short of expectations for past month";
	News n22 = new News(false, info22, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	news.push(n22);
	String info23 = "Cat of Wall Street under charges for fraud, as is much of JPM";
	News n23 = new News(false, info23, new String[]{"JPM"});
	news.push(n23);
	String info24 = "Ron Paul announces potential candidacy for President again, polling looks good, plans to end the government";
	News n24 = new News(false, info24, new String[]{"AAPL", "TSLA", "GS", "JPM", "GOOG", "MSFT", "BAC", "TWTR", "FB", "RTN"});
	news.push(n24);
	String info25 = "Google settles suit with France for 50 billion pesos, many believe they dodged a bullet";
	News n25 = new News(true, info25, new String[]{"GOOG"});
	news.push(n25);
	String info26 = "Apple to start paying its Chinese workers, investors happy";
	News n26 = new News(true, info26, new String[]{"AAPL"});
	news.push(n26);
	String info27 = "China to allow Google services free of censoring, Google to expand Asian operations";
	News n27 = new News(true, info27, new String[]{"GOOG"});
	news.push(n27);
	String info28 = "Sales of Apple's iPhone 8 through the roof";
	News n28 = new News(true, info28, new String[]{"AAPL"});
	news.push(n28);
	String info29 = "Twitter loses a lot of customers";
	News n29 = new News(false, info29, new String[]{"TWTR"});
	news.push(n29);
	String info30 = "Raytheon hires new CEO, great trackrecord";
	News n30 = new News(true, info30, new String[]{"RTN"});
	news.push(n30);
    }

    // MARKET RUNNERS

    // method that updates the market  every 5 seconds
    public void updateMarket(){
	/*time1 = System.currentTimeMillis();
	if ((time1 - time) > 5000){
	time = System.currentTimeMillis();*/

	    // randomly assign new news
	    Random r = new Random();
	    if (r.nextInt(50) == 0 && !news.empty())
		applyNews();
	    priceUpdate();
	    updateIndexVal();
	    updateStrength();
	
    }

    // tells each effected stock of new news
    public void applyNews(){
	News item = news.pop();
	String[] tags = item.getTags();

	for (int i = 0; i < tags.length; i++){
	    String searching = tags[i];

	    for (int j = 0; j < stocks.size(); j++){
		Stock checking = stocks.get(j);
		String tick = checking.getTicker();
		if (searching.equals(tick)){
		    checking.applyNews(item);
		    break;
		}
	    }
	}
    }

    // updates price of each stock
    public void priceUpdate(){
	for (int i = 0; i < stocks.size(); i++)
	    stocks.get(i).priceUpdate(); // new price for each stock
    }

    // updates the overall value of market
    public void updateIndexVal(){
	Stock st;
	// recurse through the market
	for (int i = 0; i < stocks.size(); i++){
	    st = stocks.get(i);
	    gsIndexPrice += st.getPrice(); // add each stock's price to index total
	}
    }


    // updates market strength and gives the new one to each market
    public void updateStrength(){
	// method to change market strength value
	double changeInStrength = 0.0; // can be neg or pos
	Stock st;
	// recurse through the market
	for (int i = 0; i < stocks.size(); i++){
	    st = stocks.get(i);
	    st.setMarketStrength(changeInStrength); // reset each stock's strength variable
	}
    }


    //  METHODS TO RETURN STOCKS SORTED

    public LinkedList<Stock> getStocks() {
    	return stocks;
    }

    public LinkedList<Stock> getAlphabetizedStocks(){
	LinkedList<Stock> sorted = qs.qsort(stocks, 0); // sorts by ticker name
	return sorted;
    }

    // returns a list of the most expensive stocks
    public LinkedList<Stock> getExpensiveStocks(int quantity){
	LinkedList<Stock> sorted = qs.qsort(stocks, 1); // sort by price
	LinkedList<Stock> ret = new LinkedList<Stock>();

	if (quantity > sorted.size())
	    quantity = sorted.size();

	for (int i = sorted.size() - 1; i >= sorted.size() - quantity; i--)
	    ret.add(sorted.get(i));
	return ret;
    }

    public LinkedList<Stock> getCheapestStocks(int quantity){
	LinkedList<Stock> sorted = qs.qsort(stocks, 1);
	LinkedList<Stock> ret = new LinkedList<Stock>();

	if (quantity > sorted.size())
	    quantity = sorted.size();

	for (int i = 0; i < quantity; i++)
	    ret.add(sorted.get(i));
	return ret;
    }

    // return list of stocks with highest percent changes
    public LinkedList<Stock> getStrongestStocks(int quantity){
	LinkedList<Stock> sorted = qs.qsort(stocks, 2); // sort by percentChange
	LinkedList<Stock> ret = new LinkedList<Stock>();

	if (quantity > sorted.size())
	    quantity = sorted.size();

	for (int i = sorted.size() - 1; i >= sorted.size() - quantity; i--){
	    ret.add(sorted.get(i));
	}
	return ret;
    }

    // return list of stocks with weakest percent changes
    public LinkedList<Stock> getWeakestStocks(int quantity){
	LinkedList<Stock> sorted = qs.qsort(stocks, 2); // sort by percentChange
	LinkedList<Stock> ret = new LinkedList<Stock>();

	if (quantity > sorted.size())
	    quantity = sorted.size();

	for (int i = 0; i < quantity; i++){
	    ret.add(sorted.get(i));
	}
	return ret;
    }
    
    // for testing
    public static void main(String[] args){
	Market m = new Market();
	LinkedList<Stock> tp = m.getAlphabetizedStocks();
	for (int i = 0; i < 4000; i++){
	    m.updateMarket();
	}
	tp =  m.getStrongestStocks(4);
	System.out.println("Strongest: ");
	for (Stock a : tp)
	    System.out.println(a + " Percent change: " + a.getPercentChange());
	tp =  m.getWeakestStocks(4);
	System.out.println("Weakest: ");
	for (Stock a : tp)
	    System.out.println(a + " Percent change: " + a.getPercentChange());
	tp =  m.getExpensiveStocks(4);
	System.out.println("Most expensive: ");
	for (Stock a : tp)
	    System.out.println(a + " Price: " + a.getPrice());
	tp =  m.getCheapestStocks(4);
	System.out.println("Cheapest: ");
	for (Stock a : tp)
	    System.out.println(a + " Price: " + a.getPrice());
	tp =  m.getAlphabetizedStocks();
	System.out.println("By name: ");
	for (Stock a : tp)
	    System.out.println(a );
	
    }



}
