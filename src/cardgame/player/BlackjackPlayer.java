package cardgame.player;

import java.util.ArrayList;
import java.util.List;

import cardgame.card.Card;
import cardgame.consts.Consts;
import cardgame.dealer.Dealer;
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
	 * Dealer
	 */
	private Dealer dealer;

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

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
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

		for (Card card : getHand()) {
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
		int result = BlackJackCalcUtil.calcScore(getHand());

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
	 * 引いたカードの確認
	 *
	 * @param player
	 */
	public void checkPickCard() {

		// 手札の一番最後の数を表示
		System.out.println("[" + getName() + "] ⇒ 引いたカード：[" + getHand().get(getHand().size() - 1).getSuit() + getHand().get(getHand().size() - 1).getRank() + "]");
	}
}
