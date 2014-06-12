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
    private JLabel stats;
    private JLabel news;

    private Market market;
    private Stock stock;

    public StockScreen() { // eventually should take a ticker argument and display it accordingly
		setPreferredSize(new Dimension(1000, 650)); // set it to the same size as the container
		setLayout(null); // it will be a card in the main deck
		setBackground(Color.black);

		market = new Market();
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

		stats = new JLabel(statsText, SwingConstants.CENTER);
		stats.setBorder(BorderFactory.createLoweredBevelBorder());
		add(stats);
		stats.setBounds(10, 50,150, 390);
		stats.setOpaque(true);

		// Set up news label
		String newsText = "<html><h1 align='center'; style='padding:5';> NEWS </h1>"; // add the text to the news panel
		newsText += "<h3 style='padding:5'>Coby is whack</h3></html>";
		// newsText += getNews(); --> implement later
		
		news = new JLabel(newsText, SwingConstants.CENTER); // instantiate news panel and give it a border
		news.setBorder(BorderFactory.createLoweredBevelBorder());
		add(news); // add and position news panel
		news.setBounds(10, 450, 980, 190);
		news.setOpaque(true);
    }

    public void giveActionListener(ActionListener a) {
		backButton.addActionListener(a);
    }

    public void setStock(int index) { // Used in mainframe when button is pressed from view stock screen
    	stock = market.getStocks().get(index);
    	tickerLabel.setText("<html> <h1> <i>" + stock.getTicker() + "</i> </h1> </html>");
    }
    
}
