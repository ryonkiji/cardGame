package cardgame.player;

import java.util.List;

import cardgame.card.Card;
import cardgame.deck.Deck;
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
	 * コンストラクタ
	 *
	 * @param name
	 */
	public BlackjackPlayerUser(String name) {
		super(name);
	}

	/**
	 * 賭け金を決める
	 *
	 * @param player
	 * @throws SystemErrorException
	 */
	@Override
	public void bet() throws SystemErrorException {

		// 賭け金の入力を受け付ける
		setBet(BlackJackInputUtil.getInputBet());

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

		// ヒットかステイか入力を受け付ける
		String input = BlackJackInputUtil.getInputParam();

		boolean isContinue = isContinue(input, getScore(), deck.getDeck());

		while (isContinue) {

			// カードを引く
			setHand(deck.pick());

			// 引いたカードの確認
			checkPickCard();

			// 得点の確認
			calc();

			if (getScore() <= 21) {
				// // カードを引くかどうかの判定
				input = BlackJackInputUtil.getInputParam();
			} else {
				input = "s";
			}

			// 継続か判定
			isContinue = isContinue(input, getScore(), deck.getDeck());
		}
	}

	/**
	 *
	 * カードを引くか引かないかを判定
	 *
	 * @param input
	 * @param score
	 * @param deck
	 * @return
	 * @throws SystemErrorException
	 */
	public boolean isContinue(String input, int score, List<Card> deck) throws SystemErrorException {

		boolean isContinue = false;

		// Hitの場合、isContinueをtrueに変更
		if ("h".equals(input)) {
			isContinue = true;
		}
		// 確率計算（隠しコマンド）
		else if ("cheat".equals(input)) {

			double probability = BlackJackCalcUtil.calcProbability(deck, score);
			System.out.println("バースト確率：" + probability + "%");

			input = BlackJackInputUtil.getInputParam();
			return isContinue(input, score, deck);
		}

		return isContinue;
	}
}
