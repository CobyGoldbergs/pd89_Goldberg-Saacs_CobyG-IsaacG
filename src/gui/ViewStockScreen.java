import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ViewStockScreen extends JPanel {
    private JButton homeButton;
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
		backButton = new JButton("Home");
		add(backButton);
		backButton.setBounds(890, 10, 100, 30);
    }
    
}
