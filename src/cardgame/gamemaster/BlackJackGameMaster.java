package cardgame.gamemaster;

import cardgame.Consts;
import cardgame.card.Card;
import cardgame.dealer.Dealer;
import cardgame.deck.Deck;
import cardgame.exception.SystemErrorException;
import cardgame.player.BlackJackPlayer;
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
	 * Dealer
	 */
	public Dealer dealer;

	/**
	 * BlackJackPlayer
	 */
	public BlackJackPlayer player;

	/**
	 * ラウンド数
	 */
	public int round = 1;

	/**
	 * 初期処理
	 */
	public void init() {

		// ブラックジャック開始のメッセージを出力
		System.out.println("<< Welcome to Blackjack !! >>");

		// ルール作成
		createRule();

		// 山札作成
		createDeck();

		// ディーラー作成
		createDealer();

		// プレイヤー作成
		createPlayer();
	}

	/**
	 * ゲーム開始準備
	 */
	public void prepare() {

		// ディーラーに山札をセット
		dealer.setDeck(deck.getDeck());

		// ディーラーにプレイヤー情報をセット
		dealer.setPlayer(player);

		// プレイヤーにディーラーをセット
		player.setDealer(dealer);
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
	 * ルール作成
	 */
	public void createRule() {

	}

	/**
	 * プレイヤー作成
	 */
	public void createPlayer() {

		// プレイヤークラスインスタンス化
		player = new BlackJackPlayer();
		createMyChar();
		createCPU();
	}

	/**
	 * プレイヤー作成
	 */
	private void createMyChar() {

		BlackJackPlayer myChar = new BlackJackPlayer();
		myChar.setName("YOU");
		player.setPlayerList(myChar);
	}

	/**
	 * CPU作成
	 */
	private void createCPU() {

		for (int i = 1; i <= 3; i++) {
			BlackJackPlayer cpu = new BlackJackPlayer();
			cpu.setName("COM" + String.valueOf(i));
			cpu.setCPU(true);
			player.setPlayerList(cpu);
		}
	}

	/**
	 * ディーラー作成
	 */
	public void createDealer() {
		// ディーラークラスインスタンス化
		dealer = new Dealer();
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
		System.out.println("<< 第 " + round + " ラウンド !! >>");

		// 山札をシャッフル
		dealer.shuffle();

		// カードを配る
		dealer.distribute();

		// ディーラーの手札を表示
		dealer.openCardFirstTime();

		// プレイヤーアクション
		for (BlackJackPlayer p : player.getPlayerList()) {
			System.out.println("<<" + p.getName() + "のターン ! >>");

			// プレイヤーにディーラーをセット
			p.setDealer(dealer);

			// 手札を表示
			p.open(p);

			// 得点を計算
			p.calc(p);

			// 賭け金を決める
			p.bet(p);

			// アクションの選択
			p.choice(p);

			System.out.print(Consts.CRLF);
		}

		System.out.println("<<Dealerのターン ! >>");

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

		for (BlackJackPlayer p : player.getPlayerList()) {
			System.out.println("[" + p.getName() + "]：" + p.getWinLoseCode() + " チップ総額：" + p.getChip());
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
				for (BlackJackPlayer p : player.getPlayerList()) {
					p.getHand().clear();
				}

				// ディーラーの手札を初期化
				dealer.getHand().clear();

				start();
			}
		}
	}
}
