package edu.nwpu.pokemon.npc;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import edu.nwpu.pokemon.data.ImageData;
import static edu.nwpu.pokemon.data.Map.initPos;
import static edu.nwpu.pokemon.System.currentFloor;

/**
 * The type Player.
 *
 * @author 老毛桃
 * @version v1.0.0
 */
public class Player {


    private int level;      // 等级
    private int hp;         // 生命值
    private int attack;     // 攻击力
    private int defend;     // 防御力
    private int money;      // 金钱
    private int exp;        // 经验
    private int Ykey;       // 黄色钥匙数
    private int Bkey;       // 蓝色钥匙数
    private int Rkey;       // 红色钥匙数

    private int toward;     // 当前朝向 0-左 1-下 2-右 3-上
    private int posX;       // X 坐标值
    private int posY;       // Y 坐标值

    private static int levelUpExp = 50; // 升级所需

    private HashMap<Integer,BufferedImage> playerImgSource = ImageData.playerMap0;

    public HashMap<Integer, BufferedImage> getPlayerImgSource() {
        return playerImgSource;
    }

    public void setPlayerImgSource(HashMap<Integer, BufferedImage> playerImgSource) {
        this.playerImgSource = playerImgSource;
    }

    /**
     * Instantiates a new Player.
     */
    public Player() {
        this.level = 1;     // 初始等级      1
        this.hp = 1000;       // 初始生命值  1000
        this.attack = 1000000;     // 初始攻击力    10
        this.defend = 1000000;      // 初始防御力    10
        this.money = 0;     // 初始金钱      0
        this.exp = 0;       // 初始经验值    0
        this.Ykey = 10;      // 初始黄钥匙数   0
        this.Bkey = 10;      // 初始蓝钥匙数   0
        this.Rkey = 10;      // 初始红钥匙数   0

        this.toward = 1;    // 初始朝向
        this.posX = initPos[currentFloor][0];      // 初始 X坐标
        this.posY = initPos[currentFloor][1];;      // 初始 Y坐标
    }

    /**
     * 玩家移动
     * 改变坐标值
     *
     * @param cx the cx
     * @param cy the cy
     */
    public void move(int cx, int cy) {
        posX = cx;
        posY = cy;
    }

    /**
     * 绘制玩家图像
     *
     * @return the buffered image
     */
    public BufferedImage draw() {
        if (toward == 0)
            return playerImgSource.get(-1);
        if (toward == 1)
            return playerImgSource.get(-2);
        if (toward == 2)
            return playerImgSource.get(-3);
        if (toward == 3)
            return playerImgSource.get(-4);
        return null;
    }


    public static void setLevelUpExp(int levelUpExp) {
        Player.levelUpExp = levelUpExp;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * 获取等级
     *
     * @return the level
     */

    public int getLevel() {
        if(exp>=levelUpExp) {//经验值满足升级要求时，为人物升级
            this.level++;
            if(level<=5) {//等级到达5级后，升级所需的经验值增大
                levelUpExp += 50;
            }
            else {
                levelUpExp += 80;
            }
        }
        return level;
    }

    /**
     * 设置等级
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 获取HP
     *
     * @return the hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * 设置HP .
     *
     * @param hp the hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * 获取攻击力 .
     *
     * @return  attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * 设置攻击力.
     *
     * @param attack the attack
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * 获取防御力 .
     *
     * @return the defend
     */
    public int getDefend() {
        return defend;
    }

    /**
     * 设置防御力.
     *
     * @param defend the defend
     */
    public void setDefend(int defend) {
        this.defend = defend;
    }

    /**
     * 获取金币数.
     *
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * 设置金币数.
     *
     * @param money the money
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * 获取经验值.
     *
     * @return the exp
     */
    public int getExp() {
        return exp;
    }

    /**
     * 设置经验值.
     *
     * @param exp the exp
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * 获取等级提升经验 level up exp.
     *
     * @return the level up exp
     */
    public static int getLevelUpExp() {
        return levelUpExp;
    }

    /**
     * 获取黄钥匙 ykey.
     *
     * @return the ykey
     */
    public int getYkey() {
        return Ykey;
    }

    /**
     * 设置黄钥匙 ykey.
     *
     * @param ykey the ykey
     */
    public void setYkey(int ykey) {
        Ykey = ykey;
    }

    /**
     * 获取蓝钥匙 bkey.
     *
     * @return the bkey
     */
    public int getBkey() {
        return Bkey;
    }

    /**
     * 设置蓝钥匙 bkey.
     *
     * @param bkey the bkey
     */
    public void setBkey(int bkey) {
        Bkey = bkey;
    }

    /**
     * 获取红钥匙 rkey.
     *
     * @return the rkey
     */
    public int getRkey() {
        return Rkey;
    }

    /**
     * 设置红钥匙 rkey.
     *
     * @param rkey the rkey
     */
    public void setRkey(int rkey) {
        Rkey = rkey;
    }

    /**
     * 获取人物朝向 toward.
     *
     * @return the toward
     */
    public int getToward() {
        return toward;
    }

    /**
     * 设置人物朝向 toward.
     *
     * @param toward the toward
     */
    public void setToward(int toward) {
        this.toward = toward;
    }

    /**
     * 获取人物位置横坐标 pos x.
     *
     * @return the pos x
     */
    public int getPosX() {
        return posX;
    }

    /**
     * 获取人物位置竖坐标 pos y.
     *
     * @return the pos y
     */

    public int getPosY() {
        return posY;
    }

    @Override
    public String toString() {
        return   level + "_" +
                 hp + "_" +
                 attack + "_" +
                 defend + "_" +
                 money + "_" +
                 exp + "_" +
                 Ykey + "_" +
                 Bkey + "_" +
                 Rkey + "_" +
                 toward + "_" +
                 posX + "_" +
                 posY + "_" ;
    }
}
