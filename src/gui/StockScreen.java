import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import org.math.plot.*;

public class StockScreen extends JPanel {
    Plot2DPanel plot; // from jmathplots, it extends JPanel
    JLabel stats;
    JLabel news;

    public StockScreen() {
		setPreferredSize(new Dimension(1000, 650)); // set it to the same size as the container
		setLayout(null); // it will be a card in the main deck
		setBackground(Color.black);

		// Set up graph
		//plot = new Plot2DPanel();
		//double[] x = { 1, 2, 3, 4, 5, 6 };
		//double[] y = { 45, 89, 6, 32, 63, 12 };
		//plot.addLinePlot("Test Plot", x, y);
		//plot.setBorder(BorderFactory.createTitledBorder("Test Plot"));
		//plot.setBounds(10,50, 980, 590);
		//add(plot);

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

		



		revalidate();
		repaint();
    }
    
}
