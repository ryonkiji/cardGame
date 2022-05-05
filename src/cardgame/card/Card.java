package cardgame.card;

public class Card {

	/**
	 * スート（クラブ・スペード・ダイヤ・ハートの4つのマーク）
	 */
	private String suit;

	/**
	 * 数字
	 */
	private int num;

	/**
	 * ランク
	 */
	private String rank;

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
}
