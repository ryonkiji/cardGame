package cardgame.player;

import java.util.ArrayList;
import java.util.List;

import cardgame.Consts;
import cardgame.card.Card;
import cardgame.dealer.Dealer;
import cardgame.exception.SystemErrorException;
import cardgame.util.BlackJackCalcUtil;
import cardgame.util.BlackJackInputUtil;

public class BlackJackPlayer extends Player {

	/**
	 * Dealer
	 */
	private Dealer dealer;

	/**
	 * プレイヤーリスト
	 */
	private List<BlackJackPlayer> playerList = new ArrayList<>();

	/**
	 * 得点
	 */
	private int score = 0;

	/**
	 * チップ
	 */
	private int chip = 100;

	/**
	 * 賭け金
	 */
	private int bet = 0;

	/**
	 * 勝敗コード
	 */
	private String winLoseCode = "";

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public List<BlackJackPlayer> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(BlackJackPlayer playerList) {
		this.playerList.add(playerList);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getChip() {
		return chip;
	}

	public void setChip(int chip) {
		this.chip = chip;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public String getWinLoseCode() {
		return winLoseCode;
	}

	public void setWinLoseCode(String winLoseCode) {
		this.winLoseCode = winLoseCode;
	}

	/**
	 * 手札を表示
	 *
	 * @param name
	 * @param list
	 *
	 * @param i
	 */
	public void open(BlackJackPlayer player) {
		System.out.print("[" + player.getName() + "] ⇒ 手札：");

		for (Card card : player.getHand()) {
			System.out.print(card.getSuit() + card.getRank() + " ");
		}

		System.out.print(Consts.CRLF);
	}

	/**
	 * プレイヤーの得点を計算
	 *
	 * @param player
	 */
	public void calc(BlackJackPlayer player) {

		// 手札の得点を計算
		int result = BlackJackCalcUtil.calcScore(player.getHand());

		// 得点をセット
		player.setScore(result);

		System.out.println("[" + player.getName() + "] ⇒ 得点：" + player.getScore());
	}

	/**
	 * 賭け金を決める
	 *
	 * @param player
	 * @throws SystemErrorException
	 */
	public void bet(BlackJackPlayer player) throws SystemErrorException {

		// CPUの場合
		if (player.isCPU()) {
			// 賭け金の入力を受け付ける
			player.setBet(Consts.CPU_BET);

			// チップに、ベットを引いた値をセット
			player.setChip(player.getChip() - player.getBet());
		}
		// CPUではない場合
		else {
			// 賭け金の入力を受け付ける
			player.setBet(BlackJackInputUtil.getInputBet());

			// チップに、ベットを引いた値をセット
			player.setChip(player.getChip() - player.getBet());
		}
	}

	/**
	 * Hit or Standを選択
	 *
	 * @param player
	 * @throws SystemErrorException
	 */
	public void choice(BlackJackPlayer player) throws SystemErrorException {

		// CPUの場合
		if (player.isCPU()) {

			// cpuの得点計算
			int result = player.getScore();

			boolean isContinue = false;

			if (result < 17) {
				isContinue = true;
			}

			while (isContinue) {

				// カードを引く
				player.setHand(dealer.pick());

				// 引いたカードの確認
				checkPickCard(player);

				// 得点の確認
				calc(player);

				if (player.getScore() >= 17) {
					isContinue = false;
				}
			}
		}
		// CPUではない場合
		else {
			// ヒットかステイか入力を受け付ける
			String input = BlackJackInputUtil.getInputParam();

			boolean isContinue = isContinue(input, player);

			while (isContinue) {

				// カードを引く
				player.setHand(dealer.pick());

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
	public boolean isContinue(String input, BlackJackPlayer player) throws SystemErrorException {

		boolean isContinue = false;

		// Hitの場合、isContinueをtrueに変更
		if ("h".equals(input)) {
			isContinue = true;
		}
		// 確率計算（隠しコマンド）
		else if ("cheat".equals(input)) {

			double probability = BlackJackCalcUtil.calcProbability(dealer.getDeck(), player.getScore());
			System.out.println("バースト確率：" + probability + "%");

			input = BlackJackInputUtil.getInputParam();
			return isContinue(input, player);
		}

		return isContinue;
	}

	/**
	 * 引いたカードの確認
	 *
	 * @param player
	 */
	private void checkPickCard(BlackJackPlayer player) {

		// 手札の一番最後の数を表示
		System.out.println("[" + player.getName() + "] ⇒ 引いたカード：[" + player.getHand().get(player.getHand().size() - 1).getSuit() + player.getHand().get(player.getHand().size() - 1).getRank() + "]");
	}
}
