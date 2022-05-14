package cardgame.gamemaster;

import cardgame.consts.Consts;
import cardgame.dealer.Dealer;
import cardgame.deck.Deck;
import cardgame.exception.SystemErrorException;
import cardgame.player.BlackjackPlayer;
import cardgame.player.BlackjackPlayerList;
import cardgame.util.BlackJackInputUtil;

/**
 * BlackJackゲームマスタークラス
 *
 * @author kijima
 *
 */
public class BlackJackGameMaster extends GameMaster {

	/**
	 * PlayerList
	 */
	public BlackjackPlayerList playerList;

	/**
	 * Dealer
	 */
	public Dealer dealer;

	/**
	 * ラウンド数
	 */
	public int round = 1;

	/**
	 * 初期処理
	 */
	public void init() {

		// ブラックジャック開始のメッセージを出力
		System.out.println("<< Welcome to Blackjack!!>>");

		// 山札作成
		createDeck();

		// プレイヤー作成
		createPlayer();

		// ディーラー作成
		createDealer();
	}

	/**
	 * ブラックジャック用の山札作成
	 */
	public void createDeck() {

		// Deckクラスをインスタンス化
		deck = new Deck();
	}

	/**
	 * プレイヤー作成
	 */
	public void createPlayer() {

		// BlackjackPlayerListクラスインスタンス化
		playerList = new BlackjackPlayerList();
	}

	/**
	 * ディーラー作成
	 */
	public void createDealer() {
		// ディーラークラスインスタンス化
		dealer = new Dealer(deck);
	}

	/**
	 * ゲーム進行
	 *
	 * @throws SystemErrorException
	 *
	 */
	@Override
	public void start() throws SystemErrorException {

		// ブラックジャック開始のメッセージを出力
		System.out.println("<< 第 " + round + " ラウンド!>>");

		// 山札をシャッフル
		dealer.shuffle();

		// カードを配る
		dealer.distribute(playerList);

		// ディーラーの手札を表示
		dealer.openCardFirstTime();

		// プレイヤーアクション
		for (int i = 0; i < playerList.count(); i++) {

			BlackjackPlayer player = playerList.get(i);

			System.out.println("<<" + player.getName() + "のターン!>>");

			// プレイヤーアクション
			player.action(deck);

			System.out.print(Consts.CRLF);
		}

		System.out.println("<<Dealerのターン!>>");

		// ディーラーアクション
		dealer.action();

		// 勝敗判定
		dealer.judge(playerList);

		// 結果出力
		printResult();

		// 続けるか？
		isContinue();
	}

	/**
	 * 結果表示
	 */
	public void printResult() {

		System.out.println("<<結果発表>>");

		for (int i = 0; i < playerList.count(); i++) {
			BlackjackPlayer player = playerList.get(i);
			System.out.println("[" + player.getName() + "]：" + player.getWinLoseCode() + " チップ総額：" + player.getChip());
		}

		System.out.print(Consts.CRLF);
	}

	/**
	 * ゲームを続けるか？
	 *
	 * @throws SystemErrorException
	 */
	public void isContinue() throws SystemErrorException {

		System.out.println("<<もう一度プレイしますか？>>");

		// ゲームを続けるか入力を受け付ける
		String input = BlackJackInputUtil.getInputContinue();

		if ("y".equals(input)) {

			// ラウンドを追加
			round++;

			for (int i = 0; i < playerList.count(); i++) {
				BlackjackPlayer player = playerList.get(i);

				// 手札を山札に戻す
				player.returnCard(deck);
			}

			// 手札を山札に戻す
			dealer.returnCard(deck);

			start();
		} else {
			// ブラックジャック終了のメッセージを出力
			System.out.println("<<Thank you for playing Blackjack!>>");
		}
	}
}
