package cardgame.player;

import java.util.ArrayList;
import java.util.List;

/**
 * Playerを格納する実装クラス
 *
 * @author kijima
 *
 */
public class BlackjackPlayerList implements PlayerList {

	/**
	 * プレイヤーリスト
	 */
	private List<BlackjackPlayer> playerList;

	/**
	 * コンストラクタ
	 */
	public BlackjackPlayerList() {

		playerList = new ArrayList<>();
		createUser();
		createComputer();
	}

	/**
	 * ユーザーを作成し、プレイヤーリストに追加
	 */
	private void createUser() {

		BlackjackPlayer player = new BlackjackPlayerUser("YOU");
		playerList.add(player);
	}

	/**
	 * コンピューターを作成し、プレイヤーリストに追加
	 */
	private void createComputer() {

		for (int i = 1; i <= 3; i++) {
			BlackjackPlayer player = new BlackjackPlayerComputer("COM" + String.valueOf(i));
			playerList.add(player);
		}
	}

	/**
	 * プレイヤーの人数を返却
	 *
	 * @return
	 */
	public int count() {

		return playerList.size();
	}

	/**
	 * プレイヤーの人数を返却
	 *
	 * @return
	 */
	public BlackjackPlayer get(int index) {

		BlackjackPlayer player;

		try {
			player = playerList.get(index);
		} catch (IllegalArgumentException e) {
			throw e;
		}

		return player;
	}
}
