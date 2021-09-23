package edu.nwpu.pokemon.bgm;

/**
 * 此类用于新线程播放打怪界面音乐
 * 
 * @author Old Peach
 *
 */
public class BattleBgm extends Thread {

	private BGM bgm = new BGM();// 创建播放音乐的对象

	/**
	 * 启动该线程播放音乐
	 */
	public void run() {
		bgm.battleBgm();
	}

	/**
	 * 停止播放音乐
	 */
	public void stopBgm() {
		bgm.getP_battle().close();
	}
}
