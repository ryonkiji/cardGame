package cardgame.player;

import cardgame.Consts;
import cardgame.exception.SystemErrorException;
import cardgame.util.BlackJackInputUtil;

/**
 * BlackjackPlayerの実装クラス
 *
 * @author kijima
 *
 */
public class BlackjackPlayerComputer extends BlackjackPlayer {

	/**
	 * 賭け金を決める
	 *
	 * @param player
	 * @throws SystemErrorException
	 */
	@Override
	public void bet(BlackjackPlayer player) {

		// 賭け金の入力を受け付ける
		player.setBet(Consts.CPU_BET);

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

		// cpuの得点計算
		int result = player.getScore();

		boolean isContinue = false;

		if (result < 17) {
			isContinue = true;
		}

		while (isContinue) {

			// カードを引く
			player.setHand(getDealer().pick());

			// 引いたカードの確認
			checkPickCard(player);

			// 得点の確認
			calc(player);

			if (player.getScore() >= 17) {
				isContinue = false;
			}
		}

		// Enterを押して次へ進む
		BlackJackInputUtil.getInputEnter();
	}

}
