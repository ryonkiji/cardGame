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
	private List<BlackjackPlayer> playerList = new ArrayList<>();

	public List<BlackjackPlayer> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(BlackjackPlayer player) {
		this.playerList.add(player);
	}
}
