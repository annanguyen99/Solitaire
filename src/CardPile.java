import java.util.Stack;
import java.awt.Color;
import java.awt.Graphics;
import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.Random;
/**
 * @author tchoeney001
 *
 */
public class CardPile {
	protected int x;
	protected int y;
	protected Stack<Card> pile;

	CardPile(int x2, int y2) {
		x = x2;
		y=y2;
		pile = new Stack<Card>();
	}
	public final Card top() {
		return pile.peek();
	}
	public final boolean isEmpty() {
		return pile.empty();
	}
	public final Card pop() {
		return pile.pop();
	}

	public void addCard(Card aCard) {
		pile.push(aCard);
	}

	/**
	 * Display the card pile
	 * @param g
	 */
	public void display(Graphics g) {
		g.setColor(Color.blue);
		if(isEmpty()) {

			g.drawRect(x,  y,  Card.width, Card.height);
		}
		else {
			top().draw(g, x,  y);;
		}
	}
	public boolean canTake(Card aCard) {
		return false;
	}

	public boolean includes(int tx, int ty) {
		return x <= tx && tx <= x + Card.width 
				&& y <= ty && ty <=y + Card.height;
	}
	public void select(int tx, int ty) {
		// TODO Auto-generated method stub
	}
}
class DiscardPile extends CardPile{
	/**
	 * The constructor
	 * @param x
	 * @param y
	 */
	DiscardPile(int x, int y) {
		super(x, y);

	}

	//only add card is it is flipped
	public void addCard(Card aCard) {
		if(!aCard.isFaceUp()) {
			aCard.flip();
		}
		super.addCard(aCard);


	}
	public void select(int tx, int ty) {
		if(isEmpty()) {
			return;
		}
		Card topCard = pop();
		for (int i =0; i<4; i++) {
			if (Solitare.acePile[i].canTake(topCard)) {
				Solitare.acePile[i].addCard(topCard);
				return;
			}
		}
		for (int i =0; i< 7; i++) {
			if (Solitare.tableau[i].canTake(topCard)) {
				Solitare.tableau[i].addCard(topCard);
				return;
			}
		}
		addCard(topCard);
	}
}
class DeckOfCard extends CardPile{

	DeckOfCard(int ax, int ay) {
		super(ax, ay);

		//Create a full deck of card
		for(int i =0; i<4; i++) {
			for(int j = 0; j < 14; j++) {
				Card card = new Card(i, j);
				addCard(card);		
			}
		}
		this.shuffle(pile);
	}

	public void shuffle(Stack<Card> cards) {
		Random r = new Random();
		for ( int n = cards.size() - 1; n >0; n--) {
			int k = r.nextInt(n + 1);
			Card temp = cards.get(n);
			cards.set(n, cards.get(k));
			cards.set(k, temp);

		}
	}
	public void select(int x, int y) {
		if(isEmpty()) {
			return;
		}
		Solitare.discardPile.addCard(pop());
	}

}

class AcePile extends CardPile 
{
	public AcePile(int x, int y){
		super(x, y);
	}

	public boolean canTake (Card aCard) 
	{
		if (isEmpty())
			return aCard.getValue()==0;

		Card topCard = top();
		return (aCard.getColor() == topCard.getColor()) &&
				(aCard.getValue() == 1 + topCard.getValue());
	}
}

class TablePile extends CardPile{
	TablePile(int x, int y, int z){
		super(x, y);
		for(int i=0; i < z; i++) {
			addCard(Solitare.deckCard.pop());
		}
		top().flip();
	}
	public boolean canTake(Card aCard) {
		if(isEmpty()) {
			return aCard.getValue()==12;
		}
		Card topCard = top();
		return (aCard.color() != topCard.color()) && (aCard.getValue()==topCard.getValue()-1);
	}
	public boolean includes(int tx, int ty) {
		return x <= tx && tx <= x + Card.width && y <= ty;
	}
	public void display(Graphics g) {
		int y3 = y;
		
		for(Enumeration<Card> e = pile.elements(); e.hasMoreElements();) {
			
			Card aCard = (Card) e.nextElement();
			aCard.draw(g,  x,  y3);;
			y3 += 35;
		}
		

	}
	public void select(int tx, int ty) {
		if(isEmpty()) {
			return;
		}
		Card topCard= top();
		if(!topCard.isFaceUp()) {
			topCard.flip();
			return;
		}
		for(int i =0; i < 4; i++) {
			if(Solitare.acePile[i].canTake(topCard)) {
				Solitare.acePile[i].addCard(pop());
				return;
			}
		}
		for(int j = 0; j < 7; j++) {
			if(Solitare.tableau[j].canTake(topCard)) {
				Solitare.tableau[j].addCard(pop());
				return;
			}
		}
		addCard(topCard);
	}

}

