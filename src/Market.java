import java.util.*;

public class Market{

    private double gsIndexPrice; // total value of goldberg and saacs index = sum of all prices
    private LinkedList<Stock> stocks; // will contain all stocks on market
    private double marketStrength; // market strength of whole market
    private QuickSortStocks qs; // quick sort to be used when needed
    
    public Market(){
	fillMarket(); // method to add stocks to market
	gsIndexPrice = 0;
	updateIndexVal(); // method to update the index of the market
	qs = new QuickSortStocks();
    }

    public void fillMarket(){
	stocks = new LinkedList<Stock>();
	Stock stock1 = new Stock("AAPL", 633.00, .74, .08, .3);
	// ticker, price, beta, vol, market strength
	stocks.add(stock1);
	Stock stock2 = new Stock("TSLA", 133.22, -.74, 1.3, .3);
	stocks.add(stock2);
    }

    public void updateIndexVal(){
	Stock st;
	// recurse through the market
	for (int i = 0; i < stocks.size(); i++){
	    st = stocks.get(i);
	    gsIndexPrice += st.getPrice(); // add each stock's price to index total
	}
    }

    public void updateStrength(){
	// method to change market strength value
	Stock st;
	// recurse through the market
	for (int i = 0; i < stocks.size(); i++){
	    st = stocks.get(i);
	    st.setMarketStrength(marketStrength); // reset each stock's strength variable
	}
    }


    // Methods that sort all market stocks by price or strength

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
	for (int i = 0; i < 4000; i++)
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

}
