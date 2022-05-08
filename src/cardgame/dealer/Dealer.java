package cardgame.dealer;

import java.util.ArrayList;
import java.util.List;

import cardgame.card.Card;
import cardgame.consts.BlackjackSetting;
import cardgame.consts.Consts;
import cardgame.deck.Deck;
import cardgame.exception.SystemErrorException;
import cardgame.player.BlackjackPlayer;
import cardgame.player.BlackjackPlayerList;
import cardgame.util.BlackJackCalcUtil;
import cardgame.util.BlackJackInputUtil;

public class Dealer {

	/**
	 * 山札クラス
	 */
	private Deck deck;

	/**
	 * 手札
	 */
	private List<Card> hand = new ArrayList<>();

	/**
	 * 得点
	 */
	private int score = 0;

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(Card card) {
		this.hand.add(card);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * コンストラクタ
	 *
	 * @param deck
	 * @param playerList
	 */
	public Dealer(Deck deck) {
		this.deck = deck;
	}

	/**
	 * 山札シャッフル
	 *
	 * @param deck
	 */
	public void shuffle() {

		deck.shuffle();
	}

	/**
	 * カード分配
	 *
	 * @param playerList
	 *
	 * @param deck
	 *
	 * @param playerList
	 */
	public void distribute(BlackjackPlayerList playerList) {

		// ディーラーに手札を配る
		for (int j = 0; j < BlackjackSetting.INITIAL_HAND; j++) {
			setHand(deck.pick());
		}

		// プレイヤーに手札を配る
		for (BlackjackPlayer player : playerList.getPlayerList()) {
			for (int j = 0; j < BlackjackSetting.INITIAL_HAND; j++) {
				player.setHand(deck.pick());
			}
		}
	}

	/**
	 * 初回手札を表示
	 */
	public void openCardFirstTime() {

		System.out.println("[Dealer] ⇒ [" + getHand().get(0).getSuit() + getHand().get(0).getRank() + "]");
		System.out.println("[Dealer] ⇒ [?]");
		System.out.print(Consts.CRLF);
	}

	/**
	 * ディーラーの動作
	 *
	 * @throws SystemErrorException
	 */
	public void action() throws SystemErrorException {

		// 手札の確認
		open();

		// 点数計算
		calc();

		boolean isContinue = false;

		if (getScore() < BlackjackSetting.HIT_LINE) {
			isContinue = true;
		}

		while (isContinue) {

			// カードを引く
			setHand(deck.pick());

			// 引いたカードの確認
			checkPickCard();

			// 得点計算
			calc();

			if (getScore() >= BlackjackSetting.HIT_LINE) {
				isContinue = false;
			}
		}

		// Enterを押して次へ進む
		BlackJackInputUtil.getInputEnter();

		System.out.print(Consts.CRLF);
	}

	/**
	 * 手札を表示
	 */
	public void open() {

		System.out.print("[Dealer] ⇒ 手札：");

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

		System.out.println("[Dealer] ⇒ 得点：" + getScore());
	}

	/**
	 * 引いたカードの確認
	 */
	private void checkPickCard() {

		// 手札の一番最後の数を表示
		System.out.println("[Dealer] ⇒ 引いたカード：" + getHand().get(getHand().size() - 1).getSuit() + getHand().get(getHand().size() - 1).getRank());
	}

	/**
	 * 勝敗判定
	 *
	 * @param playerList
	 */
	public void judge(BlackjackPlayerList playerList) {

		// ディーラーの得点
		int dealerPoint = getScore();

		// ディーラーがバーストしていない場合
		if (dealerPoint <= BlackjackSetting.BURST_LINE) {

			for (BlackjackPlayer player : playerList.getPlayerList()) {

				// 得点が21を超える場合バースト
				if (player.getScore() > BlackjackSetting.BURST_LINE) {
					player.setWinLoseCode(Consts.LOSE_CODE);
				} else {
					// 勝利
					if (dealerPoint < player.getScore()) {
						player.setWinLoseCode(Consts.WIN_CODE);
						player.setChip(player.getChip() + player.getBet() * 2);
					}
					// 敗北
					else if (dealerPoint > player.getScore()) {
						player.setWinLoseCode(Consts.LOSE_CODE);
					}
					// 引き分け
					else {
						player.setWinLoseCode(Consts.DROW_CODE);
						player.setChip(player.getChip() + player.getBet());
					}
				}
			}
		}
		// ディーラーがバーストしている場合
		else {
			for (BlackjackPlayer player : playerList.getPlayerList()) {

				// 21を超えていなければ勝ち
				if (player.getScore() <= BlackjackSetting.BURST_LINE) {
					player.setWinLoseCode(Consts.WIN_CODE);
					player.setChip(player.getChip() + player.getBet() * 2);
				}
				// 21を超えていれば負け
				else {
					player.setWinLoseCode(Consts.LOSE_CODE);
				}
			}
		}
	}
}
