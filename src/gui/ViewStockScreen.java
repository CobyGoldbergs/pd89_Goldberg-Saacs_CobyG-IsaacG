import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import org.math.plot.*;

public class ViewStockScreen extends JPanel {
    Plot2DPanel plot;

    public ViewStockScreen() {
		setPreferredSize(new Dimension(1000, 650)); // set it to the same size as the container
		setLayout(null); // it will be a card in the main deck
		setBackground(Color.black);

		plot = new Plot2DPanel();
		double[] x = { 1, 2, 3, 4, 5, 6 };
		double[] y = { 45, 89, 6, 32, 63, 12 };
		plot.addLinePlot("Test Plot", x, y);
		plot.setBorder(BorderFactory.createTitledBorder("Test Plot"));
		plot.setBounds(10,50, 980, 590);

		add(plot);
		revalidate();
		repaint();
    }
    
}