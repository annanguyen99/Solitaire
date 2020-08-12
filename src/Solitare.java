import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * @author tchoeney001
 *
 */
public class Solitare extends JPanel{
	
	private static JFrame gameBoard;
	public static DeckOfCard deckCard;
	public static CardPile piles[];
	public static DiscardPile discardPile;
	public static AcePile acePile[];
	public static TablePile tableau[];

	public static void main(String[] args) {
		
		gameBoard = new JFrame();
		gameBoard.setSize(600, 500);
	    gameBoard.getContentPane().add(new Solitare());
	    gameBoard.setTitle("Solitaire Project");
		gameBoard.setVisible(true);;
		gameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameBoard.setResizable(false);
	}
	public Solitare() {
		
		gameBoard.addMouseListener(new MouseKeeper());
		Button restartButton = new Button("New game");
		restartButton.addActionListener(new RestartButtonListener());
		gameBoard.add("South",restartButton );
		init();
		gameBoard.show();
		
	}
	
	public void init() {
		piles = new CardPile[13];
		acePile = new AcePile[4];
		tableau = new TablePile[7];
		piles[0] =deckCard = new DeckOfCard (30, 30);
		piles[1] =discardPile = new DiscardPile (30, 110);
		for(int i = 0; i <4; i++) {
			piles[2+i] = acePile[i] = new AcePile(150 + (Card.width+10) * i, 30);
		}
		for(int i=0; i < 7; i++) {
			piles[6+i] = tableau[i] = new TablePile(150+(Card.width+5)*i, Card.height+35, i+1);
		}
	}
	public void paint(Graphics g) {
		for(int i = 0; i < 13; i++) {
			piles[i].display(g);
		}
	}
	
	/**
	 * Restart button
	 * @author mnguyen001
	 *
	 */
	private class RestartButtonListener implements ActionListener{
		public void actionPerformed (ActionEvent e) {
			init();
			repaint();
		}
	}
	
	private class MouseKeeper extends MouseAdapter{
		public void mousePressed (MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			for(int i = 0; i < 13; i++) {
				if (piles[i].includes(x, y)) {
					piles[i].select(x, y);
					repaint();
				}
			}
		}
	}
	
	
	
}


