package cardgame.consts;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ランクに関するEnumクラス
 *
 * @author kijima
 *
 */
public class Rank {

	/**
	 * 記号と数字をマッピング
	 */
	public static final Map<Integer, String> numRankMap = new LinkedHashMap<Integer, String>() {
		{
			put(1, "A");
			put(2, "2");
			put(3, "3");
			put(4, "4");
			put(5, "5");
			put(6, "6");
			put(7, "7");
			put(8, "8");
			put(9, "9");
			put(10, "10");
			put(11, "J");
			put(12, "Q");
			put(13, "K");
		}
	};
}
