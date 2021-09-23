/**
 * 
 */
package edu.nwpu.pokemon.bgm;

/**
 * @author Old Peach
 *
 */
public class StartBgm extends Thread {

	/**
	 * 
	 */
	private BGM bgm = new BGM();// 创建播放音乐的对象

	/**
	 * 启动该线程播放音乐
	 */
	public void run() {
		bgm.startBgm();
	}

	/**
	 * 停止播放音乐
	 */
	public void stopBgm() {
		bgm.getP_start().close();
	}

}
