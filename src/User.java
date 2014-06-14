import java.util.*;

public class User{
    // needs system for user to know how much they have of each (if not using ll)

    private LinkedList<StockPosition> portfolio; // linked list to quickly remove and add, expanding and contracting the list
    private double money; // stores how much money they have not tied up in stocks
    private QuickSort qs;

    public User(double m){
	portfolio = new LinkedList<StockPosition>();
	money = m;
	qs = new QuickSort();
    }
    
    public LinkedList<StockPosition> getPortfolio(){return portfolio;}

    public String buyStock(Stock st, int numShares){
	if (money > numShares * st.getPrice()){ // they have enough money
	    money = money - st.getPrice() * numShares; // decrement money
	    portfolio = qs.qsort(portfolio, 0); // 0 sorts by ticker name
	    int index = -1;
	    // check if they already own some shares in that stock
	    String searchedTicker = st.getTicker();
	    // traverse until ticker is found in portfolio
	    for (int i = 0; i < portfolio.size(); i++){
		StockPosition temp = portfolio.get(i);
		String tempTicker = temp.getTicker();
		if (tempTicker.equals(searchedTicker)){ // this is the index of that ticker in portfolio
		    index = i;
		    break;
		}
	    }
	    if (index == -1){ // stock not currently owned
		StockPosition sp = new StockPosition(st, numShares);
		portfolio.add(sp);
	        return "Purchased your first " + numShares + " shares";
	    }
	    else{
		portfolio.get(index).addShares(numShares); // add the shares to existing position
		return "Purchased another " + numShares + " shares";
	    }
	}
	else{
	    return "Insufficient funds.";
	}
    }

    public String sellStock(Stock st, int numShares){
	portfolio = qs.qsort(portfolio, 0); // 0 sorts by ticker name
	int index = -1;
	// check if they already own some shares in that stock
	String searchedTicker = st.getTicker();
	// traverse until ticker is found in portfolio
	for (int i = 0; i < portfolio.size(); i++){
	    StockPosition temp = portfolio.get(i);
	    String tempTicker = temp.getTicker();
	    if (tempTicker.equals(searchedTicker)){ // this is the index of that ticker in portfolio
		index = i;
		break;
	    }
	}
	int sharesOwned = portfolio.get(index).getNumShares();
	if (index == -1)
	    return "Invalid stock";
	else if (sharesOwned < numShares)
	    return "You do not own sufficient shares";
	else{
	    portfolio.get(index).sellShares(numShares);
	    return "Sold " + numShares + " shares";
	}
	
    }

}
