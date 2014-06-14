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
	Stock stock2 = new Stock("TSLA", 133.22, -.74, 1.3, .3);
	stocks.add(stock2);
	Stock stock3 = new Stock("GS", 150.10, .05, .9, .3);
	stocks.add(stock3);
    }

// initializes all news
    public void createNews(){
	String info1 = "Apple invents new iPhone that does your comp sci project";
	News n1 = new News(true, info1, new String[]{"AAPL"});
	news.push(n1);
	String info2 = "Federal reserves announces it will contract money supply. Investors be warned";
	News n2 = new News(false, info2, new String[]{"AAPL", "TSLA", "GS", "JPM"});
	news.push(n2);
	String info3 = "Government will begin prosecuting several major financial CEOs for fraud";
	News n3 = new News(false, info3, new String[]{"GS", "JPM"});
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
	News n8 = new News(true, info8, new String[]{"GS", "JPM", "TSLA", "AAPL"});
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
	News n12 = new News(false, info12, new String[]{"JPM", "GS"});
	news.push(n12);
    }

    // MARKET RUNNERS

    // method that updates the market  every 5 seconds
    public void updateMarket(){
	time1 = System.currentTimeMillis();
	if ((time1 - time) > 5000){
	    time = System.currentTimeMillis();

	    // randomly assign new news
	    Random r = new Random();
	    if (r.nextInt(100) == 0)
		applyNews();
	    priceUpdate();
	    updateIndexVal();
	    updateStrength();
	}
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
	Stock st;
	// recurse through the market
	for (int i = 0; i < stocks.size(); i++){
	    st = stocks.get(i);
	    st.setMarketStrength(marketStrength); // reset each stock's strength variable
	}
    }


    //  METHODS TO RETURN STOCKS SORTED

    public LinkedList<Stock> getAlphabetizedStocks(){
	stocks = qs.qsort(stocks, 0); // sorts by ticker name
	return stocks;
    }

    // returns a list of the most expensive stocks
    public LinkedList<Stock> getExpensiveStocks(int quantity){
	stocks = qs.qsort(stocks, 1); // sort by price
	LinkedList<Stock> ret = new LinkedList<Stock>();

	if (quantity > stocks.size())
	    quantity = stocks.size();

	for (int i = stocks.size() - 1; i >= stocks.size() - quantity; i--)
	    ret.add(stocks.get(i));
	return ret;
    }

    public LinkedList<Stock> getCheapestStocks(int quantity){
	stocks = qs.qsort(stocks, 1);
	LinkedList<Stock> ret = new LinkedList<Stock>();

	if (quantity > stocks.size())
	    quantity = stocks.size();

	for (int i = 0; i < quantity; i++)
	    ret.add(stocks.get(i));
	return ret;
    }

    // return list of stocks with highest percent changes
    public LinkedList<Stock> getStrongestStocks(int quantity){
	stocks = qs.qsort(stocks, 2); // sort by percentChange
	LinkedList<Stock> ret = new LinkedList<Stock>();

	if (quantity > stocks.size())
	    quantity = stocks.size();

	for (int i = stocks.size() - 1; i >= stocks.size() - quantity; i--){
	    ret.add(stocks.get(i));
	}
	return ret;
    }

    // return list of stocks with weakest percent changes
    public LinkedList<Stock> getWeakestStocks(int quantity){
	stocks = qs.qsort(stocks, 2); // sort by percentChange
	LinkedList<Stock> ret = new LinkedList<Stock>();

	if (quantity > stocks.size())
	    quantity = stocks.size();

	for (int i = 0; i < quantity; i++){
	    ret.add(stocks.get(i));
	}
	return ret;
    }
    
    // for testing
    public static void main(String[] args){
	Market m = new Market();
	LinkedList<Stock> tp = m.getAlphabetizedStocks();
	for (int i = 0; i < 40000; i++)
	    for (Stock a : tp)
		a.priceUpdate();
	tp =  m.getStrongestStocks(4);
	System.out.println("Strongest: ");
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

    public LinkedList<Stock> getStocks() {
    	return stocks;
    }

}
