package edu.nwpu.pokemon.ui;

import static edu.nwpu.pokemon.System.*;
import static edu.nwpu.pokemon.data.Map.LvMap;

import edu.nwpu.pokemon.System;
import edu.nwpu.pokemon.npc.Monster;
import edu.nwpu.pokemon.data.MonsterData;


import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * 当存在特殊道具时触发该类
 * 可以观察各怪物属性值
 * @author 老毛桃
 * @version v1.0.0
 */
public class ForecastUI {
    public static JLayeredPane forecastLPane = new JLayeredPane();     // 预测信息面板

    static {
        // 初始化 预测面板
        forecastLPane.setLayout(null);
        forecastLPane.setBounds(6 * GAME_PIX_72, GAME_PIX_72, GAME_PIX_72 * 11, GAME_PIX_72 * 11);
        forecastLPane.setBackground(Color.BLACK);   // 黑色背景
        forecastLPane.setOpaque(true);
        forecastLPane.setVisible(false);
    }

    // 查看怪物功能
    public static void displayForecast() {
        inConversation = true;
        forecastLPane.setVisible(true);
        int cnt = 0;

        HashSet<Integer> forecastSet = new HashSet<>();
        // 遍历当前楼层

        for (int x = 0; x < LvMap[currentFloor].length; x++) {
            for (int y = 0; y < LvMap[currentFloor][x].length; y++) {
                int id = LvMap[currentFloor][x][y];
                // 如果 id 对应 怪物 且 在 forecastSet 中不存在 id 值
                if ((id >= 40 && id <= 70) && !forecastSet.contains(id)) {
                    forecastSet.add(id);
                    Monster m = MonsterData.monsterMap.get(id);

                    //预测属性界面
                    JLabel nameLabel = new JLabel("名称：" + m.getName());
                    JLabel hpLabel = new JLabel("生命：" + m.getHp());
                    JLabel attackLabel = new JLabel("攻击：" + m.getAttack());
                    JLabel defendLabel = new JLabel("防御：" + m.getDefend());
                    JLabel moneyExpLabel = new JLabel("金 · 经：" + m.getMoney() + " · " + m.getExp());
                    JLabel loseLabel = new JLabel("损失：" + forecast(m));

                    //添加名称模块
                    nameLabel.setBounds(115, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    nameLabel.setForeground(Color.WHITE);
                    nameLabel.setFont(new Font("Serif", 0, 25));

                    //添加生命值模块
                    hpLabel.setBounds(115, 24 + 96 * cnt + GAME_PIX_72 / 2, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    hpLabel.setForeground(Color.WHITE);
                    hpLabel.setFont(new Font("Serif", 0, 25));

                    //添加攻击力模块
                    attackLabel.setBounds(339, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    attackLabel.setForeground(Color.WHITE);
                    attackLabel.setFont(new Font("Serif", 0, 25));

                    //添加防御力模块
                    defendLabel.setBounds(339, 24 + 96 * cnt + GAME_PIX_72 / 2, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    defendLabel.setForeground(Color.WHITE);
                    defendLabel.setFont(new Font("Serif", 0, 25));

                    //添加掉落金钱以及经验模块
                    moneyExpLabel.setBounds(563, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    moneyExpLabel.setForeground(Color.WHITE);
                    moneyExpLabel.setFont(new Font("Serif", 0, 25));

                    //添加自身损失模块
                    loseLabel.setBounds(563, 24 + 96 * cnt + GAME_PIX_72 / 2, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
                    loseLabel.setForeground(Color.WHITE);
                    loseLabel.setFont(new Font("Serif", 0, 25));

                    //添加贴图模块
                    JLabel headLabel = new JLabel();
                    headLabel.setIcon(new ImageIcon(System.imgSource.get(id)));
                    headLabel.setBounds(25, 24 + 96 * cnt, GAME_PIX_72, GAME_PIX_72);
                    cnt++;

                    forecastLPane.add(nameLabel);
                    forecastLPane.add(hpLabel);
                    forecastLPane.add(attackLabel);
                    forecastLPane.add(defendLabel);
                    forecastLPane.add(moneyExpLabel);
                    forecastLPane.add(loseLabel);
                    forecastLPane.add(headLabel);
                }
            }
        }
        if(cnt == 0){
            JLabel nullMonster = new JLabel("不存在怪物");
            nullMonster.setBounds(320, 24 + 96 * cnt + GAME_PIX_72 / 2, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
            nullMonster.setForeground(Color.WHITE);
            nullMonster.setFont(new Font("Serif", 0, 25));

            forecastLPane.add(nullMonster);
        }
    }
    // 预测功能
    public static String forecast(Monster e) {
            int hp;

            if(e.getHp()%(System.playerBean_1.getAttack() - e.getDefend())==0) hp=(((e.getHp() / (System.playerBean_1.getAttack() - e.getDefend()))-1) * (e.getAttack() - System.playerBean_1.getDefend()));
            else hp=((e.getHp() / (System.playerBean_1.getAttack() - e.getDefend())) * (e.getAttack() - System.playerBean_1.getDefend()));
            if (System.playerBean_1.getAttack() <= e.getDefend()||System.playerBean_1.getHp()<=hp) {
                return "死亡";
            } else if (System.playerBean_1.getDefend() >= e.getAttack()) {
                return 0 + "";
            } else {
                return hp + "";
            }
    }
}
