package cardgame.player;

import java.util.ArrayList;
import java.util.List;

import cardgame.Consts;
import cardgame.card.Card;
import cardgame.dealer.Dealer;
import cardgame.exception.SystemErrorException;
import cardgame.util.BlackJackCalcUtil;

/**
 * BlackjackPlayerの抽象クラス
 *
 * ※全てのBlackjackPlayerはこのクラスを継承すること
 *
 * @author kijima
 *
 */
public abstract class BlackjackPlayer implements Player {

	/**
	 * Dealer
	 */
	private Dealer dealer;

	/**
	 * 名前
	 */
	private String name = "";

	/**
	 * 持ち札
	 */
	private List<Card> hand = new ArrayList<>();

	/**
	 * CPUかどうか
	 */
	private boolean isCPU = false;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(Card card) {
		this.hand.add(card);
	}

	public boolean isCPU() {
		return isCPU;
	}

	public void setCPU(boolean isCPU) {
		this.isCPU = isCPU;
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
	 * プレイヤーの動作
	 *
	 * @param player
	 * @throws SystemErrorException
	 */
	public void action(BlackjackPlayer player) throws SystemErrorException {

		// 手札を表示
		player.open(player);

		// 得点を計算
		player.calc(player);

		// 賭け金を決める
		player.bet(player);

		// アクションの選択
		player.choice(player);
	}

	/**
	 * 手札を公開
	 *
	 * @param player
	 */
	public void open(BlackjackPlayer player) {

		System.out.print("[" + player.getName() + "] ⇒ 手札：");

		for (Card card : player.getHand()) {
			System.out.print(card.getSuit() + card.getRank() + " ");
		}

		System.out.print(Consts.CRLF);
	}

	/**
	 * 得点を計算
	 *
	 * @param player
	 */
	public void calc(BlackjackPlayer player) {

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
	public abstract void bet(BlackjackPlayer player) throws SystemErrorException;

	/**
	 * Hit or Standを選択
	 *
	 * @param player
	 */
	public abstract void choice(BlackjackPlayer player) throws SystemErrorException;

	/**
	 * 引いたカードの確認
	 *
	 * @param player
	 */
	public void checkPickCard(BlackjackPlayer player) {

		// 手札の一番最後の数を表示
		System.out.println("[" + player.getName() + "] ⇒ 引いたカード：[" + player.getHand().get(player.getHand().size() - 1).getSuit() + player.getHand().get(player.getHand().size() - 1).getRank() + "]");
	}
}
