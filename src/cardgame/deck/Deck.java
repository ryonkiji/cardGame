package cardgame.deck;

import java.util.ArrayList;
import java.util.List;

import cardgame.card.Card;

/**
 * Deckクラス
 *
 * @author kijima
 *
 */
public class Deck {

	/**
	 * 山札
	 */
	private List<Card> deck = new ArrayList<>();

	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(Card card) {
		this.deck.add(card);
	}

}
