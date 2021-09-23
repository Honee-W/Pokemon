package edu.nwpu.pokemon;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import edu.nwpu.pokemon.npc.*;
import edu.nwpu.pokemon.data.*;
import edu.nwpu.pokemon.ui.MessageUI;
import edu.nwpu.pokemon.ui.*;

import edu.nwpu.fly.ShootGame;
import edu.nwpu.pushbox.pushbox.Main;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import static edu.nwpu.pokemon.data.Map.*;
import static edu.nwpu.pokemon.ui.LevelUpUI.*;
import static edu.nwpu.fly.ShootGame.SUCCESS;

/**
 * 负责交互的驱动类
 * 进行所有交互动作
 * 游戏总体运行
 * @author 老毛桃
 * @version v1.0.0
 */
public class System extends JPanel{

    public static final int GAME_PIX_72 = 72;

    public static JFrame gameFrame;
    public static JPanel gamePanel;

    // 游戏时间 分 秒
    public static JLabel timeLabel;
    public static int gameMin = 0;          //游戏时间分
    public static double gameSec = 0;       //游戏时间秒

    public static boolean inConversation = false;   // 是否允许键盘交互
    public static boolean talked = false;   //是否有对话
    public static int currentFloor = 19; //0;     // 当前楼层
    public static int maxFloor = 26;         // 最大楼层

    public static HashMap<Integer, BufferedImage> imgSource = ImageData.imagesMap0;     // 用于帧数切换
    public static Player playerBean_1 = new Player();                           // 用于保存玩家属性数值

    public System() {
        setLayout(null);
        gameFrame = new JFrame("精灵宝可梦");

        //注册挂机监听器
        gameFrame.addKeyListener(new HangUpListener());

        // 初始化 时间面板
        timeLabel = new JLabel();
        timeLabel.setBounds(80, 800, 250, 100);
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setFont(new Font("Serif", 0, 25));


        new Timer(500, new ActionListener() {
            boolean change = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                System.gameSec += 0.5;  // 频率为 0.5s
                if (gameSec == 60) {
                    gameSec = 0;
                    gameMin++;
                }
                System.timeLabel.setText(" 游戏时间：" + gameMin + " 分 " + (int) gameSec + " 秒");
                if (change) {
                    change = false;
                    imgSource = ImageData.imagesMap0;           //加载第一帧
                    playerBean_1.setPlayerImgSource(ImageData.playerMap0);

                } else {
                    change = true;
                    imgSource = ImageData.imagesMap1;           //加载第二帧
                    playerBean_1.setPlayerImgSource(ImageData.playerMap1);
                }
                //两帧交替实现动画呈现
                repaint();
            }
        }).start();
    }

        // 重写 paintComponent() 方法。

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            // 绘制游戏主背景
            g2.drawImage(ImageData.gameBgImg, 0, 0, null);

            // 绘制 地图数据
            for (int x = 0; x < 11; x++) {
                for (int y = 0; y < 11; y++) {
                    int id = LvMap[currentFloor][x][y];
                    BufferedImage a = imgSource.get(id);
                    g2.drawImage(a, GAME_PIX_72 * y + GAME_PIX_72 * 6, GAME_PIX_72 * x + GAME_PIX_72, null);
                }
            }

            // 绘制 玩家
            g2.drawImage(playerBean_1.draw(), (playerBean_1.getPosX() + 6) * GAME_PIX_72, (playerBean_1.getPosY() + 1) * GAME_PIX_72, null);
            //代码测试行
            //**************************

            //***************************

            // 绘制 左侧面板的玩家数据
            g2.setFont(new Font("Arial", 0, 30));
            g2.setColor(Color.GRAY);
            g2.drawString(playerBean_1.getLevel() + "", 240, 150);
            g2.drawString(playerBean_1.getHp() + "", 240, 215);
            g2.drawString(playerBean_1.getAttack() + "", 240, 270);
            g2.drawString(playerBean_1.getDefend() + "", 240, 325);
            g2.drawString(playerBean_1.getMoney() + "", 240, 385);
            g2.drawString(playerBean_1.getExp() + "", 240, 440);

            // 绘制 各种钥匙数目
            g2.drawString(playerBean_1.getYkey() + "", 240, 530);
            g2.drawString(playerBean_1.getBkey() + "", 240, 605);
            g2.drawString(playerBean_1.getRkey() + "", 240, 680);

            // 绘制 当前楼层
            g2.drawString(currentFloor + "", 210, 760);
        }

    // 核 心 交 互 函 数
    public static void interaction(int x, int y) {
        int id = LvMap[currentFloor][y][x];
        switch (id) {
            case 0:     // 玩家移动
                playerBean_1.move(x, y);
                break;
            case 1:     // 砖墙
                break;
            case 2:     // 黄门
                if (playerBean_1.getYkey() > 0) {
                    LvMap[currentFloor][y][x] = 0;
                    playerBean_1.setYkey(playerBean_1.getYkey() - 1);
                    playerBean_1.move(x, y);
                }
                break;
            case 3:     // 蓝门
                if (playerBean_1.getBkey() > 0) {
                    LvMap[currentFloor][y][x] = 0;
                    playerBean_1.setBkey(playerBean_1.getBkey() - 1);
                    playerBean_1.move(x, y);
                }
                break;
            case 4:     // 红门
                if (playerBean_1.getRkey() > 0) {
                    LvMap[currentFloor][y][x] = 0;
                    playerBean_1.setRkey(playerBean_1.getRkey() - 1);
                    playerBean_1.move(x, y);
                }
                break;
            case 5:     // 石块
                break;
            case 6:     // [道具] 黄钥匙
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setYkey(System.playerBean_1.getYkey() + 1);
                MessageUI.displayMessage("得到一个 精灵球 ！");
                break;
            case 7:     // [道具] 蓝钥匙
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setBkey(System.playerBean_1.getBkey() + 1);
                MessageUI.displayMessage("得到一个 治愈球 ！");
                break;
            case 8:     // [道具] 红钥匙
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setRkey(System.playerBean_1.getRkey() + 1);
                MessageUI.displayMessage("得到一个 大师球 ！");
                break;
            case 9:     // [道具] 蓝宝石
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setDefend(System.playerBean_1.getDefend() + 3);
                MessageUI.displayMessage("得到一个蓝宝石 防御力加 3 ！");
                break;
            case 10:    // [道具] 红宝石
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setAttack(System.playerBean_1.getAttack() + 3);
                MessageUI.displayMessage("得到一个红宝石 攻击力加 3 ！");
                break;
            case 11:    // [道具] 红药水
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setHp(System.playerBean_1.getHp() + 200);
                MessageUI.displayMessage("得到一个伤药 生命加 200 ！");
                break;
            case 12:    // [道具] 蓝药水
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setHp(System.playerBean_1.getHp() + 500);
                MessageUI.displayMessage("得到一个好伤药 生命加 500 ！");
                break;
            case 13:    // 上楼
                currentFloor++;
                maxFloor = Math.max(maxFloor, currentFloor);
                playerBean_1.move(initPos[currentFloor][0], initPos[currentFloor][1]);
                break;
            case 14:    // 下楼
                currentFloor--;
                playerBean_1.move(finPos[currentFloor][0], finPos[currentFloor][1]);
                break;
            case 15:    // 不可通过的护栏
                break;
            case 19:    // 火海
                break;
            case 20:    // 星空
                break;
            case 22:    // 商店
                if (currentFloor == 3) {
                    ShopUI.shop(0);
                } else if (currentFloor == 11) {
                    ShopUI.shop(3);
                }
                break;
            case 24:    // [对话] 仙子
                new Dialogue(id);
                break;
            case 25:    // [对话] 小偷
                new Dialogue(id);
                break;
            case 26:    // [对话] 老人
                new Dialogue(id);
                break;
            case 27:    // [对话] 商人
                new Dialogue(id);
                break;
            case 28:    // [对话] 公主
                new Dialogue(id);
                break;
            case 30:    // [道具] 小飞羽
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setLevel(System.playerBean_1.getLevel() + 1);
                playerBean_1.setHp(System.playerBean_1.getHp() + 1000);
                playerBean_1.setAttack(System.playerBean_1.getAttack() + 7);
                playerBean_1.setDefend(System.playerBean_1.getDefend() + 7);
                MessageUI.displayMessage("得到 小飞羽 等级提升一级 ！");
                upGrade();
                break;
            case 31:    // [道具] 大飞羽
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                playerBean_1.setLevel(System.playerBean_1.getLevel() + 3);
                playerBean_1.setHp(System.playerBean_1.getHp() + 3000);
                playerBean_1.setAttack(System.playerBean_1.getAttack() + 21);
                playerBean_1.setDefend(System.playerBean_1.getDefend() + 21);
                MessageUI.displayMessage("得到 大飞羽 等级提升三级 ！");
                for (int i = 0; i < 2; i++) {
                    upGrade();
                }
                break;
            case 32:    // [宝物] 幸运十字架
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                Prop.isHasCross = true;
                MessageUI.displayMessage("【幸运十字架】 把它交给序章中的仙子，可以将自身的所有能力提升一些（攻击、防御和生命值）。");
                break;
            case 33:    // [宝物] 圣水瓶
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setHp(System.playerBean_1.getHp() * 2);
                MessageUI.displayMessage("【圣水瓶】 它可以将你的体质增加一倍（生命值加倍）。");
                break;
            case 34:    // [宝物] 圣光徽
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                Prop.isHasForecast = true;
                MessageUI.displayMessage("【圣光徽】 按 L 键使用 查看怪物的基本情况。");
                break;
            case 35:    // [宝物] 风之罗盘
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                Prop.isHasJump = true;
                MessageUI.displayMessage("【风之罗盘】 按 J 键使用 在已经走过的楼层间进行跳跃。");
                break;
            case 36:    // [道具] 钥匙盒
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setYkey(System.playerBean_1.getYkey() + 1);
                System.playerBean_1.setBkey(System.playerBean_1.getBkey() + 1);
                System.playerBean_1.setRkey(System.playerBean_1.getRkey() + 1);
                MessageUI.displayMessage("得到 精灵球包 各种精灵球加 1 ！");
                break;
            case 38:    // [宝物] 星光神榔
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                Prop.isHasHammer = true;
                MessageUI.displayMessage("【星光神榔】 把它交给第四层的小偷，小偷便会用它打开第十八层的隐藏地面（你就可以救出公主了）。");
                break;
            case 39:    // [道具] 金块
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setMoney(System.playerBean_1.getMoney() + 300);
                MessageUI.displayMessage("得到 金块 金币数加 300 ！");
                break;
            case 40:    // [怪物 monster]
            case 41:    // [怪物 monster]
            case 42:    // [怪物 monster]
            case 43:    // [怪物 monster]
            case 44:    // [怪物 monster]
            case 45:    // [怪物 monster]
            case 46:    // [怪物 monster]
            case 47:    // [怪物 monster]
            case 48:    // [怪物 monster]
            case 49:    // [怪物 monster]
            case 50:    // [怪物 monster]
            case 51:    // [怪物 monster]
            case 52:    // [怪物 monster]
            case 53:    // [怪物 monster]
            case 54:    // [怪物 monster]
            case 55:    // [怪物 monster]
            case 56:    // [怪物 monster]
            case 57:    // [怪物 monster]
            case 58:    // [怪物 monster]

            case 60:    // [怪物 monster]
            case 61:    // [怪物 monster]
            case 62:    // [怪物 monster]
            case 63:    // [怪物 monster]
            case 64:    // [怪物 monster]
            case 65:    // [怪物 monster]
            case 66:    // [怪物 monster]
            case 67:    // [怪物 monster]
            case 68:    // [怪物 monster]
            case 69:    // [怪物 monster]
            case 70:    // [怪物 monster]
                if (ForecastUI.forecast(MonsterData.monsterMap.get(id)).equals("死亡")
                        || Integer.parseInt(ForecastUI.forecast(MonsterData.monsterMap.get(id))) >= playerBean_1.getHp()) {
                    return;
                } else {
                    new BattleUI(id, x, y);
                }
                break;
            case 59:
                if (ForecastUI.forecast(MonsterData.monsterMap.get(id)).equals("死亡")
                        || Integer.parseInt(ForecastUI.forecast(MonsterData.monsterMap.get(id))) >= playerBean_1.getHp()) {
                    return;
                } else {
                    new BattleUI(id, x, y);
                }
                new Dialogue(id);
                break;
            case 71:    // [宝物] 铁剑
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setAttack(System.playerBean_1.getAttack() + 10);
                MessageUI.displayMessage("得到 铁剑 攻击加 10 ！");
                break;
            case 73:    // [宝物] 钢剑
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setAttack(System.playerBean_1.getAttack() + 30);
                MessageUI.displayMessage("得到 钢剑 攻击加 30 ！");
                break;
            case 75:    // [宝物] 圣光剑
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setAttack(System.playerBean_1.getAttack() + 120);
                MessageUI.displayMessage("得到 圣光剑 攻击加 120 ！");
                break;
            case 76:    // [宝物]
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setDefend(System.playerBean_1.getDefend() + 10);
                MessageUI.displayMessage("得到 铁盾 防御加 10 ！");
                break;
            case 78:    // [宝物] 钢盾
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setDefend(System.playerBean_1.getDefend() + 30);
                MessageUI.displayMessage("得到 钢盾 防御加 30 ！");
                break;
            case 80:    // [宝物] 星光盾
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                System.playerBean_1.setDefend(System.playerBean_1.getDefend() + 120);
                MessageUI.displayMessage("得到 星光盾 防御加 120 ！");
                break;
            case 115:   // 可通过的护栏
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                break;
            case 119:   // 同 case 1:
            case 129:   // 同 case 1:
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                break;
            case 188:
                if (ForecastUI.forecast(MonsterData.monsterMap.get(id)).equals("???")
                        || Integer.parseInt(ForecastUI.forecast(MonsterData.monsterMap.get(id))) >= playerBean_1.getHp()) {
                    return;
                } else {
                    new BattleUI(id, x, y);
                }
                break;
            case 198:
                ShootGame boss = new ShootGame();
                boss.run();
                break;
            case 202:   // [宝物] 炎之灵杖
                MessageUI.displayMessage("得到 炎之灵杖");
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                break;
            case 203:   // [宝物] 心之灵杖
                MessageUI.displayMessage("得到 心之灵杖");
                LvMap[currentFloor][y][x] = 0;
                playerBean_1.move(x, y);
                break;
            case 301:   // 22层 到 24层
                currentFloor += 2;
                playerBean_1.move(5, 1);
                break;
            case 302:   // 22层 到 25层
                currentFloor += 3;
                playerBean_1.move(1, 5);
                break;
            case 303:   // 24层 到 22层
                currentFloor -= 2;
                playerBean_1.move(5, 9);
                break;
            case 304:   // 25层 到 22层
                currentFloor -= 3;
                playerBean_1.move(9, 5);
                break;
            case 305:   // 24层 到 26层
                currentFloor += 2;
                playerBean_1.move(5, 10);
                break;
        }
    }

    class HangUpListener implements KeyListener{


        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_X){
                Algorithm algorithm =new Algorithm();
                repaint();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
