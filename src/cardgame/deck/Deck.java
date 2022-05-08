package cardgame.deck;

import java.util.ArrayList;
import java.util.Collections;
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

	/**
	 * 山札シャッフル
	 *
	 * @param deck
	 */
	public void shuffle() {
		Collections.shuffle(deck);
	}

	/**
	 * デッキの先頭からカードを一枚取り出す
	 *
	 * @param deck
	 */
	public Card pick() {

		// デッキの先頭からカードを一枚取り出す
		Card card = deck.get(0);

		// 山札からカードを一枚削除する
		deck.remove(0);

		return card;
	}
}
