package cardgame.util;

import java.util.List;

import cardgame.consts.Card;
import cardgame.consts.CardRank;
import cardgame.deck.Deck;

/**
 * ブラックジャックの計算ロジックのUtilクラス
 *
 * @author kijima
 *
 */
public class BlackJackCalcUtil {

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

		return (int) hand.stream().filter(card -> card.getRank() == CardRank.RANKA.getRank()).count();
	}

	/**
	 * Aを除いた手札の得点を計算
	 *
	 * @param hand
	 * @return
	 */
	public static int calcExcludeAce(List<Card> hand) {

		return hand.stream().filter(card -> card.getRank() != CardRank.RANKA.getRank()).mapToInt(card -> card.getPoint()).sum();
	}

	/**
	 * Aを含めた手札の得点を計算
	 *
	 * @param result
	 * @param aceNum
	 * @return
	 */
	public static int calcIncludeAce(int score, int aceNum) {

		int minScore = score + aceNum;
		int maxScore = minScore + (aceNum == 0 ? 0 : 10);
		return maxScore <= 21 ? maxScore : minScore;
	}

	/**
	 * バースト確率を返却
	 *
	 * @param score
	 * @param deck
	 * @return
	 */
	public static double calcProbability(Deck deck, int score) {

		// バーストまでの得点
		int pickableCount = deck.countMoreThan(21 - score);
		// バースト確率返却
		return Double.valueOf(pickableCount) / Double.valueOf(deck.size()) * 100;
	}
}
