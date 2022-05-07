package cardgame.gamemaster;

import cardgame.Consts;
import cardgame.card.Card;
import cardgame.dealer.Dealer;
import cardgame.deck.Deck;
import cardgame.exception.SystemErrorException;
import cardgame.player.BlackjackPlayer;
import cardgame.player.BlackjackPlayerComputer;
import cardgame.player.BlackjackPlayerList;
import cardgame.player.BlackjackPlayerUser;
import cardgame.util.BlackJackInputUtil;

/**
 *
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

		// 山札クラスをインスタンス化
		deck = new Deck();

		// ♠︎を作成
		createSuit(Consts.SPADE);

		// ❤︎を作成
		createSuit(Consts.HEART);

		// ☘を作成
		createSuit(Consts.CLOVER);

		// ♦︎を作成
		createSuit(Consts.DAIYA);
	}

	/**
	 * スートを作成
	 *
	 * @param suit
	 */
	public void createSuit(String suit) {
		for (int i = 1; i <= 13; i++) {
			Card card = new Card();
			card.setNum(i);
			card.setSuit(suit);
			card.setRank(Consts.numRankMap.get(i));
			deck.setDeck(card);
		}
	}

	/**
	 * プレイヤー作成
	 */
	public void createPlayer() {

		// BlackjackPlayerListクラスインスタンス化
		playerList = new BlackjackPlayerList();
		createUser();
		createComputer();
	}

	/**
	 * ユーザーを作成し、プレイヤーリストに追加
	 */
	private void createUser() {

		BlackjackPlayer player = new BlackjackPlayerUser();
		player.setName("YOU");
		playerList.getPlayerList().add(player);
	}

	/**
	 * コンピューターを作成し、プレイヤーリストに追加
	 */
	private void createComputer() {

		for (int i = 1; i <= 3; i++) {
			BlackjackPlayer player = new BlackjackPlayerComputer();
			player.setName("COM" + String.valueOf(i));
			playerList.getPlayerList().add(player);
		}
	}

	/**
	 * ディーラー作成
	 */
	public void createDealer() {
		// ディーラークラスインスタンス化
		dealer = new Dealer(deck, playerList);
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
		dealer.distribute();

		// ディーラーの手札を表示
		dealer.openCardFirstTime();

		// プレイヤーアクション
		for (BlackjackPlayer player : playerList.getPlayerList()) {

			System.out.println("<<" + player.getName() + "のターン!>>");

			// プレイヤーにディーラーをセット
			player.setDealer(dealer);

			// プレイヤーアクション
			player.action(player);

			System.out.print(Consts.CRLF);
		}

		System.out.println("<<Dealerのターン!>>");

		// ディーラーアクション
		dealer.action();

		// 勝敗判定
		dealer.judge();

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

		for (BlackjackPlayer player : playerList.getPlayerList()) {
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

		if (round < 3) {
			System.out.println("<<もう一度プレイしますか？>>");

			// ゲームを続けるか入力を受け付ける
			String input = BlackJackInputUtil.getInputContinue();

			if ("y".equals(input)) {

				// ラウンドを追加
				round++;

				// プレイヤーの手札を初期化
				for (BlackjackPlayer player : playerList.getPlayerList()) {
					player.getHand().clear();
				}

				// ディーラーの手札を初期化
				dealer.getHand().clear();

				start();
			} else {
				// ブラックジャック終了のメッセージを出力
				System.out.println("<<Thank you for playing Blackjack!>>");
			}
		} else {
			// ブラックジャック終了のメッセージを出力
			System.out.println("<<Thank you for playing Blackjack!>>");
		}
	}
}
