package cardgame.dealer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cardgame.Consts;
import cardgame.card.Card;
import cardgame.player.BlackJackPlayer;
import cardgame.util.BlackJackCalcUtil;

public class Dealer {

	/**
	 * BlackJackPlayer
	 */
	private BlackJackPlayer player;

	/**
	 * 山札
	 */
	private List<Card> deck = new ArrayList<>();

	/**
	 * 手札
	 */
	private List<Card> hand = new ArrayList<>();

	/**
	 * 得点
	 */
	private int score = 0;

	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}

	public void setDeck(Card card) {
		this.deck.add(card);
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

	public BlackJackPlayer getPlayer() {
		return player;
	}

	public void setPlayer(BlackJackPlayer player) {
		this.player = player;
	}

	/**
	 * 山札シャッフル
	 */
	public void shuffle() {
		Collections.shuffle(deck);
	}

	/**
	 * カード分配
	 */
	public void distribute() {

		// ディーラーに手札を配る
		for (int j = 0; j < Consts.BLACK_JACK_HAND; j++) {
			setHand(pick());
		}

		// プレイヤーに手札を配る
		for (BlackJackPlayer p : player.getPlayerList()) {
			for (int j = 0; j < Consts.BLACK_JACK_HAND; j++) {
				p.setHand(pick());
			}
		}
	}

	/**
	 * デッキの先頭からカードを一枚取り出す
	 */
	public Card pick() {

		// デッキの先頭からカードを一枚取り出す
		Card card = getDeck().get(0);

		// 山札からカードを一枚削除する
		getDeck().remove(0);

		return card;
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
	 */
	public void action() {

		// 手札の確認
		open();

		// 点数計算
		calc();

		boolean isContinue = false;

		if (getScore() < 17) {
			isContinue = true;
		}

		while (isContinue) {

			// カードを引く
			setHand(pick());

			// 引いたカードの確認
			checkPickCard();

			// 得点計算
			calc();

			if (getScore() >= 17) {
				isContinue = false;
			}
		}

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
	 */
	public void judge() {

		// ディーラーの得点
		int dealerPoint = getScore();

		// ディーラーがバーストしていない場合
		if (dealerPoint <= 21) {

			for (BlackJackPlayer p : player.getPlayerList()) {

				// 得点が21を超える場合バースト
				if (p.getScore() > 21) {
					p.setWinLoseCode(Consts.LOSE_CODE);
				} else {
					// 勝利
					if (dealerPoint < p.getScore()) {
						p.setWinLoseCode(Consts.WIN_CODE);
						p.setChip(p.getChip() + p.getBet() * 2);
					}
					// 敗北
					else if (dealerPoint > p.getScore()) {
						p.setWinLoseCode(Consts.LOSE_CODE);
					}
					// 引き分け
					else {
						p.setWinLoseCode(Consts.DROW_CODE);
						p.setChip(p.getChip() + p.getBet());
					}
				}
			}
		}
		// ディーラーがバーストしている場合
		else {
			for (BlackJackPlayer p : player.getPlayerList()) {

				// 21を超えていなければ勝ち
				if (p.getScore() <= 21) {
					p.setWinLoseCode(Consts.WIN_CODE);
					p.setChip(p.getChip() + p.getBet() * 2);
				}
				// 21を超えていれば負け
				else {
					p.setWinLoseCode(Consts.LOSE_CODE);
				}
			}
		}
	}
}
