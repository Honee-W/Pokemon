package edu.nwpu.pokemon.bgm;

import java.io.File;
import java.io.FileInputStream;
import java.util.Random;

import javazoom.jl.player.Player;

/**
 * @author Old peach
 *
 */
public class BGM {

	File f = new File("BGM");// 主音乐所在文件夹
	private String[] SNames;

	private void createplaylist() {// 将文件中的音乐提取出来
		SNames = f.list();
	}

	private Player p_main;// 主背景音乐的播放
	private Player p_start;//开始界面音乐
	private Player p_battle;// 打怪时的背景音乐
	private int temp1 = -1, temp2 = -1, temp3 = -1, temp4 = -1, var;

	/**
	 * 构造函数，提取音乐
	 */
	public BGM() {
		createplaylist();
	}

	/**
	 * @return the p_main 返回主音乐播放对象
	 */
	public Player getP_main() {
		return p_main;
	}

	/**
	 * 返回播放打怪音乐的对象
	 * 
	 * @return the p_battle
	 */
	public Player getP_battle() {
		return p_battle;
	}

	/**
	 * 通过此方法完成打怪界面的音乐播放
	 */
	public void battleBgm() {
		try {
			FileInputStream fis = new FileInputStream(
					"otherbgm/battle.mp3");
			p_battle = new Player(fis);
			p_battle.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回播放开始音乐的对象
	 * @return the p_start
	 */
	public Player getP_start() {
		return p_start;
	}

	/**
	 * 通过此方法完成主游戏界面的音乐播放
	 */
	public void startBgm() {
		try {
			FileInputStream fis = new FileInputStream(
					"otherbgm/start.mp3");
			p_start = new Player(fis);
			p_start.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 通过此方法完成主背景音乐的播放
	 */
	public void mainBgm() {
		try {
			FileInputStream fis = new FileInputStream("BGM/" + randsong());
			p_main = new Player(fis);
			p_main.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得随机音乐播放顺序
	 * 
	 * @return 返回顺序列表
	 */
	private String randsong() {
		Random r = new Random();
		var = r.nextInt(SNames.length);
		while (var == temp1 || var == temp2 || var == temp3 || var == temp4) {
			var = r.nextInt(SNames.length);
		}
		temp4 = temp3;
		temp3 = temp2;
		temp2 = temp1;
		temp1 = var;
		return SNames[var];
	}

	/**
	 * 播放下一曲
	 */
	public void playNext() {
		p_main.close();
	}

}
