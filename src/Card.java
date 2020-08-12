import java.awt.*;
/**
 * 
 
* @author Choeney & Nguyen
 *
 */
public class Card {
	// Type of cards
	final static int heart = 0;
	final static int diamond = 1;
	final static int club = 2;
	final static int spade = 3;
	// List of cards
	final static String cards[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	public final static int width = 50;
	public final static int height = 70;

	// Constructor each card have color, value, and face up or down
	private int suit;
	private int value;
	private boolean faceUp;

	public Card(int c, int v) {
		this.suit=c;
		this.value=v;
		this.setFaceUp(false);
	}

	/**
	 * Get the color of the card
	 * @return the color
	 */
	public int getColor() {
		return this.suit;
	}


	/**
	 * Get the value of the card
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Set the value of the card
	 * @param cards2 the value to set
	 */
	public void setValue(int cards2) {
		this.value = cards2;
	}

	/**
	 * Check if the card is face up
	 * @return the faceUp
	 */
	public boolean isFaceUp() {
		return faceUp;
	}

	/**
	 * Set the face up status of the card 
	 * @param faceUp the faceUp to set
	 */
	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}

	/**
	 * Flip the card
	 */
	public void flip() {
		faceUp = !faceUp;
	}
	
	/**
	 * Get the color of the card 
	 * @return color of the card
	 */
	public Color color() {
		if(isFaceUp())
			if(getColor()==heart || getColor()==diamond) 
				return Color.red;
			else 
				return Color.black;
			return Color.yellow;
	}
	
	/**
	 * Draw the card
	 * @param g Graphics
	 * @param x the coordinates
	 * @param y the coordinates
	 */
	public void draw (Graphics g, int x, int y) {
		String names[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		
		g.clearRect(x, y, width, height);
		g.setColor(Color.blue);
		g.drawRect(x,  y,  width, height);
		g.setColor(color());;
		if(isFaceUp()) {
			g.drawString(names[getValue()], x+3, y+15);
			if(getColor()==heart) {
				g.drawLine(x+25,  y+30, x+35, y+20);
				g.drawLine(x+35,  y+20, x+45, y+30);
				g.drawLine(x+45,  y+30, x+25, y+60);
				g.drawLine(x+25,  y+60, x+5, y+30);
				g.drawLine(x+5,  y+30, x+15, y+20);
				g.drawLine(x+15,  y+20, x+25, y+30);
			}
			else if (getColor() == spade){
			    g.drawLine(x+25, y+20, x+40, y+50);
			    g.drawLine(x+40, y+50, x+10, y+50);
			    g.drawLine(x+10, y+50, x+25, y+20);
			    g.drawLine(x+23, y+45, x+20, y+60);
			    g.drawLine(x+20, y+60, x+30, y+60);
			    g.drawLine(x+30, y+60, x+27, y+45); 
			  }
			else if (getColor() == diamond){
			    g.drawLine(x+25, y+20, x+40, y+40);
			    g.drawLine(x+40, y+40, x+25, y+60);
			    g.drawLine(x+25, y+60, x+10, y+40);
			    g.drawLine(x+10, y+40, x+25, y+20);
			  }
			else if (getColor() == club) {
			    g.drawOval(x+20, y+25, 10, 10);
			    g.drawOval(x+25, y+35, 10, 10);
			    g.drawOval(x+15, y+35, 10, 10);
			    g.drawLine(x+23, y+45, x+20, y+55);
			    g.drawLine(x+20, y+55, x+30, y+55);
			    g.drawLine(x+30, y+55, x+27, y+45); 
			  }
		     }
		    else {
			g.setColor(Color.yellow);
			g.drawLine(x+15, y+5, x+15, y+65);
			g.drawLine(x+35, y+5, x+35, y+65);
			g.drawLine(x+5, y+20, x+45, y+20);
			g.drawLine(x+5, y+35, x+45, y+35);
			g.drawLine(x+5, y+50, x+45, y+50);
		      }
		}
}
