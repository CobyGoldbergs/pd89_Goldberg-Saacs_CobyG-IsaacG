import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class HomeScreen extends JPanel {
    private JButton viewStocksButton;

    public HomeScreen() {
	setPreferredSize(new Dimension(1000, 750)); // set it to the same size as the container
	setLayout(null); // it will be a card in the main deck
	setBackground(Color.black);

	viewStocksButton = new JButton("View Stocks");
	add(viewStocksButton);
    }
    
}