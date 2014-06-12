import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ViewStockScreen extends JPanel {
    protected JButton homeButton; // protected so that it can be accessed in MainFrame's actionlistener
    protected JButton ticker1, ticker2, ticker3, ticker4, ticker5, ticker6, ticker7, ticker8, ticker9, ticker10;
    private JLabel viewStocksLabel;

    public ViewStockScreen() { // eventually should take a ticker argument and display it accordingly
		setPreferredSize(new Dimension(1000, 650)); // set it to the same size as the container
		setLayout(null); // it will be a card in the main deck
		setBackground(Color.black);

		// Set up the ticker label
		viewStocksLabel = new JLabel("<html> <h1> <i>View Stocks</i> </h1> </html>"); // should use ticker
		viewStocksLabel.setBackground(Color.black);
		viewStocksLabel.setForeground(Color.white);
		add(viewStocksLabel); // add to panel
		viewStocksLabel.setBounds(10, 15, 175, 20);
		viewStocksLabel.setOpaque(true);

		// Set up button to view stocks page
		homeButton = new JButton("Home");
		add(homeButton);
		homeButton.setBounds(890, 10, 100, 30);

		// Set up tickers
		ticker1 = new JButton("Ticker1");
		add(ticker1);
		ticker1.setBounds(200, 125, 200, 50);

		ticker2 = new JButton("Ticker2");
		add(ticker2);
		ticker2.setBounds(200, 225, 200, 50);
		
		ticker3 = new JButton("Ticker3");
		add(ticker3);
		ticker3.setBounds(200, 325, 200, 50);

		ticker4 = new JButton("Ticker4");
		add(ticker4);
		ticker4.setBounds(200, 425, 200, 50);

		ticker5 = new JButton("Ticker5");
		add(ticker5);
		ticker5.setBounds(200, 525, 200, 50);

		ticker6 = new JButton("Ticker6");
		add(ticker6);
		ticker6.setBounds(600, 125, 200, 50);

		ticker7 = new JButton("Ticker7");
		add(ticker7);
		ticker7.setBounds(600, 225, 200, 50);

		ticker8 = new JButton("Ticker8");
		add(ticker8);
		ticker8.setBounds(600, 325, 200, 50);

		ticker9 = new JButton("Ticker9");
		add(ticker9);
		ticker9.setBounds(600, 425, 200, 50);

		ticker10 = new JButton("Ticker10");
		add(ticker10);
		ticker10.setBounds(600, 525, 200, 50);
    }

    public void giveActionListener(ActionListener a) {
		homeButton.addActionListener(a);
    }
    
}
