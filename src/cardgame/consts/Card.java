package cardgame.consts;

/**
 * Cardクラス
 *
 * @author kijima
 *
 */
public enum Card {

	SPADE01(Suit.SPADE.getSuit(), CardRank.RANKA.getNum(), CardRank.RANKA.getRank()),
	SPADE02(Suit.SPADE.getSuit(), CardRank.RANK2.getNum(), CardRank.RANK2.getRank()),
	SPADE03(Suit.SPADE.getSuit(), CardRank.RANK3.getNum(), CardRank.RANK3.getRank()),
	SPADE04(Suit.SPADE.getSuit(), CardRank.RANK4.getNum(), CardRank.RANK4.getRank()),
	SPADE05(Suit.SPADE.getSuit(), CardRank.RANK5.getNum(), CardRank.RANK5.getRank()),
	SPADE06(Suit.SPADE.getSuit(), CardRank.RANK6.getNum(), CardRank.RANK6.getRank()),
	SPADE07(Suit.SPADE.getSuit(), CardRank.RANK7.getNum(), CardRank.RANK7.getRank()),
	SPADE08(Suit.SPADE.getSuit(), CardRank.RANK8.getNum(), CardRank.RANK8.getRank()),
	SPADE09(Suit.SPADE.getSuit(), CardRank.RANK9.getNum(), CardRank.RANK9.getRank()),
	SPADE10(Suit.SPADE.getSuit(), CardRank.RANK10.getNum(), CardRank.RANK10.getRank()),
	SPADE11(Suit.SPADE.getSuit(), CardRank.RANKJ.getNum(), CardRank.RANKJ.getRank()),
	SPADE12(Suit.SPADE.getSuit(), CardRank.RANKQ.getNum(), CardRank.RANKQ.getRank()),
	SPADE13(Suit.SPADE.getSuit(), CardRank.RANKK.getNum(), CardRank.RANKK.getRank()),
	HEART01(Suit.HEART.getSuit(), CardRank.RANKA.getNum(), CardRank.RANKA.getRank()),
	HEART02(Suit.HEART.getSuit(), CardRank.RANK2.getNum(), CardRank.RANK2.getRank()),
	HEART03(Suit.HEART.getSuit(), CardRank.RANK3.getNum(), CardRank.RANK3.getRank()),
	HEART04(Suit.HEART.getSuit(), CardRank.RANK4.getNum(), CardRank.RANK4.getRank()),
	HEART05(Suit.HEART.getSuit(), CardRank.RANK5.getNum(), CardRank.RANK5.getRank()),
	HEART06(Suit.HEART.getSuit(), CardRank.RANK6.getNum(), CardRank.RANK6.getRank()),
	HEART07(Suit.HEART.getSuit(), CardRank.RANK7.getNum(), CardRank.RANK7.getRank()),
	HEART08(Suit.HEART.getSuit(), CardRank.RANK8.getNum(), CardRank.RANK8.getRank()),
	HEART09(Suit.HEART.getSuit(), CardRank.RANK9.getNum(), CardRank.RANK9.getRank()),
	HEART10(Suit.HEART.getSuit(), CardRank.RANK10.getNum(), CardRank.RANK10.getRank()),
	HEART11(Suit.HEART.getSuit(), CardRank.RANKJ.getNum(), CardRank.RANKJ.getRank()),
	HEART12(Suit.HEART.getSuit(), CardRank.RANKQ.getNum(), CardRank.RANKQ.getRank()),
	HEART13(Suit.HEART.getSuit(), CardRank.RANKK.getNum(), CardRank.RANKK.getRank()),
	CLOVER01(Suit.CLOVER.getSuit(), CardRank.RANKA.getNum(), CardRank.RANKA.getRank()),
	CLOVER02(Suit.CLOVER.getSuit(), CardRank.RANK2.getNum(), CardRank.RANK2.getRank()),
	CLOVER03(Suit.CLOVER.getSuit(), CardRank.RANK3.getNum(), CardRank.RANK3.getRank()),
	CLOVER04(Suit.CLOVER.getSuit(), CardRank.RANK4.getNum(), CardRank.RANK4.getRank()),
	CLOVER05(Suit.CLOVER.getSuit(), CardRank.RANK5.getNum(), CardRank.RANK5.getRank()),
	CLOVER06(Suit.CLOVER.getSuit(), CardRank.RANK6.getNum(), CardRank.RANK6.getRank()),
	CLOVER07(Suit.CLOVER.getSuit(), CardRank.RANK7.getNum(), CardRank.RANK7.getRank()),
	CLOVER08(Suit.CLOVER.getSuit(), CardRank.RANK8.getNum(), CardRank.RANK8.getRank()),
	CLOVER09(Suit.CLOVER.getSuit(), CardRank.RANK9.getNum(), CardRank.RANK9.getRank()),
	CLOVER10(Suit.CLOVER.getSuit(), CardRank.RANK10.getNum(), CardRank.RANK10.getRank()),
	CLOVER11(Suit.CLOVER.getSuit(), CardRank.RANKJ.getNum(), CardRank.RANKJ.getRank()),
	CLOVER12(Suit.CLOVER.getSuit(), CardRank.RANKQ.getNum(), CardRank.RANKQ.getRank()),
	CLOVER13(Suit.CLOVER.getSuit(), CardRank.RANKK.getNum(), CardRank.RANKK.getRank()),
	DAIYA01(Suit.DAIYA.getSuit(), CardRank.RANKA.getNum(), CardRank.RANKA.getRank()),
	DAIYA02(Suit.DAIYA.getSuit(), CardRank.RANK2.getNum(), CardRank.RANK2.getRank()),
	DAIYA03(Suit.DAIYA.getSuit(), CardRank.RANK3.getNum(), CardRank.RANK3.getRank()),
	DAIYA04(Suit.DAIYA.getSuit(), CardRank.RANK4.getNum(), CardRank.RANK4.getRank()),
	DAIYA05(Suit.DAIYA.getSuit(), CardRank.RANK5.getNum(), CardRank.RANK5.getRank()),
	DAIYA06(Suit.DAIYA.getSuit(), CardRank.RANK6.getNum(), CardRank.RANK6.getRank()),
	DAIYA07(Suit.DAIYA.getSuit(), CardRank.RANK7.getNum(), CardRank.RANK7.getRank()),
	DAIYA08(Suit.DAIYA.getSuit(), CardRank.RANK8.getNum(), CardRank.RANK8.getRank()),
	DAIYA09(Suit.DAIYA.getSuit(), CardRank.RANK9.getNum(), CardRank.RANK9.getRank()),
	DAIYA10(Suit.DAIYA.getSuit(), CardRank.RANK10.getNum(), CardRank.RANK10.getRank()),
	DAIYA11(Suit.DAIYA.getSuit(), CardRank.RANKJ.getNum(), CardRank.RANKJ.getRank()),
	DAIYA12(Suit.DAIYA.getSuit(), CardRank.RANKQ.getNum(), CardRank.RANKQ.getRank()),
	DAIYA13(Suit.DAIYA.getSuit(), CardRank.RANKK.getNum(), CardRank.RANKK.getRank());

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
	private Card(String suit, int num, String rank) {
		this.suit = suit;
		this.num = num;
		this.rank = rank;
	}

	/**
	 * スートを返却
	 *
	 * @return
	 */
	public String getSuit() {
		return suit;
	}

	/**
	 * 数字を返却
	 *
	 * @return
	 */
	public int getNum() {
		return num;
	}

	/**
	 * ランクを返却
	 *
	 * @return
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * 得点を返却
	 *
	 * @return
	 */
	public int getPoint() {

		if (num >= 11) {
			return 10;
		}
		return num;
	}
}
