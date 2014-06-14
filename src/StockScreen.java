import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import org.math.plot.*;

public class StockScreen extends JPanel {
    private Plot2DPanel plot; // from jmathplots, it extends JPanel
    protected JButton backButton; // protected so that it can be accessed in MainFrame's actionlistener
    private JLabel tickerLabel;
    private JLabel statsLabel;
    private JLabel newsLabel;

    protected JTextField quantity;
    protected JButton buyButton, sellButton;
    private JLabel buyLabel;
    private JLabel quantityLabel;
    protected JLabel errorMessage;

    private Market market;
    private Stock stock;

    public StockScreen(Market m) { // eventually should take a ticker argument and display it accordingly
	market = m;
	setPreferredSize(new Dimension(1000, 650)); // set it to the same size as the container
	setLayout(null); // it will be a card in the main deck
	setBackground(Color.black);

	stock = market.getStocks().get(0);

	// Set up the ticker label
	tickerLabel = new JLabel("<html> <h1> <i>" + stock.getTicker() + "</i> </h1> </html>"); // should use ticker
	tickerLabel.setBackground(Color.black);
	tickerLabel.setForeground(Color.white);
	add(tickerLabel); // add to panel
	tickerLabel.setBounds(10, 15, 100, 20);
	tickerLabel.setOpaque(true);

	// Set up button to view stocks page
	backButton = new JButton("Back");
	add(backButton);
	backButton.setBounds(890, 10, 100, 30);

	// Set up graph
	plot = new Plot2DPanel();
	double[] x = { 1, 2, 3, 4, 5, 6 }; //getX(ticker) FROM HERE --> TIME
	double[] y = { 45, 89, 6, 32, 63, 12 }; //getY(ticker)
	plot.addLinePlot("Test Stock Plot", x, y); // create line plot from jmathplots
	plot.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Test Stock Plot"));
	plot.setBounds(170,50, 820, 390);
	add(plot); // add to main jpanel

	//Set up stats label
	String statsText = "<html><h1 align='center'; style='padding:5';> STATS </h1>"; // add the text to the stats panel
	statsText += "<h3 style='padding:5'>Coby is whack</h3></html>";
	// statsText += getStats(); --> implement later

	statsLabel = new JLabel(statsText, SwingConstants.CENTER);
	statsLabel.setBorder(BorderFactory.createLoweredBevelBorder());
	add(statsLabel);
	statsLabel.setBounds(10, 50,150, 390);
	statsLabel.setOpaque(true);

	// Set up news label
	String newsText = "<html><h1 align='center'; style='padding:5';> NEWS </h1>"; // add the text to the news panel
	newsText += "<h3 style='padding:5'>Coby is whack</h3></html>";
	// newsText += getNews(); --> implement later
		
	newsLabel = new JLabel(newsText, SwingConstants.CENTER); // instantiate news panel and give it a border
	newsLabel.setBorder(BorderFactory.createLoweredBevelBorder());
	add(newsLabel); // add and position newsLabel panel
	newsLabel.setBounds(10, 450, 540, 190);
	newsLabel.setOpaque(true);

	buyLabel = new JLabel("<html> <h2> <i>Buy/Sell Stock in " + stock.getTicker() +"</i> </h2> </html>");
	buyLabel.setBounds(700, 450, 220, 50);
	buyLabel.setBackground(Color.black);
	buyLabel.setForeground(Color.white);
	add(buyLabel);

	quantityLabel = new JLabel("<html> <h3> <i>Enter Quantity:");
	quantityLabel.setBounds(680, 500, 120, 30);
	quantityLabel.setBackground(Color.black);
	quantityLabel.setForeground(Color.white);
	add(quantityLabel);

	errorMessage = new JLabel("<html> <h3> <i>Invalid Entry:");
	quantityLabel.setBounds(1000, 500, 120, 30);
	quantityLabel.setBackground(Color.black);
	quantityLabel.setForeground(Color.white);
	add(errorMessage);

	quantity = new JTextField();
	quantity.setBounds(795, 500, 120, 30);
	add(quantity);

	buyButton = new JButton("Buy");
	buyButton.setBounds(730, 540, 100, 30);
	add(buyButton);

	sellButton = new JButton("Sell");
	sellButton.setBounds(730, 580, 100, 30);
	add(sellButton);
    }

    public void giveActionListener(ActionListener a) {
	backButton.addActionListener(a);
	buyButton.addActionListener(a);
	sellButton.addActionListener(a);
    }

    public void setStock(int index) { // Used in mainframe when button is pressed from view stock screen
    	stock = market.getStocks().get(index);
    	tickerLabel.setText("<html> <h1> <i>" + stock.getTicker() + "</i> </h1> </html>");
    }
    
}
