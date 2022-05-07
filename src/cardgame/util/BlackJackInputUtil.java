package cardgame.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cardgame.exception.SystemErrorException;

/**
 * ブラックジャックの計算/判定ロジックのUtilクラス
 *
 * @author kijima
 *
 */
public class BlackJackInputUtil {

	/**
	 * 入力受付オブジェクト
	 */
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * J, Q, Kが含まれていないかを判定
	 *
	 * @param rank
	 * @return
	 */
	public static boolean isJQK(String rank) {

		if ("J".equals(rank) || "Q".equals(rank) || "K".equals(rank)) {
			return true;
		}
		return false;
	}

	/**
	 * Enterを求めるメソッド
	 *
	 * @return
	 * @throws SystemErrorException
	 */
	public static void getInputEnter() throws SystemErrorException {

		try {
			// 入力受付
			System.out.println("Enterを入力して次へ進んで下さい。");
			String input = br.readLine();
		} catch (IOException e) {
			throw new SystemErrorException();
		}
	}

	/**
	 * 入力値を返却
	 *
	 * @return
	 * @throws SystemErrorException
	 */
	public static String getInputParam() throws SystemErrorException {

		// 入力
		String input = null;

		// 判定
		boolean isInputOk = false;

		// 正しい入力を受け付けるまで繰り返し入力を求める
		while (!isInputOk) {

			try {
				// 入力受付
				System.out.println("Hit or Stand? [h/s]");
				input = br.readLine();
				// 論理チェック
				if (BlackJackInputUtil.isCorrectInput(input)) {
					isInputOk = true;
				} else {
					System.out.println("Hitならばhを、Standならばsを入力して下さい。");
				}
			} catch (IOException e) {
				throw new SystemErrorException();
			}
		}

		return input;
	}

	/**
	 * 入力の妥当性を判定
	 *
	 * @param input
	 * @return
	 */
	public static boolean isCorrectInput(String input) {

		if ("h".equals(input) || "s".equals(input) || "cheat".equals(input)) {
			return true;
		}

		return false;
	}

	/**
	 * 入力値を返却
	 *
	 * @return
	 * @throws SystemErrorException
	 */
	public static int getInputBet() throws SystemErrorException {

		// 入力
		String input = "";

		// 判定
		boolean isInputOk = false;

		// 正しい入力を受け付けるまで繰り返し入力を求める
		while (!isInputOk) {

			try {
				// 入力受付
				System.out.println("賭け金を入力して下さい。");
				input = br.readLine();

				// 型チェック
				if (BlackJackInputUtil.isCorrectInputBet(input)) {
					System.out.println("賭け金には、数字を入力して下さい。");
					continue;
				}

				// 論理チェック
				if (0 < Integer.valueOf(input) && Integer.valueOf(input) <= 50) {
					isInputOk = true;
				} else {
					System.out.println("賭け金は、1〜50で入力して下さい。");
				}
			} catch (IOException e) {
				throw new SystemErrorException();
			}
		}
		return Integer.valueOf(input);
	}

	/**
	 * 入力の妥当性を判定
	 *
	 * @param input
	 * @return
	 */
	public static boolean isCorrectInputBet(String input) {

		String pattern = "[0-9]{2}";

		if (input.matches(pattern)) {
			return false;
		}

		return true;
	}

	/**
	 * 入力値を返却
	 *
	 * @return
	 * @throws SystemErrorException
	 */
	public static String getInputContinue() throws SystemErrorException {

		// 入力
		String input = null;

		// 判定
		boolean isInputOk = false;

		// 正しい入力を受け付けるまで繰り返し入力を求める
		while (!isInputOk) {

			try {
				// 入力受付
				System.out.println("yes or no? [y/n]");
				input = br.readLine();
				// 論理チェック
				if (BlackJackInputUtil.isCorrectInputContinue(input)) {
					isInputOk = true;
				} else {
					System.out.println("もう一度遊ぶ場合はyを、やめる場合はnを入力して下さい。");
				}
			} catch (IOException e) {
				throw new SystemErrorException();
			}
		}

		return input;
	}

	/**
	 * 入力の妥当性を判定
	 *
	 * @param input
	 * @return
	 */
	public static boolean isCorrectInputContinue(String input) {

		if ("y".equals(input) || "n".equals(input)) {
			return true;
		}

		return false;
	}
}
