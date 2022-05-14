package cardgame.dealer;

import java.util.ArrayList;
import java.util.List;

import cardgame.consts.BlackjackSetting;
import cardgame.consts.Card;
import cardgame.consts.Consts;
import cardgame.deck.Deck;
import cardgame.exception.SystemErrorException;
import cardgame.player.BlackjackPlayer;
import cardgame.player.BlackjackPlayerList;
import cardgame.util.BlackJackCalcUtil;
import cardgame.util.BlackJackInputUtil;

/**
 * ディーラークラス
 *
 * @author kijima
 *
 */
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

	/**
	 * 得点
	 */
	private boolean isBurst;

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

		// 手札を追加
		for (int j = 0; j < BlackjackSetting.INITIAL_HAND; j++) {
			hand.add(deck.pick());
		}

		// プレイヤーは手札を受け取る
		for (int i = 0; i < playerList.count(); i++) {
			BlackjackPlayer player = playerList.get(i);
			for (int j = 0; j < BlackjackSetting.INITIAL_HAND; j++) {
				// 手札を受け取る
				player.receiveCard(deck.pick());
			}
		}
	}

	/**
	 * 初回手札を表示
	 */
	public void openCardFirstTime() {

		System.out.println("[Dealer] ⇒ [" + hand.get(0).getSuit() + hand.get(0).getRank() + "]");
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

		if (score < BlackjackSetting.HIT_LINE) {
			isContinue = true;
		}

		while (isContinue) {

			// カードを引く
			hand.add(deck.pick());

			// 引いたカードの確認
			checkPickCard();

			// 得点計算
			calc();

			if (score >= BlackjackSetting.HIT_LINE) {
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
		score = BlackJackCalcUtil.calcScore(hand);

		// バーストチェック
		System.out.println("[Dealer] ⇒ 得点：" + score);
	}

	/**
	 * 引いたカードの確認
	 */
	private void checkPickCard() {

		// 手札の一番最後の数を表示
		System.out.println("[Dealer] ⇒ 引いたカード：" + hand.get(size() - 1).getSuit() + hand.get(size() - 1).getRank());
	}

	/**
	 * 勝敗判定
	 *
	 * @param playerList
	 */
	public void judge(BlackjackPlayerList playerList) {

		BlackjackPlayer player;

		// ディーラーの得点
		int dealerPoint = score;

		// バーストしているか判定
		isBurst(score);

		// 勝敗を判定
		for (int i = 0; i < playerList.count(); i++) {
			player = playerList.get(i);

			// プレーヤーがバーストしている場合
			if (player.getScore() > BlackjackSetting.BURST_LINE) {
				player.setWinLoseCode(Consts.LOSE_CODE);
				continue;
			}
			// ディーラーがバーストしている場合
			else if (isBurst) {
				player.setWinLoseCode(Consts.WIN_CODE);
				// player.setChip(player.getChip() + player.getBet() * 2);
				continue;
			}
			// ディーラー得点がプレイヤー得点より高い場合
			else if (dealerPoint < player.getScore()) {
				player.setWinLoseCode(Consts.WIN_CODE);
				// player.setChip(player.getChip() + player.getBet() * 2);
				continue;
			}
			// プレイヤー得点がディーラー得点より高い場合
			else if (dealerPoint > player.getScore()) {
				player.setWinLoseCode(Consts.LOSE_CODE);
			}
			// 引き分け
			else if (dealerPoint == player.getScore()) {

				player.setWinLoseCode(Consts.DROW_CODE);
				// player.setChip(player.getChip() + player.getBet());
			}

		}

		// 賭け金を分配
		for (int i = 0; i < playerList.count(); i++) {
			player = playerList.get(i);

			// 勝者は賭け金の2倍のチップを受領
			if (Consts.WIN_CODE.equals(player.getWinLoseCode())) {
				player.receiveRefund(2);
			}
			// 引分者は賭け金の2倍のチップを受領
			else if (Consts.DROW_CODE.equals(player.getWinLoseCode())) {
				player.receiveRefund(1);
			}
		}
	}

	/**
	 * バーストしているかを判定
	 *
	 * @param score
	 */
	public void isBurst(int score) {
		if (score > 21) {
			isBurst = true;
		} else {
			isBurst = false;
		}
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
