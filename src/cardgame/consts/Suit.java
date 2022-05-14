package cardgame.consts;

/**
 * スートに関するEnumクラス
 *
 * @author kijima
 *
 */
public enum Suit {

	SPADE("♠︎"),
	DAIYA("♦︎"),
	CLOVER("☘"),
	HEART("❤︎");

	/**
	 * スート
	 */
	private final String suit;

	/**
	 * コンストラクタ
	 *
	 * @param suit
	 */
	private Suit(String suit) {
		this.suit = suit;
	}

	/**
	 * スートを返却
	 *
	 * @return
	 */
	public String getSuit() {
		return suit;
	}
}
