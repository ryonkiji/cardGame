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

			// 初期処理
			init();

			// ゲーム開始
			start();

		} catch (SystemErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初期処理
	 *
	 * @return
	 */
	abstract void init();

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
