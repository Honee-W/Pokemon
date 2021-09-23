package edu.nwpu.pokemon.bgm;
/**
 * 此类用于播放主背景音乐
 * @author Old peach
 *
 */

public class MainBgm extends Thread{
	private BGM mainBgm =new BGM();
/**
 * 启动该线程	
 */
	public void run() {
		mainBgm.mainBgm();
	}
	/**
	 * 停止播放
	 */
	public void stopBgm() {
		mainBgm.getP_main().close();
	}
	
}
