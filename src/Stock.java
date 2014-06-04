import java.util.*;
import java.lang.*;

public class Stock{

    private String ticker; // name of stock
    private double price; // price
    private double beta; // degree to which stock mirrors market. b > 1 means exaggerated response to market movement. b < -1 means exaggerated response in opposite direction of market
    private double volatility; // degree to which stock fluctuates
    private double marketStrength; // measure of market as a whole between -1 and 1
    private RunMed bids; // a running median counter of bids
    private RunMed asks; // a running median counter of asks
    private LinkedList<Double> pastPrices; // for use in calculating percent change
    private double percentChange; // queue for use of fifo properties
    /*private Stack<Double> oneDayMVA; // will be created from the day's trading
    private Stack<Double> fiftyDayMVA; // to be downloaded from yahoo finance
    private Stack<Double> twohundredDayMVA;*/

    public Stock(String t, double p, double b, double v, double ms){
	ticker = t;
	price = p;
	beta = b;
	volatility = v;
	marketStrength = ms;
	bids = new RunMed();
	asks = new RunMed();
	price = p;
	percentChange = 0.0;
	pastPrices = new LinkedList<Double>();
	// add code to use 50 and 200 day mva
	initialPurhcases(); // function to create some initial bids and asks
    }

    // populates bid and ask lists with values and sets price
    public void initialPurhcases(){
	for (int i = 0; i < 100; i++){
	    double newBid = newPurchase(); // calls function that creates realistic new purchase
	    bids.insert(newBid); // add new bid to the running list of bids
	    double newAsk = newPurchase();
	    asks.insert(newAsk);
	}
	priceUpdate(); // new price set at the average of the bid average and ask average
    }

    public void priceUpdate(){
	double bidMedian = bids.getMedian();
	double askMedian = asks.getMedian();
	price = (bidMedian + askMedian) / 2; // new price is average of their medians
	price = (double)(Math.round(price * 100)) / 100; // rounds to 10th
	double newBid = newPurchase(); // calls helper method to create new purchase
	bids.insert(newBid);
	double newAsk = newPurchase();
	asks.insert(newAsk);
	updatePercent(); // method that updates percent change
    }

    /*Adds new purchase
      PRECONDITIONS: 
      -1 <= marketStrength <= 1 -1 weakest, 1 strongest
      0 < volatility < 1. 1 means huge fluctuations
      ALGORITHM: random double (less than one) * volatility (degree of fluctuation in price) * marketStrength (-1 means it is really weak and price should decrease, 1 means it is really strong) * beta ( beta > 1 will exaggerate market's strength, 1 > beta > 0 will dampen market effect, beta < 0 will move in oppposite of market) = price change.
      The closer market strength is to zero, the more likely it is that the stock will move in the opposite direction that market strength and beta would otherwise indicate.
     */
    public double newPurchase(){
	Random r = new Random();
	if (marketStrength == 0)
	    marketStrength += .01;
	double marketEffect = marketStrength * beta; // strength less than one causes decrease in price, beta exaggerates/ mitigate's market's effect
	double d = r.nextDouble() * volatility * marketEffect; // higher volatility makes new purchase deviate further from current price
	int marketSentiment = (int)(marketStrength * 50) + 1; // +1 meant to avoid errors on nextInt when strength is zero
	marketSentiment = Math.abs(marketSentiment); // for use in next int
	if (r.nextInt(marketSentiment) == 1)
	    d = 0-d; // adds degree of fluctuation. A market sentiment closer to zero is more likely to yield a reverse in direction of price
	return (price + price*d);
    }

    public void updatePercent(){
	pastPrices.add(price);
	if (pastPrices.size() > 300)
	    pastPrices.remove(); // remove oldest price if it's too long
	double oldP = pastPrices.peek(); // oldest value in queue
	percentChange = ((price - oldP)/price) * 100;
	percentChange = (double)(Math.round(percentChange * 100)) / 100; // rounds to 10th
    }

    // accessor methods
    public String getTicker(){return ticker;}
    public double getPrice(){return price;}
    public double getBeta(){return beta;}
    public double getVolatility(){return volatility;}
    public double getPercentChange(){return percentChange;}
    
    // outside classes can set a new market strength
    public void setMarketStrength(double ms){
	marketStrength += ms;
    }

    public void applyNews(News news){
	boolean effect = news.getEffect();
	if (effect)
	    marketStrength += .1;
	else
	    marketStrength -= .1;
    }

    public String toString(){
	return ticker;
    }

    public static void main(String[] args){
	Stock st = new Stock("AAPL", 30.0, 2, 1.5, .8); 
	// ticker name, start price, beta, volatility,  market strength
	for (int i = 0; i < 300; i++){
	    st.priceUpdate();
	    System.out.println("Price: " + st.getPrice() + "$");
	    System.out.println("Percent change: " + st.getPercentChange() + "%");
	}
    }



}
