package cardgame.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import cardgame.card.Card;

/**
 * ブラックジャックの計算ロジックのUtilクラス
 *
 * @author kijima
 *
 */
public class BlackJackCalcUtil {

	/**
	 * 入力受付オブジェクト
	 */
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * J, Q, Kの計算
	 *
	 * @param num
	 * @return
	 */
	public static int calcPoint(int num) {

		int val = num;

		// J, Q, Kの場合
		if (num > 10) {
			val = 10;
		}

		return val;
	}

	/**
	 * プレイヤーの得点を計算
	 *
	 * @param player
	 */
	public static int calcScore(List<Card> hand) {

		// Aの枚数をカウント
		int aceNum = countAce(hand);

		// Aを除いた手札の計算
		int result = calcExcludeAce(hand);

		// Aを含めた手札の計算
		result = calcIncludeAce(result, aceNum);

		return result;
	}

	/**
	 * 手札のAの枚数を計算
	 *
	 * @param hand
	 * @return
	 */
	public static int countAce(List<Card> hand) {

		int aceNum = 0;

		for (Card card : hand) {

			if ("A".equals(card.getRank())) {
				aceNum++;
			}
		}

		return aceNum;
	}

	/**
	 * Aを除いた手札の得点を計算
	 *
	 * @param hand
	 * @return
	 */
	public static int calcExcludeAce(List<Card> hand) {

		int result = 0;

		for (Card card : hand) {
			if (!"A".equals(card.getRank())) {
				int val = BlackJackCalcUtil.calcPoint(card.getNum());
				result += val;
			}
		}
		return result;
	}

	/**
	 * Aを含めた手札の得点を計算
	 *
	 * @param result
	 * @param aceNum
	 * @return
	 */
	public static int calcIncludeAce(int result, int aceNum) {

		int result1 = 0;
		int result2 = 0;

		// Aが一枚以上ある場合
		if (aceNum > 0) {
			// Aが一枚ある場合
			if (aceNum == 1) {

				// Aを1で計算
				result1 += 1;

				// Aを11で計算
				result2 += 11;
			}
			// Aが二枚ある場合
			else if (aceNum == 2) {
				// Aを(1, 1)で計算
				result1 += 2;

				// Aを(1, 11)で計算
				result2 += 12;
			}
			// Aが三枚ある場合
			else if (aceNum == 3) {

				// Aを(1, 1, 1)で計算
				result1 += 3;

				// Aを(1, 1, 11)で計算
				result2 += 13;
			}
			// Aが四枚ある場合
			else if (aceNum == 4) {

				// Aを(1, 1, 1, 1)で計算
				result1 += 4;

				// Aを(1, 1, 1, 11)で計算
				result2 += 14;
			}

			result1 += result;
			result2 += result;

			// result1が21以下の場合、result1を返却
			if (result1 <= 21) {
				result = result1;
			}

			// result2が21以下かつresult1よりも大きい値の場合、result2を返却
			if (result2 <= 21 && result2 > result1) {
				result = result2;
			}
		}

		return result;
	}

	/**
	 * バースト確率を返却
	 *
	 * @param score
	 * @param deck
	 * @return
	 */
	public static double calcProbability(List<Card> deck, int score) {

		// バーストまでの得点
		int forgiveNum = 21 - score;

		// 引いても大丈夫な数の枚数
		int butstCard = 0;
		for (Card card : deck) {
			if (card.getNum() > forgiveNum) {
				butstCard++;
			}
		}

		// 確率計算
		double probability = Double.valueOf(butstCard) / Double.valueOf(deck.size()) * 100;

		return probability;
	}
}
