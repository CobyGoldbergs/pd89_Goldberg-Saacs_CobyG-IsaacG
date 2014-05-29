// screwing around with movement of price
// bid and ask movement working to some degree
// market sentiment (right now statically either positive or negative) needs to be able to update itself based on whats going on in the simulator
// momentum indicators should be added for mild changes in direction

import java.util.*;

public class StockTry{

    double price;
    double volatility; // not currently used
    boolean marketPos = false; // true -- market trending up
    RunMed runnerB; // running median to keep track of bid median
    RunMed runnerA; // running median to keep track of ask median

    public StockTry(double pr, double vol, int bV, int aV){
	price = pr;
	volatility = vol;
	runnerB = new RunMed();
	runnerA = new RunMed();
	populate(bV, aV);
    }

    public void populate(int bV, int aV){
	for (int i = 0; i < bV; i++){
	    double newB = this.newPurch(); // calls method to create a semi-random new values
	    if (marketPos == false)
		newB = newB*.98; // poor market sentiment lowers prices
	    else
		newB = newB*1.02; // good market sentiment increases prices
	    runnerB.insert(newB); // update the running median
	    double newA = this.newPurch();
	    if (marketPos == false)
		newA = newA*.98;
	    else
		newA = newA*1.02;
	    runnerA.insert(newA);
	}
	price = (runnerA.getMedian() + runnerB.getMedian()) / 2; // new price found with the averages of the two running medians
    }

    // updates the price
    // ASSSUMPTIONS: volume of trades on a stock remains constant
    // :volatility of stock remains constant, not changed
    // :replace .sort with running med
    // :bids and asks stay forever
    // market sentiment never changes
    public void priceUpdate(){
	System.out.println("" + price);
	double bidMed = runnerB.getMedian();
	double askMed = runnerA.getMedian();
	price = (bidMed + askMed) / 2; // new price
	double newB = this.newPurch();
	if (marketPos == false)
	    newB = newB*.98;
	else
	    newB = newB*1.02;
	runnerB.insert(newB);
	//System.out.println("new Bid: " + newB);
	double newA = this.newPurch();
	if (marketPos == false)
	    newA = newA*.98;
	else
	    newA = newA*1.02;
	runnerA.insert(newA);
	//System.out.println("new Ask: " + newA);
    }

    // creates random new bid/ ask
    public double newPurch(){
	Random r = new Random();
	double d = r.nextDouble() * volatility; // higher volatility makes it deviate further form the price
	if (r.nextInt(2) == 1)
	    d = 0-d; // half of all requests are lower than price, half are higher
	return (price + price*d);
    }


    //main method for testing
    public static void main ( String[] args ) {
	StockTry st = new StockTry(30.0, .5, 100, 100);
	for (int i = 0; i < 100; i++)
	    st.priceUpdate();
    }



}
