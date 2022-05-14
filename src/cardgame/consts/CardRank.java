package cardgame.consts;

/**
 * ランクに関するEnumクラス
 *
 * @author kijima
 *
 */
public enum CardRank {

	RANKA(1, "A"),
	RANK2(2, "2"),
	RANK3(3, "3"),
	RANK4(4, "4"),
	RANK5(5, "5"),
	RANK6(6, "6"),
	RANK7(7, "7"),
	RANK8(8, "8"),
	RANK9(9, "9"),
	RANK10(10, "10"),
	RANKJ(11, "J"),
	RANKQ(12, "Q"),
	RANKK(13, "K");

	private final int num;
	private final String rank;

	/**
	 * コンストラクタ
	 *
	 * @param suit
	 * @return
	 */
	private CardRank(int num, String rank) {
		this.num = num;
		this.rank = rank;
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
	 * 数字を返却
	 *
	 * @return
	 */
	public int getNum() {
		return num;
	}
}
