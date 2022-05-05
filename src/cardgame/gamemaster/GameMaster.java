package cardgame.gamemaster;

import cardgame.deck.Deck;
import cardgame.exception.SystemErrorException;

/**
 * GameMasterの抽象クラス
 *
 * ※各ゲームのゲームマスタークラスはこのクラスを継承すること
 *
 * @author kijima
 *
 */
public abstract class GameMaster {

	/**
	 * 山札クラス
	 */
	public Deck deck;

	/**
	 *
	 * 全ゲームの共通処理
	 *
	 * @param args
	 * @throws SystemErrorException
	 * @throws Exception
	 */
	public void execute(String[] args) {

		try {
			// パラメータチェック
			// cheackParam(args);

			// 初期処理
			init();

			// ゲーム準備
			prepare();

			// ゲーム開始
			start();

		} catch (SystemErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * パラメータチェック
	 *
	 * @param args
	 * @throws SystemErrorException
	 */
	// abstract void cheackParam(String[] args) throws SystemErrorException;

	/**
	 * 初期処理
	 *
	 * @return
	 */
	abstract void init();

	/**
	 * ゲーム準備
	 */
	/**
	 * ゲーム準備
	 */
	abstract void prepare();

	/**
	 * ルール作成
	 */
	abstract void createRule();

	/**
	 * 山札作成
	 */
	abstract void createDeck();

	/**
	 * プレーヤー作成
	 */
	abstract void createPlayer();

	/**
	 * ゲーム開始
	 */
	abstract void start() throws SystemErrorException;
}
