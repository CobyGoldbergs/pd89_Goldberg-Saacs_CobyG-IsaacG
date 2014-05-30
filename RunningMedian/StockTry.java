// screwing around with movement of price
// bid and ask movement working to some degree
// market sentiment needs to be able to update itself based on whats going on in the simulator
// momentum indicators should be added for mild changes in direction

import java.util.*;
import java.lang.*;

public class StockTry{

    private double price;
    private double volatility; // creates stronger changes in prices
    private double beta; // need to think about how to use it to change movement with market
    private double marketStrength; // measure of market strength on scale of -1 (unhealthiest) to 1 (healthiest)
    private RunMed runnerB; // running median to keep track of bid median
    private RunMed runnerA; // running median to keep track of ask median

    public StockTry(double pr, double vol, double bet, double ms){
	price = pr;
	volatility = vol;
	beta = bet;
	marketStrength = ms;
	runnerB = new RunMed();
	runnerA = new RunMed();
	populate();
    }

    public void populate(){
	for (int i = 0; i < 100; i++){
	    double newB = this.newPurch(); // calls method to create a semi-random new values
	    runnerB.insert(newB); // update the running median for bids
	    double newA = this.newPurch();
	    runnerA.insert(newA);
	}
	price = (runnerA.getMedian() + runnerB.getMedian()) / 2; // new price found with the averages of the two running medians
    }

    // updates the price
    // ASSSUMPTIONS: volume of trades on a stock remains constant
    // :volatility of stock remains constant, not changed
    // :bids and asks stay forever
    // market sentiment never changes
    public void priceUpdate(){
	System.out.println("" + price); // for testing purposes
	double bidMed = runnerB.getMedian();
	double askMed = runnerA.getMedian();
	price = (bidMed + askMed) / 2; // new price is average of the median bid and median ask
	double newB = this.newPurch();
	runnerB.insert(newB);
	double newA = this.newPurch();
	runnerA.insert(newA);
    }

    // creates random new bid/ ask
    public double newPurch(){
	Random r = new Random();
	double marketEffect = marketStrength * beta; // strength less than one causes decrease in price, beta simply exaggerates or mitigate's market's effect
	double d = r.nextDouble() * volatility * marketEffect; // higher volatility makes new purchase deviate further from current price
	int markSent = (int)(marketStrength * 50) + 1; // +1 meant to avoid errors on nextInt when strength is zero
	markSent = Math.abs(markSent);
	if (r.nextInt(markSent) == 1)
	    d = 0-d; // adds degree of fluctuation. A market sentiment closer to zero is more likely to yield a reverse in direction of price 
	return (price + price*d);
    }


    //main method for testing
    public static void main ( String[] args ) {
	StockTry st = new StockTry(30.0, .3, .8, -.1); 
	// start price, volatility, beta, market strength
	for (int i = 0; i < 300; i++)
	    st.priceUpdate();
    }



}
