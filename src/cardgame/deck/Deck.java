package cardgame.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cardgame.consts.Card;

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
	private List<Card> deck;

	/**
	 * コンストラクタ
	 */
	public Deck() {

		deck = new ArrayList<>();

		for (Card card : Card.values()) {
			deck.add(card);
		}
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

	/**
	 * 21を超えないカードの残り枚数を取得
	 *
	 * @param target
	 * @return
	 */
	public int countMoreThan(final int target) {
		return (int) deck.stream().filter(card -> card.getPoint() > target).count();
	}

	/**
	 * 山札の枚数を取得
	 *
	 * @return
	 */
	public int size() {
		return deck.size();
	}

	/**
	 * 山札にカードを戻す
	 *
	 * @param card
	 */
	public void receiveCard(Card card) {
		deck.add(card);
	}
}
