package edu.nwpu.pokemon.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static edu.nwpu.pokemon.System.*;
import static edu.nwpu.pokemon.data.SavaAndLoad.*;
import static edu.nwpu.pokemon.ui.PrintFrame.printFrame;

/**
 * 在游戏中退出界面
 */
public class EscUI {
    public static JLayeredPane escapeLPane = new JLayeredPane();         // 跳跃信息面板
    static JLabel[] choices = new JLabel[3];                             // 选择操作

    static {
        //初始化退出
        escapeLPane.setLayout(null);
        escapeLPane.setBounds(7 * GAME_PIX_72, 2 * GAME_PIX_72, GAME_PIX_72 * 4, GAME_PIX_72 * 3);
        escapeLPane.setBorder(BorderFactory.createLineBorder(new Color(204, 102, 0), 8, true));
        escapeLPane.setBackground(Color.BLACK);
        escapeLPane.setOpaque(true);
        escapeLPane.setVisible(false);
    }

    public static void displayEscape(){
        /*绘制整个退出菜单*/

        //初始化退出
        escapeLPane.setLayout(null);
        escapeLPane.setBounds(7 * GAME_PIX_72, 2 * GAME_PIX_72, GAME_PIX_72 * 4, GAME_PIX_72 * 3);
        escapeLPane.setBorder(BorderFactory.createLineBorder(new Color(201, 121, 56), 8, true));
        escapeLPane.setBackground(Color.BLACK);
        escapeLPane.setOpaque(true);

        JLabel save = new JLabel("▶: 存档");
        save.setFont(new Font("Serif", 0, 30));
        save.setForeground(Color.WHITE);
        save.setBounds(50, 30, 200, 50);
        escapeLPane.add(save);
        choices[0] = save;

        JLabel load = new JLabel("▷: 读档");
        load.setFont(new Font("Serif", 0, 30));
        load.setForeground(Color.WHITE);
        load.setBounds(50, 80, 200, 50);
        escapeLPane.add(load);
        choices[1] = load;

        JLabel esc = new JLabel("▷: 退出");
        esc.setFont(new Font("Serif", 0, 30));
        esc.setForeground(Color.WHITE);
        esc.setBounds(50, 130, 200, 50);
        escapeLPane.add(esc);
        choices[2] = esc;

        //开始执行退出菜单的任务
        escapeLPane.setVisible(true);

            gameFrame.addKeyListener(new KeyListener() {
                int selection = 0;
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if(selection != 2 && e.getKeyCode() == e.VK_S && choices[selection + 1] != null){
                        choices[selection].setText(choices[selection].getText().replaceAll("▶", "▷"));
                        selection = selection + 1;
                        choices[selection].setText(choices[selection].getText().replaceAll("▷", "▶"));
                        gameFrame.repaint();
                    }
                    if(selection != 0 && e.getKeyCode() == e.VK_W && choices[selection - 1] != null){
                        choices[selection].setText(choices[selection].getText().replaceAll("▶", "▷"));
                        selection = selection - 1;
                        choices[selection].setText(choices[selection].getText().replaceAll("▷", "▶"));
                        gameFrame.repaint();
                    }
                    if(e.getKeyCode() == e.VK_SPACE){
                        switch (selection){
                            case 0:             //存档
                                java.lang.System.out.print("save");
                                save();
                                break;
                            case 1:             //读档
                                java.lang.System.out.print("load");
                                load();
                                printFrame();
                                break;
                            case 2:             //退出游戏
                                java.lang.System.exit(1);
                                break;
                        }
                    }
                    if(e.getKeyCode() == e.VK_ESCAPE){
                        inConversation = false;
                        escapeLPane.removeAll();
                        escapeLPane.setVisible(false);
                        escapeLPane.removeKeyListener(this);
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
    }
}
