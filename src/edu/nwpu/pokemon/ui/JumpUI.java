package edu.nwpu.pokemon.ui;

import static edu.nwpu.pokemon.data.Map.initPos;
import static edu.nwpu.pokemon.System.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 界面跳转
 * @author 老毛桃
 * @version v1.0.0
 */
public class JumpUI {

    public static JLayeredPane jumpLPane = new JLayeredPane();         // 跳跃信息面板
    static JLabel[] choices = new JLabel[21];                          // 选择楼层

    static {
        // 初始化 跳跃信息面板
        jumpLPane.setLayout(null);
        jumpLPane.setBounds(7 * GAME_PIX_72, 2 * GAME_PIX_72, GAME_PIX_72 * 9, GAME_PIX_72 * 9);
        jumpLPane.setBorder(BorderFactory.createLineBorder(new Color(204, 102, 0), 8, true));
        jumpLPane.setBackground(Color.BLACK);
        jumpLPane.setOpaque(true);

        jumpLPane.setVisible(false);
    }

    //楼层跳转
    public static void displayJump() {
        //添加提示界面
        JLabel text = new JLabel("按U键退出罗盘界面");
        text.setFont(new Font("Serif", 0, 30));
        text.setForeground(Color.WHITE);
        text.setBounds(180, 50, 350, 50);

        jumpLPane.add(text);


        for (int x = 0; x < Math.min(8, maxFloor); x++) {
            //0-7层
            JLabel temp = new JLabel("▷第 " + x + " 层");
            temp.setFont(new Font("Serif", 0, 30));
            temp.setForeground(Color.WHITE);
            temp.setBounds(50, 150 + 50 * x, 200, 50);
            jumpLPane.add(temp);
            choices[x] = temp;

        }
        if (maxFloor >= 8)
            for (int x = 8; x < 16; x++) {
                //8-15层button
                JLabel temp = new JLabel("▷第 " + x + " 层");
                temp.setFont(new Font("Serif", 0, 30));
                temp.setForeground(Color.WHITE);
                temp.setBounds(250, 150 + 50 * (x - 8), 200, 50);
                jumpLPane.add(temp);
                choices[x] = temp;

            }
        if (maxFloor >= 16)
            for (int x = 16; x < 21; x++) {
                //16-20层
                JLabel temp = new JLabel("▷第 " + x + " 层");
                temp.setFont(new Font("Serif", 0, 30));
                temp.setForeground(Color.WHITE);
                temp.setBounds(450, 150 + 50 * (x - 16), 200, 50);
                jumpLPane.add(temp);
                choices[x] = temp;

            }

        jumpLPane.setVisible(true);

        //添加键盘监听
        gameFrame.addKeyListener(new KeyListener() {
            int selection = 0;

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (selection != 20 && e.getKeyCode() == e.VK_W && choices[selection + 1] != null) {
                    choices[selection].setText(choices[selection].getText().replaceAll("▶", "▷"));
                    selection = selection + 1;
                    choices[selection].setText(choices[selection].getText().replaceAll("▷", "▶"));
                    gameFrame.repaint();
                }
                if (selection != 0 && e.getKeyCode() == e.VK_S) {
                    choices[selection].setText(choices[selection].getText().replaceAll("▶", "▷"));
                    selection = selection - 1;
                    choices[selection].setText(choices[selection].getText().replaceAll("▷", "▶"));
                    gameFrame.repaint();
                }
                if (e.getKeyCode() == e.VK_SPACE) {
                    choices[selection].setText(choices[selection].getText().replaceAll("▷", "▶"));
                    playerBean_1.move(initPos[selection][0], initPos[selection][1]);
                    currentFloor = selection;
                    gameFrame.repaint();
                    inConversation = false;
                    jumpLPane.removeAll();
                    jumpLPane.setVisible(false);
                    gameFrame.removeKeyListener(this);
                }
                if(e.getKeyCode() == e.VK_U){
                    inConversation = false;
                    jumpLPane.removeAll();
                    jumpLPane.setVisible(false);
                    gameFrame.removeKeyListener(this);
                    gameFrame.repaint();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
