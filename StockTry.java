import java.util.*;

public class StockTry{

    double price;
    double volatility;
    boolean marketPos = false; // true -- market trending up
    ArrayList<Double> bids;
    ArrayList<Double> asks;

    public StockTry(double pr, double vol, int bV, int aV){
	price = pr;
	volatility = vol;
	bids = new ArrayList<Double>(bV);
	asks = new ArrayList<Double>(aV);
	populate(bV, aV);
    }

    public void populate(int bV, int aV){
	for (int i = 0; i < bV; i++){
	    double newB = this.newPurch();
	    if (marketPos == false)
		newB = newB*.98;
	    else
		newB = newB*1.02;
	    bids.add(newB);
	    double newA = this.newPurch();
	    if (marketPos == false)
		newA = newA*.98;
	    else
		newA = newA*1.02;
	    asks.add(newA);
	}
    }

    // ASSSUMPTIONS: volume of trades on a stock remains constant
    // :volatility of stock remains constant, not updated
    // :no other stocks trading on dow other than this. Market swings with stock
    // :replace .sort with running med
    // :bids below new price not eliminated, asks above new price not eliminated
    public void loop(){
	System.out.println("" + price);
	QuickSort b = new QuickSort(bids);
	bids = b.qsort(bids);
        QuickSort a = new QuickSort(asks);
	asks = a.qsort(asks);
	int ind = bids.size()/2;
	double bidMed = bids.remove(ind);
	double askMed = asks.remove(ind);
	price = (bidMed + askMed) / 2; // new price
	double newB = this.newPurch();
	if (marketPos == false)
	    newB = newB*.98;
	else
	    newB = newB*1.02;
	bids.add(newB);
	System.out.println("newBid: " + newB);
	double newA = this.newPurch();
	if (marketPos == false)
	    newA = newA*.98;
	else
	    newA = newA*1.02;
	asks.add(newA);
	System.out.println("newBid: " + newA);
    }

    public double newPurch(){
	Random r = new Random();
	double d = r.nextDouble() * volatility;
	if (r.nextInt(2) == 1)
	    d = 0-d;
	return (price + price*d);
    }


    //main method for testing
    public static void main ( String[] args ) {
	StockTry st = new StockTry(30.0, .1, 100, 100);
	for (int i = 0; i < 100; i++)
	    st.loop();
    }



}
