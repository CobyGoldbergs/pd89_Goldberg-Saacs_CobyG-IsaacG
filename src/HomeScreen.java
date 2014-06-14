import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.BorderFactory;

public class HomeScreen extends JPanel {
    protected JButton viewStocksButton; // protected so that it can be accessed in MainFrame's actionlistener
    private JLabel homeLabel;
    private JLabel news;
    private JLabel hotStocks;
    private JLabel coldStocks;
    private JLabel myPort;
    private Market market;
    

    public HomeScreen(Market m) {
	setPreferredSize(new Dimension(1000, 650)); // set it to the same size as the container
	setLayout(null); // it will be a card in the main deck
	setBackground(Color.black);

	market = m;

	// Set up the home label
	homeLabel = new JLabel("<html> <h1> <i>Home</i> </h1> </html>");
	homeLabel.setBackground(Color.black);
	homeLabel.setForeground(Color.white);
	add(homeLabel); // add to panel
	homeLabel.setBounds(10, 15, 100, 20);
	homeLabel.setOpaque(true);


	// Set up hot stocks label
	String hotText = "<html> <h1 align='center'>Hot Stocks</h1> <h3 style='padding:5'>";
	LinkedList<Stock> hotties = m.getStrongestStocks(4);
	Stock st;
	for (int i = 0; i < 4; i++){
	    st = hotties.get(i);
	    hotText += st.getTicker() + " : " + st.getPercentChange() + "\n";
	}
	hotText += "</h3></html>";

	hotStocks = new JLabel(hotText, SwingConstants.CENTER);
	hotStocks.setBorder(BorderFactory.createLoweredBevelBorder());
	add(hotStocks);
	hotStocks.setBounds(10, 50, 320, 290);
	hotStocks.setOpaque(true);


	// Set up cold stocks label
	String coldText = "<html> <h1 align='center'>Cold Stocks</h1> <h3 style='padding:5'>";
	LinkedList<Stock> weakies = m.getWeakestStocks(4);
	for (int i = 0; i < 4; i++){
	    st = weakies.get(i);
	    coldText += st.getTicker() + " : " + st.getPercentChange() + "\n";
	}
	coldText += "</h3></html>";

	coldStocks = new JLabel(coldText, SwingConstants.CENTER);
	coldStocks.setBorder(BorderFactory.createLoweredBevelBorder());
	add(coldStocks);
	coldStocks.setBounds(340, 50, 320, 290);
	coldStocks.setOpaque(true);


	// Set up button to view stocks page
	viewStocksButton = new JButton("View Stocks");
	add(viewStocksButton);
	viewStocksButton.setBounds(890, 10, 100, 30);


	// Set up news label
	String newsText = "<html><h1 align='center'; style='padding:5';> NEWS </h1>"; // add the text to the news panel
	newsText += "<h3 style='padding:5'>Coby is whack</h3></html>";
	// newsText += getNews(); --> implement later

	news = new JLabel(newsText, SwingConstants.CENTER); // instantiate news panel and give it a border
	news.setBorder(BorderFactory.createLoweredBevelBorder());
	add(news); // add and position news panel
	news.setBounds(10, 350, 650, 290);
	news.setOpaque(true);

	// Set up the portfolio
	String portText = "<html> <h1 align='center'>Portfolio</h1> </html>";
	// portText += getHotStocks(); --> implement later

	myPort = new JLabel(portText, SwingConstants.CENTER);
	myPort.setBorder(BorderFactory.createLoweredBevelBorder());
	add(myPort);
	myPort.setBounds(670, 50, 320, 590);
	myPort.setOpaque(true);

    }
    public void giveActionListener(ActionListener a) {
	viewStocksButton.addActionListener(a);
    }
}
