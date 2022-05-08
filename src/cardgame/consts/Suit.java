package cardgame.consts;

/**
 * スートに関するEnumクラス
 *
 * @author kijima
 *
 */
public enum Suit {

	// スペード
	SPADE("♠︎"),
	// ダイヤ
	DAIYA("♦︎"),
	// クローバー
	CLOVER("☘"),
	// ハート
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
	 * @param suit
	 * @return
	 */
	public String getSuit() {
		return suit;
	}
}
