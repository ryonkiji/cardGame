package cardgame.dealer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cardgame.Consts;
import cardgame.card.Card;
import cardgame.deck.Deck;
import cardgame.exception.SystemErrorException;
import cardgame.player.BlackjackPlayer;
import cardgame.player.BlackjackPlayerList;
import cardgame.util.BlackJackCalcUtil;
import cardgame.util.BlackJackInputUtil;

public class Dealer {

	/**
	 * コンストラクタ
	 *
	 * @param deck
	 * @param playerList
	 */
	public Dealer(Deck deck, BlackjackPlayerList playerList) {
		this.deck = deck;
		this.playerList = playerList;
	}

	/**
	 * 山札クラス
	 */
	private Deck deck;

	/**
	 * PlayerList
	 */
	private BlackjackPlayerList playerList;

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

	public BlackjackPlayerList getPlayerList() {
		return playerList;
	}

	public void setPlayerList(BlackjackPlayerList playerList) {
		this.playerList = playerList;
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
	 * 山札シャッフル
	 *
	 * @param deck
	 */
	public void shuffle() {
		Collections.shuffle(deck.getDeck());
	}

	/**
	 * カード分配
	 *
	 * @param deck
	 *
	 * @param playerList
	 */
	public void distribute() {

		// ディーラーに手札を配る
		for (int j = 0; j < Consts.BLACK_JACK_HAND; j++) {
			setHand(pick());
		}

		// プレイヤーに手札を配る
		for (BlackjackPlayer player : playerList.getPlayerList()) {
			for (int j = 0; j < Consts.BLACK_JACK_HAND; j++) {
				player.setHand(pick());
			}
		}
	}

	/**
	 * デッキの先頭からカードを一枚取り出す
	 *
	 * @param deck
	 */
	public Card pick() {

		// デッキの先頭からカードを一枚取り出す
		Card card = deck.getDeck().get(0);

		// 山札からカードを一枚削除する
		deck.getDeck().remove(0);

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
	 * 
	 * @throws SystemErrorException
	 */
	public void action() throws SystemErrorException {

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
	 */
	public void judge() {

		// ディーラーの得点
		int dealerPoint = getScore();

		// ディーラーがバーストしていない場合
		if (dealerPoint <= 21) {

			for (BlackjackPlayer player : playerList.getPlayerList()) {

				// 得点が21を超える場合バースト
				if (player.getScore() > 21) {
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
				if (player.getScore() <= 21) {
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
