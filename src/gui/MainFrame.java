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
    
    public MainFrame() {
	
	setTitle("Goldberg & Saac's"); // create invisible frame with title
	setDefaultCloseOperation(EXIT_ON_CLOSE); // exits program when exits gui
	setResizable(false); // no resizing
	setVisible(true); // make it visible

	Container con = this.getContentPane(); // get the container the frame is in
	con.setPreferredSize(new Dimension(1000, 650)); // set the size of the container and the frames it contains
	
	pane = new JPanel();
	pane.setLayout(new CardLayout()); // create the main window holder and make it a deck
	
	home = new HomeScreen(); // instantiate the home screen
	stockScreen = new StockScreen(); // instantiate the stocks screen
	viewStocks = new ViewStockScreen();

	// Give Action Listeners
	home.giveActionListener(this);	

	// put the screens in the deck
	pane.add(home, "home");
	pane.add(stockScreen, "view");
	pane.add(viewStocks, "stock");
	
	pane.requestFocus(); // make sure the pane has the focus not anything behind it
	con.add(pane); // put the pain into the container
	pack(); // pack up the container

	setLocationRelativeTo(null); // center it
    }

    public void actionPerformed(ActionEvent e) {
	JButton source = (JButton) e.getSource();
	CardLayout card = (CardLayout) pane.getLayout();
    }
    
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
		public void run(){
		    MainFrame f = new MainFrame();
		}
	    }); // places frame in swing event queue so it can run smoothly
    }   
}
