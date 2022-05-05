package cardgame.player;

import java.util.ArrayList;
import java.util.List;

import cardgame.card.Card;

/**
 * ゲームをする参加者を扱う抽象クラス
 *
 * @author kijima
 *
 */
public abstract class Player {

	/**
	 * 名前
	 */
	private String name = "";

	/**
	 * 持ち札
	 */
	private List<Card> hand = new ArrayList<>();

	/**
	 * CPUかどうか
	 */
	private boolean isCPU = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(Card card) {
		this.hand.add(card);
	}

	public boolean isCPU() {
		return isCPU;
	}

	public void setCPU(boolean isCPU) {
		this.isCPU = isCPU;
	}
}
