import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.BorderFactory;

public class HomeScreen extends JPanel {
    private JButton viewStocksButton;
    private JLabel news;

    public HomeScreen() {
	setPreferredSize(new Dimension(850, 500)); // set it to the same size as the container
	setLayout(null); // it will be a card in the main deck
	setBackground(Color.black);

	viewStocksButton = new JButton("View Stocks");
	add(viewStocksButton);
	viewStocksButton.setBounds(740, 10, 100, 20);

	String text = "<html><h1 align='center'; style='padding:5';> NEWS </h1>"; // add the text to the news panel
	text += "<h3 style='padding:5'>Coby is whack</h3></html>";
	// getNews(); --> implement later
	news = new JLabel(text, SwingConstants.CENTER); // instantiate news panel and give it a border
	news.setBorder(BorderFactory.createLoweredBevelBorder());
	add(news); // add and position news panel
	news.setBounds(30, 30, 300, 180);
	news.setOpaque(true);
    }
    
}