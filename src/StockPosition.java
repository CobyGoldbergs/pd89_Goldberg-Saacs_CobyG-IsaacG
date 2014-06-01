public class StockPosition{

    private String ticker; // the stock that is this position
    private int quantityShares; // how many shares are owned of this stock

    public StockPosition(Stock st, int numShares){
	ticker = st.getTicker();
	quantityShares = numShares;
    }

    public void addShares(int numShares){
	quantityShares += numShares;
    }

    // pre-condition: There are enough shares owned to sell
    public void sellShares(int numShares){
	quantityShares -= numShares;
    }

    // accessor methods
    public String getTicker(){return ticker;}
    public int getNumShares(){return quantityShares;}

}
