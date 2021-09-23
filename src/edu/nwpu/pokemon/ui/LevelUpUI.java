package edu.nwpu.pokemon.ui;

import static edu.nwpu.pokemon.System.*;
import static edu.nwpu.pokemon.ui.DiaLogUI.*;

import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

import edu.nwpu.pokemon.data.ImageData;

/**
 * 人物升级弹窗
 * @author 老毛桃
 * @version v1.0.0
 */
public class LevelUpUI {

    private static String[] choice = new String[3];// 用于储存三段文字
    private static final int PLAYER_DOWN = -2;// 图片所在位置

    /**
     * 当经验值达到升级条件时，会触发升级事件，可增加生命力，攻击力或防御值
     */
    public static void upGrade() {
        imgIco.setIcon(new ImageIcon(ImageData.playerMap1.get(PLAYER_DOWN)));// 加入图片
        choice = new String[]{"▶增加 生命值", "▷增加攻击力", "▷增加 防御值"};
        Insets insets = dialogLPane.getInsets();

        imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX_72, GAME_PIX_72);// 调整图片位置
        text.setBounds(140 + insets.left, 50 + insets.top, 550 - 50, 250);// 调整文字的位置
        text.setText("你已升级，请选择一个奖励: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2]);

        dialogBgImg.setBounds(0, 0, 550, 250);// 以下为弹出框的形成
        dialogLPane.setBounds(550, 230, 550, 250);
        dialogLPane.add(imgIco, 2, 0);
        dialogLPane.add(text, 3, 0);
        gamePanel.add(dialogLPane);
        gamePanel.repaint();
        /**
         * 监听事件，当选择一个奖励时触发对应的事件
         */
        gameFrame.addKeyListener(new KeyListener() {
            int selection = 0;
            String message = "选择一个奖励: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2];

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (selection != 2 && e.getKeyCode() == e.VK_S) {
                    choice[selection] = choice[selection].replaceAll("▶", "▷");
                    selection = selection + 1;
                    choice[selection] = choice[selection].replaceAll("▷", "▶");
                    message = "选择一个奖励:\n " + choice[0] + " \n " + choice[1] + " \n " + choice[2];
                    text.setText(message);
                    gameFrame.repaint();
                }
                if (selection != 0 && e.getKeyCode() == e.VK_W) {
                    choice[selection] = choice[selection].replaceAll("▶", "▷");
                    selection = selection - 1;
                    choice[selection] = choice[selection].replaceAll("▷", "▶");
                    message = "选择一个奖励: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2];
                    text.setText(message);
                    gameFrame.repaint();
                }
                if (e.getKeyCode() == e.VK_SPACE) {// 空格为已选择奖励
                    switch (selection) {
                        case 0:
                            playerBean_1.setHp((int) (playerBean_1.getHp() * 1.2));// 选择增加HP
                            break;
                        case 1:
                            playerBean_1.setAttack((int) (playerBean_1.getAttack() * 1.2));// 选择增加攻击力
                            break;
                        case 2:
                            playerBean_1.setDefend(((int) (playerBean_1.getDefend() * 1.2)));// 选择增加防御值
                            break;
                    }
                    // 事件完成，移除弹框
                    dialogLPane.remove(imgIco);
                    dialogLPane.remove(text);
                    gamePanel.remove(dialogLPane);
                    gameFrame.repaint();
                    inConversation = false;
                    gameFrame.removeKeyListener(this);
                    return;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
