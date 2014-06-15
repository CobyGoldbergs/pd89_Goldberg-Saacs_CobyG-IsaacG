import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame implements ActionListener{

    private JPanel pane; // Screen Holder
    private HomeScreen home;
    private StockScreen stockScreen;
    private ViewStockScreen viewStocks;
    private User user;
    private Market market;
    private String curTick;
 
    public MainFrame() {

	setTitle("Goldberg & Saac's"); // create invisible frame with title
	setDefaultCloseOperation(EXIT_ON_CLOSE); // exits program when exits gui
	setResizable(false); // no resizing
	setVisible(true); // make it visible
	
	Container con = this.getContentPane(); // get the container the frame is in
	con.setPreferredSize(new Dimension(1000, 650)); // set the size of the container and the frames it contains
		
	pane = new JPanel();
	pane.setLayout(new CardLayout()); // create the main window holder and make it a deck

	market = new Market();
	for (int i = 0; i < 4000; i++)
	    market.updateMarket(); // create initial history for the market
	
	user = new User(10000);
	
	home = new HomeScreen(market, user); // instantiate the home screen
	stockScreen = new StockScreen(market); // instantiate the stocks screen
	viewStocks = new ViewStockScreen();

	
	// Give Action Listeners
	home.giveActionListener(this);
	viewStocks.giveActionListener(this);
	stockScreen.giveActionListener(this);
	
	// put the screens in the deck
	pane.add(home, "home");
	pane.add(stockScreen, "view");
	pane.add(viewStocks, "stock");
		
	pane.requestFocus(); // make sure the pane has the focus not anything behind it
	con.add(pane); // put the pain into the container
	pack(); // pack up the container
	
	setLocationRelativeTo(null); // center it

	// Test
	for (int i = 0; i < 1000; i++)
	    market.updateMarket();
    }

    public void actionPerformed(ActionEvent e) {
	JButton source = (JButton) e.getSource();
	CardLayout card = (CardLayout) pane.getLayout();
		
	// From the home screen to the view stocks screen
	if (source.equals(home.viewStocksButton)){
	    card.show(pane, "stock");
	}

	// From the view stocks screen to the home screen
	else if (source.equals(viewStocks.homeButton)){
	    card.show(pane, "home");
	    home.setText();
	}

	// The different stocks
	else if (source.equals(viewStocks.AAPL)){
	    card.show(pane, "view");
	    stockScreen.setStock("AAPL");
	    curTick = "AAPL";
	}
	else if (source.equals(viewStocks.TSLA)){
	    card.show(pane, "view");
	    stockScreen.setStock("TSLA");
	    curTick = "TSLA";
	}
	else if (source.equals(viewStocks.GS)){
	    card.show(pane, "view");
	    stockScreen.setStock("GS");
	    curTick = "GS";
	}
	else if (source.equals(viewStocks.JPM)){
	    card.show(pane, "view");
	    stockScreen.setStock("JPM");
	    curTick = "JPM";
	}
	else if (source.equals(viewStocks.GOOG)){
	    card.show(pane, "view");
	    stockScreen.setStock("GOOG");
	    curTick = "GOOG";
	}
	else if (source.equals(viewStocks.MSFT)){
	    card.show(pane, "view");
	    stockScreen.setStock("MSFT");
	    curTick = "MSFT";
	}
	else if (source.equals(viewStocks.BAC)){
	    card.show(pane, "view");
	    stockScreen.setStock("BAC");
	    curTick = "BAC";
	}
	else if (source.equals(viewStocks.TWTR)){
	    card.show(pane, "view");
	    stockScreen.setStock("TWTR");
	    curTick = "TWTR";
	}
	else if (source.equals(viewStocks.FB)){
	    card.show(pane, "view");
	    stockScreen.setStock("FB");
	    curTick = "FB";
	}
	else if (source.equals(viewStocks.RTN)){
	    card.show(pane, "view");
	    stockScreen.setStock("RTN");
	    curTick = "RTN";
	}

	// From the stock screens to the view stock screen
	else if (source.equals(stockScreen.backButton)){
	    card.show(pane, "stock");
	}

	// Stock screen's purchase/ sale methods
	else if (source.equals(stockScreen.buyButton)){
	    Stock purchasing = market.getStockTicker(curTick);
	    String quantityStr = stockScreen.quantity.getText();
	    try {
		int quant = (int)(Integer.parseInt(quantityStr));
		String message = user.buyStock(purchasing, quant); // what is the result
		System.out.println(message);
		stockScreen.errorMessage.setText("<html> <h4> <i>" + message + "</i> </h4> </html>");
		stockScreen.errorMessage.setForeground(Color.white);
	    }
	    catch( Exception f ) {
		stockScreen.errorMessage.setText("<html> <h4> <i>Invalid entry" + "</i> </h4> </html>") ;
		stockScreen.errorMessage.setForeground(Color.white);
	    }
	}

	else if (source.equals(stockScreen.sellButton)){
	    Stock purchasing = market.getStockTicker(curTick);
	    System.out.println("Works");
	    String quantityStr = stockScreen.quantity.getText();
	    try {
		int quant = (int)(Integer.parseInt(quantityStr));
		String message = user.sellStock(purchasing, quant);
		stockScreen.errorMessage.setText("<html> <h4> <i>" + message + "</i> </h4> </html>");
		stockScreen.errorMessage.setForeground(Color.white);
	    }
	    catch( Exception f ) {
		stockScreen.errorMessage.setText("<html> <h4> <i> Invalid entry </i> </h4> </html>");
		stockScreen.errorMessage.setForeground(Color.white);
	    }
	}
    }

    public Market getMarket(){ return market;}

	
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
		public void run(){
		    MainFrame f = new MainFrame();
		}
	    }); // places frame in swing even queue so it can run smoothly
    }
}
