package cardgame.player;

import cardgame.consts.BlackjackSetting;
import cardgame.deck.Deck;
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
	 * コンストラクタ
	 *
	 * @param name
	 */
	public BlackjackPlayerComputer(String name) {
		super(name);
	}

	/**
	 * 賭け金を決める
	 *
	 * @param player
	 * @throws SystemErrorException
	 */
	@Override
	public void bet() {

		// 賭け金の入力を受け付ける
		setBet(BlackjackSetting.CPU_BET);

		// チップに、ベットを引いた値をセット
		setChip(getChip() - getBet());
	}

	/**
	 * Hit or Standを選択
	 *
	 * @param player
	 * @throws SystemErrorException
	 */
	@Override
	public void choice(Deck deck) throws SystemErrorException {

		// cpuの得点計算
		int result = getScore();

		boolean isContinue = false;

		if (result < BlackjackSetting.HIT_LINE) {
			isContinue = true;
		}

		while (isContinue) {

			// カードを受け取る
			receiveCard(deck.pick());

			// 引いたカードの確認
			checkPickCard();

			// 得点の確認
			calc();

			if (getScore() >= BlackjackSetting.HIT_LINE) {
				isContinue = false;
			}
		}

		// Enterを押して次へ進む
		BlackJackInputUtil.getInputEnter();
	}
}
