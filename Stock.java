public class Stock{

    private String ticker; // name of stock
    private double price; // price
    private double beta; // degree to which stock mirrors market. b > 1 means exaggerated response to market movement. b < -1 means exaggerated response in opposite direction of market
    private double volatility; // degree to which stock fluctuates
    private double marketStrength; // measure of market as a whole between -1 and 1
    private RunMed bids; // a running median counter of bids
    private RunMed asks; // a running median counter of asks

    public Stock(double t, double p, double b, double v, double ms){
	ticker = t;
	price = p;
	beta = b;
	volatility = v;
	marketStrength = ms;
	bids = new RunMed();
	asks = new RunMed();
	initialPurchases(); // function to create some initial bids and asks
    }

    // populates bid and ask lists with values and sets price
    public void initialPurhcases(){
	for (int i = 0; i < 100; i++){
	    double newBid = newPurchase(); // calls function that creates realistic new purchase
	    bids.insert(newBid); // add new bid to the running list of bids
	    double newAsk = newPurchase();
	    asks.insert(newAsk);
	}
	price = (bids.getMedian() + asks.getMedian()) / 2; // new price set at the average of the bids and asks
    }

    public void priceUpdate(){
	System.out.println("" + price); // for testing purposes
	double bidMedian = bids.getMedian();
	double askMedian = asks.getMedian();
	price = (bidMedian + askMedian) / 2;
	double newBid = newPurhcase();
	bids.insert(newBid);
	double newAsk = newPurchase();
	asks.insert(newAsk);
    }

    public static void main(String[] args){

    }



}
