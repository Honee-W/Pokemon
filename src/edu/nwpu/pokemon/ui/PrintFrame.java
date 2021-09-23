package edu.nwpu.pokemon.ui;

import edu.nwpu.pokemon.System;
import edu.nwpu.pokemon.npc.Prop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static edu.nwpu.pokemon.System.*;
import static edu.nwpu.pokemon.System.inConversation;
import static edu.nwpu.pokemon.ui.BagUI.bagLPane;
import static edu.nwpu.pokemon.ui.BagUI.displayObjects;
import static edu.nwpu.pokemon.ui.EscUI.displayEscape;
import static edu.nwpu.pokemon.ui.ForecastUI.displayForecast;
import static edu.nwpu.pokemon.ui.ForecastUI.forecastLPane;
import static edu.nwpu.pokemon.ui.JumpUI.displayJump;
import static edu.nwpu.pokemon.System.gamePanel;
import static edu.nwpu.pokemon.ui.JumpUI.jumpLPane;
import static edu.nwpu.pokemon.ui.BattleUI.battleLPane;
import static edu.nwpu.pokemon.ui.MessageUI.messageLPane;
import static edu.nwpu.pokemon.ui.EscUI.escapeLPane;




import static java.awt.event.KeyEvent.*;


public class PrintFrame {

    public static void printFrame() {
        //绘制图像界面
        gamePanel = new System();
        gamePanel.setPreferredSize(new Dimension(GAME_PIX_72 * 18, GAME_PIX_72 * 13));

        //加载预测
        gamePanel.add(forecastLPane);
        //加载跳转楼层
        gamePanel.add(jumpLPane);
        //加载战斗界面
        gamePanel.add(battleLPane);
        //加载消息几面
        gamePanel.add(messageLPane);
        //加载背包界面
        gamePanel.add(bagLPane);
        //加载退出界面
        gamePanel.add(escapeLPane);
        //加载定时器
        gamePanel.add(timeLabel);

        gameFrame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!System.inConversation) {
                    switch (e.getKeyCode()) {

                        case VK_DOWN:   // 键盘 ↓
                            if (playerBean_1.getPosY() + 1 < 11 && playerBean_1.getPosY() + 1 >= 0) {
                                playerBean_1.setToward(1);
                                interaction(playerBean_1.getPosX(), playerBean_1.getPosY() + 1);
                                System.gameFrame.repaint();
                            }
                            break;
                        case VK_RIGHT:  // 键盘 →
                            if (playerBean_1.getPosX() + 1 < 11 && playerBean_1.getPosX() + 1 >= 0) {
                                playerBean_1.setToward(2);
                                interaction(playerBean_1.getPosX() + 1, playerBean_1.getPosY());
                                System.gameFrame.repaint();
                            }
                            break;
                        case VK_UP:     // 键盘 ↑
                            if (playerBean_1.getPosY() - 1 < 11 && playerBean_1.getPosY() - 1 >= 0) {
                                playerBean_1.setToward(3);
                                interaction(playerBean_1.getPosX(), playerBean_1.getPosY() - 1);
                                System.gameFrame.repaint();
                            }
                            break;
                        case VK_LEFT:   // 键盘 ←
                            if (playerBean_1.getPosX() - 1 < 11 && playerBean_1.getPosX() - 1 >= 0) {
                                playerBean_1.setToward(0);
                                interaction(playerBean_1.getPosX() - 1, playerBean_1.getPosY());
                                gameFrame.repaint();
                            }
                            break;
                        case VK_J:      // 键盘 J
                            if (Prop.isHasJump) {
                                displayJump();
                            }
                            break;
                        case VK_B:      //键盘 B
                            displayObjects();
                            break;
                        case VK_L:      // 键盘 L
                            if (Prop.isHasForecast) {
                                displayForecast();
                            }
                            break;
                        case VK_ESCAPE:
                            displayEscape();
                            break;
                    }
                } else if (e.getKeyCode() == e.VK_L)//bug
                {
                    inConversation = false;
                    forecastLPane.removeAll();
                    forecastLPane.setVisible(false);
                } else if (e.getKeyCode() == e.VK_B) {
                    inConversation = false;
                    bagLPane.removeAll();
                    bagLPane.setVisible(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        //设置游戏界面
        gameFrame.setContentPane(gamePanel);
        //大小可调
        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
