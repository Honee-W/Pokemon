package edu.nwpu.pokemon.ui;

import edu.nwpu.pokemon.bgm.BattleBgm;
import edu.nwpu.pokemon.npc.Monster;
import edu.nwpu.pokemon.data.MonsterData;
import edu.nwpu.pokemon.data.ImageData;
import edu.nwpu.pokemon.System;
import edu.nwpu.pokemon.npc.Player;

import static edu.nwpu.pokemon.System.GAME_PIX_72;
import static edu.nwpu.pokemon.System.currentFloor;
import static edu.nwpu.pokemon.data.Map.LvMap;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * 绘制战斗面板
 * @author 老毛桃
 * @version v1.0.0
 */
public class BattleUI {
    // 战斗页面文字的预设格式 Font
    private static final Font BATTLE_FONT = new Font("Serif", 0, 35);

    public static JLayeredPane battleLPane = new JLayeredPane();       // 战斗信息面板

    public static final int OFFSET  = -50;      //画面偏移量


    //战斗信息面板要素
    private BattleBgm battleBgm=new BattleBgm();
    private JLabel battleBgLabel;
    private JLabel monsterImg;                      // 怪物图片
    private JLabel monsterHp = new JLabel();       // 怪物生命值
    private JLabel monsterAttack = new JLabel();   // 怪物攻击力
    private JLabel monsterDefend = new JLabel();   // 怪物防御力
    private JLabel playerHp = new JLabel();        // 玩家生命值
    private JLabel playerAttack = new JLabel();    // 玩家攻击力
    private JLabel playerDefend = new JLabel();    // 玩家防御力

    //在战斗中会变化，但只是暂时的，对于总体怪物数值不变
    //应该该临时变量
    private Monster monster;        //怪物
    private int hp;                 //怪物生命值
    private int attack;             //怪物攻击力
    private int defend;             //怪物防御力

    /**
     * 生成战斗UI
     * @param id 怪物标号
     * @param x 怪物横坐标位置
     * @param y 怪物纵坐标位置
     */
    public BattleUI(int id, int x, int y) {

        battleBgm=new BattleBgm();
        battleBgm.start();
        //获取怪物的各项数值
        monster = MonsterData.monsterMap.get(id);
        hp = monster.getHp();
        attack = monster.getAttack();
        defend = monster.getDefend();

        //获取相对应怪物的图片
        battleBgLabel = new JLabel(new ImageIcon(ImageData.battleBgImg));
        monsterImg = new JLabel(new ImageIcon(System.imgSource.get(id)));

        // 初始化 战斗信息面板
        // 流式布局
        battleLPane.setLayout(null);
        battleLPane.setBounds(27, GAME_PIX_72 * 2, 1242, 541);
        battleBgLabel.setBounds(0, 0, 1242, 541);
        battleLPane.add(battleBgLabel, 1, 0);
        battleLPane.setOpaque(true);
        //暂时不显示，不推流到GPU 还有要素没有添加
        battleLPane.setVisible(false);

        //怪物生命值模块
        monsterHp.setBounds(400, 37 + OFFSET, 300, 300);
        monsterHp.setFont(BATTLE_FONT);
        monsterHp.setForeground(Color.WHITE);

        //怪物攻击力模块
        monsterAttack.setBounds(400, 157 + OFFSET, 300, 300);
        monsterAttack.setFont(BATTLE_FONT);
        monsterAttack.setForeground(Color.WHITE);

        //怪物防御力模块
        monsterDefend.setBounds(400, 291 + OFFSET, 300, 300);
        monsterDefend.setFont(BATTLE_FONT);
        monsterDefend.setForeground(Color.WHITE);

        //玩家生命值模块
        playerHp.setBounds(785, 37 + OFFSET, 300, 300);
        playerHp.setFont(BATTLE_FONT);
        playerHp.setForeground(Color.WHITE);

        //玩家攻击力模块
        playerAttack.setBounds(785, 157 + OFFSET, 300, 300);
        playerAttack.setFont(BATTLE_FONT);
        playerAttack.setForeground(Color.WHITE);

        //玩家防御力模块
        playerDefend.setBounds(785, 291 + OFFSET, 300, 300);
        playerDefend.setFont(BATTLE_FONT);
        playerDefend.setForeground(Color.WHITE);

        //添加要显示的元素
        battleLPane.add(monsterHp, 2, 0);
        battleLPane.add(monsterAttack, 3, 0);
        battleLPane.add(monsterDefend, 4, 0);
        battleLPane.add(playerHp, 5, 0);
        battleLPane.add(playerAttack, 6, 0);
        battleLPane.add(playerDefend, 7, 0);
        //显示怪物贴图
        monsterImg.setBounds(100, 120, 72, 72);
        //添加怪物贴图进界面
        battleLPane.add(monsterImg, 8, 0);
        //显示怪物生命值，攻击力，防御力等值
        monsterHp.setText(hp + "");
        monsterAttack.setText(attack + "");
        monsterDefend.setText(defend + "");
        //显示玩家生命值
        playerHp.setText(System.playerBean_1.getHp() + "");
        playerAttack.setText(System.playerBean_1.getAttack() + "");
        playerDefend.setText(System.playerBean_1.getDefend() + "");
        //可以显示，推流去GPU渲染
        battleLPane.setVisible(true);
        //允许键盘交互
        System.inConversation = true;

        //设置刷新数据定时器，将战斗结果实时输出
        Timer bFrame = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                attack();                                               //执行一轮攻击
                monsterHp.setText(hp + "");                             //更新怪物血量
                playerHp.setText(System.playerBean_1.getHp() + "");     //更新人物血量
                System.gameFrame.repaint();                             //画面更新重绘

                //添加键盘监听，enter键跳过攻击过程，直接显示结果。
                System.gameFrame.addKeyListener(new KeyListener() {
                    int count = 0;//计数器：按回车的次数、

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == e.VK_ENTER && count < 1) {

                            int result;//用来存储击杀怪物后剩余的人物hp
                            if (hp % (System.playerBean_1.getAttack() - defend) != 0) {
                                result = System.playerBean_1.getHp() - ((hp / (System.playerBean_1.getAttack() - defend))) * (attack - System.playerBean_1.getDefend());
                            } else {
                                result = System.playerBean_1.getHp() - ((hp / (System.playerBean_1.getAttack() - defend) - 1)) * (attack - System.playerBean_1.getDefend());
                            }

                            count++;
                            System.playerBean_1.setHp(result);
                            battleLPane.setVisible(false);
                            System.inConversation = false;
                            //更新获取的经验值
                            System.playerBean_1.setExp(System.playerBean_1.getExp() + monster.getExp());//exp += monsterBean.getExp();
                            //更新获取的经验值
                            System.playerBean_1.setMoney(System.playerBean_1.getMoney() + monster.getMoney());// += monsterBean.getMoney();
                            //弹出提示框，显示战斗结算
                            MessageUI.displayMessage("获得金币数 " + monster.getExp() + " 经验值 " + monster.getMoney() + " ！");
                            //销毁战斗界面
                            battleLPane.remove(monsterImg);
                            battleLPane.remove(monsterHp);
                            battleLPane.remove(monsterAttack);
                            battleLPane.remove(monsterDefend);
                            battleLPane.remove(playerHp);
                            battleLPane.remove(playerAttack);
                            battleLPane.remove(playerDefend);
                            battleBgm.stopBgm();//关闭战斗界面音乐
                            battleBgm.interrupt();//安全关闭线程
                            //删除当前位置的怪物
                            LvMap[currentFloor][y][x] = 0;
                            //人物向前移动
                            System.playerBean_1.move(x, y);
                            ((Timer) ex.getSource()).stop();
                        }
                    }

                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });

                if (hp <= 0) {
                    //战斗结束 关闭界面
                    battleLPane.setVisible(false);
                    //关闭键盘交互
                    System.inConversation = false;

                    //更新获取的经验值
                    System.playerBean_1.setExp(System.playerBean_1.getExp() + monster.getExp());
                    //更新获取的金币数
                    System.playerBean_1.setMoney(System.playerBean_1.getMoney() + monster.getMoney());
                    //弹出提示框，显示战斗结算
                    MessageUI.displayMessage("获得金币数 " + monster.getExp() + " 经验值 " + monster.getMoney() + " ！");
                    //销毁战斗界面
                    battleLPane.remove(monsterImg);
                    battleLPane.remove(monsterHp);
                    battleLPane.remove(monsterAttack);
                    battleLPane.remove(monsterDefend);
                    battleLPane.remove(playerHp);
                    battleLPane.remove(playerAttack);
                    battleLPane.remove(playerDefend);
                    battleBgm.stopBgm();//关闭战斗界面音乐
                    battleBgm.interrupt();//安全关闭线程
                    //删除当前位置的怪物
                    LvMap[currentFloor][y][x] = 0;
                    //人物向前移动
                    System.playerBean_1.move(x, y);
                    ((Timer) ex.getSource()).stop();
                }
                if (System.playerBean_1.getExp() >= Player.getLevelUpExp()) {
                    LevelUpUI.upGrade();
                }
            }
        });
        bFrame.start();
    }

    //执行一轮攻击动作
    private void attack() {
        //计算对怪物造成的伤害 HP = PLAYER_ATTACK - MONSTER_DEFEND
        if (System.playerBean_1.getAttack() > defend) {
            hp = hp - System.playerBean_1.getAttack() + defend;
        }
        if (hp <= 0) return;

        //计算怪物对玩家造成的伤害，同理
        if (attack > System.playerBean_1.getDefend()) {
            System.playerBean_1.setHp(System.playerBean_1.getHp() - attack + System.playerBean_1.getDefend());
        }
        if (System.playerBean_1.getAttack() < defend && attack < System.playerBean_1.getDefend()) return;
    }

}
