package cardgame.player;

import java.util.ArrayList;
import java.util.List;

import cardgame.consts.Card;
import cardgame.consts.Consts;
import cardgame.deck.Deck;
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
	 * 名前
	 */
	private final String name;

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

	public String getName() {
		return name;
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
	 * コンストラクタ
	 *
	 * @param name
	 */
	public BlackjackPlayer(String name) {
		this.name = name;
	}

	/**
	 * プレイヤーの動作
	 *
	 * @param deck
	 * @throws SystemErrorException
	 */
	public void action(Deck deck) throws SystemErrorException {

		// 手札を表示
		open();

		// 得点を計算
		calc();

		// 賭け金を決める
		bet();

		// アクションの選択
		choice(deck);
	}

	/**
	 * 手札を公開
	 *
	 * @param player
	 */
	public void open() {

		System.out.print("[" + getName() + "] ⇒ 手札：");

		for (Card card : hand) {
			System.out.print(card.getSuit() + card.getRank() + " ");
		}

		System.out.print(Consts.CRLF);
	}

	/**
	 * 得点を計算
	 *
	 * @param player
	 */
	public void calc() {

		// 手札の得点を計算
		int result = BlackJackCalcUtil.calcScore(hand);

		// 得点をセット
		setScore(result);

		System.out.println("[" + getName() + "] ⇒ 得点：" + getScore());
	}

	/**
	 * 賭け金を決める
	 *
	 * @param player
	 * @throws SystemErrorException
	 */
	public abstract void bet() throws SystemErrorException;

	/**
	 * Hit or Standを選択
	 *
	 * @param deck
	 *
	 * @param player
	 */
	public abstract void choice(Deck deck) throws SystemErrorException;

	/**
	 * カードを受け取る
	 *
	 * @param card
	 */
	public void receiveCard(Card card) {
		hand.add(card);
	}

	/**
	 * 引いたカードの確認
	 *
	 * @param player
	 */
	public void checkPickCard() {

		// 手札の一番最後の数を表示
		System.out.println("[" + getName() + "] ⇒ 引いたカード：[" + hand.get(size() - 1).getSuit() + hand.get(size() - 1).getRank() + "]");
	}

	/**
	 * 手札の枚数を取得
	 *
	 * @return
	 */
	public int size() {
		return hand.size();
	}

	/**
	 * 賭け金の受け取り
	 *
	 * @param rate
	 */
	public void receiveRefund(int rate) {
		chip += bet * rate;
	}

	/**
	 * 手札を山札に戻す
	 *
	 * @param deck
	 */
	public void returnCard(Deck deck) {

		// 手札を山札に返却
		hand.stream().forEach(card -> deck.receiveCard(card));
		// 手札をクリア
		hand.clear();
	}
}
