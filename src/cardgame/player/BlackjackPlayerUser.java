package cardgame.player;

import cardgame.exception.SystemErrorException;
import cardgame.util.BlackJackCalcUtil;
import cardgame.util.BlackJackInputUtil;

/**
 * BlackjackPlayerの実装クラス
 *
 * @author kijima
 *
 */
public class BlackjackPlayerUser extends BlackjackPlayer {

	/**
	 * 賭け金を決める
	 *
	 * @param player
	 * @throws SystemErrorException
	 */
	@Override
	public void bet(BlackjackPlayer player) throws SystemErrorException {

		// 賭け金の入力を受け付ける
		player.setBet(BlackJackInputUtil.getInputBet());

		// チップに、ベットを引いた値をセット
		player.setChip(player.getChip() - player.getBet());
	}

	/**
	 * Hit or Standを選択
	 *
	 * @param player
	 * @throws SystemErrorException
	 */
	@Override
	public void choice(BlackjackPlayer player) throws SystemErrorException {

		// ヒットかステイか入力を受け付ける
		String input = BlackJackInputUtil.getInputParam();

		boolean isContinue = isContinue(input, player);

		while (isContinue) {

			// カードを引く
			player.setHand(getDealer().pick());

			// 引いたカードの確認
			checkPickCard(player);

			// 得点の確認
			calc(player);

			if (player.getScore() <= 21) {
				// // カードを引くかどうかの判定
				input = BlackJackInputUtil.getInputParam();
			} else {
				input = "s";
			}

			// 継続か判定
			isContinue = isContinue(input, player);
		}
	}

	/**
	 *
	 * カードを引くか引かないかを判定
	 *
	 * @param input
	 * @param player
	 * @return
	 * @throws SystemErrorException
	 */
	public boolean isContinue(String input, BlackjackPlayer player) throws SystemErrorException {

		boolean isContinue = false;

		// Hitの場合、isContinueをtrueに変更
		if ("h".equals(input)) {
			isContinue = true;
		}
		// 確率計算（隠しコマンド）
		else if ("cheat".equals(input)) {

			double probability = BlackJackCalcUtil.calcProbability(getDealer().getDeck().getDeck(), player.getScore());
			System.out.println("バースト確率：" + probability + "%");

			input = BlackJackInputUtil.getInputParam();
			return isContinue(input, player);
		}

		return isContinue;
	}
}
