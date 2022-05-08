package cardgame.card;

public class Card {

	/**
	 * 数字
	 */
	private final int num;

	/**
	 * スート（クラブ・スペード・ダイヤ・ハートの4つのマーク）
	 */
	private final String suit;

	/**
	 * ランク
	 */
	private final String rank;

	/**
	 * コンストラクタ
	 *
	 * @param num
	 * @param suit
	 * @param rank
	 */
	public Card(int num, String suit, String rank) {
		this.num = num;
		this.suit = suit;
		this.rank = rank;
	}

	public String getSuit() {
		return suit;
	}

	public int getNum() {
		return num;
	}

	public String getRank() {
		return rank;
	}
}
