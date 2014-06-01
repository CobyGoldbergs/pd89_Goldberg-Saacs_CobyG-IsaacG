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
	Stock stock1 = new Stock("AAPL", 633.00, .74, .8, .3);
	stocks.add(stock1);
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
	    st.setMarketStrength(marketStrength); // reset each stock's strength var
	}
    }

    // returns a list of the most expensive stocks
    public LinkedList<Stock> getExpensiveStocks(int quantity){
	stocks = qs.qsort(stocks, 1); // sort by price
	LinkedList<Stock> ret = new LinkedList<Stock>();
	if (quantity > stocks.size())
	    quantity = stocks.size();
	for (int i = 0; i < quantity; i++){
	    ret.add(stocks.get(i));
	}
	return ret;
    }

}
