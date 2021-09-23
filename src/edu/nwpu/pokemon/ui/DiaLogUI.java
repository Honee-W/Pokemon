package edu.nwpu.pokemon.ui;

import edu.nwpu.pokemon.data.ImageData;

import static edu.nwpu.pokemon.System.gamePanel;
import static edu.nwpu.pokemon.System.gameFrame;
import static edu.nwpu.pokemon.System.inConversation;
import static edu.nwpu.pokemon.System.GAME_PIX_72;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * 对话界面
 * （加定时器自动播放 以及提示（按空格键继续））
 * @author 老毛桃
 * @version v1.0.0
 */
public class DiaLogUI {

    static int count = 0;           //对话标号
    static int x = 0, y = 0;        //对话位置

    //初始化对话框界面
    public static JLayeredPane dialogLPane = new JLayeredPane();
    //对话背景图
    public static JLabel dialogBgImg = new JLabel();
    //对话文字区域
    public static JTextArea text = new JTextArea(20, 20);
    public static JLabel imgIco = new JLabel();

    static {
        // 初始化 对话事件
        // 初始化背景图
        dialogLPane.setLayout(null);
        dialogBgImg.setIcon(new ImageIcon(ImageData.blankBgImg));
        dialogLPane.add(dialogBgImg, 1, 0);
        dialogBgImg.setBorder(BorderFactory.createLineBorder(new Color(204, 102, 0), 8, true));
        //初始化文本
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Serif", 0, 30));
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setOpaque(false);
        text.setEditable(false);
        text.setFocusable(false);
    }

    //创建一个对话事件
    public static void talk(String[] messages, BufferedImage[] characters, int[] w, int[] h) {
        Insets insets = dialogLPane.getInsets();

        //读取角色贴图
        imgIco.setIcon(new ImageIcon(characters[0]));
        imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX_72, GAME_PIX_72);

        //获取文本
        text.setBounds(100 + insets.left, 20 + insets.top, w[0] - 100, h[0]);
        text.setText(messages[0]);
        //获取对话背景
        dialogBgImg.setBounds(0, 0, w[0], h[0]);
        dialogLPane.setBounds(430, 570, w[0], h[0]);
        dialogLPane.add(imgIco, 2, 0);
        dialogLPane.add(text, 3, 0);
        //在游戏界面显示
        gamePanel.add(dialogLPane);
        //刷新界面显示
        gamePanel.repaint();

        //定时器自动对话
        Timer autoDialogue = new Timer (3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //删除原对话
                dialogLPane.remove(imgIco);
                dialogLPane.remove(text);
                gamePanel.remove(dialogLPane);
                count++;
                //如果对话结束，则关闭对话界面
                if (count >= messages.length) {
                    inConversation = false;
                    count = 0;
                    return;
                }
                //对话位置1
                if (count % 2 == 1) {
                    x = 430;
                    y = 570;
                } else {
                    x = 430;
                    y = 570;
                    //对话位置2
                }
                //输出新对话框
                imgIco.setIcon(new ImageIcon(characters[count]));
                imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX_72, GAME_PIX_72);

                text.setBounds(100 + insets.left, 20 + insets.top, w[count] - 100, h[count]);
                text.setText(messages[count]);

                dialogBgImg.setBounds(0, 0, w[count], h[count]);
                dialogLPane.setBounds(x, y, w[count], h[count]);
                dialogLPane.add(imgIco, 2, 0);
                dialogLPane.add(text, 3, 0);

                gamePanel.add(dialogLPane);
                gamePanel.repaint();
            }
        });

        //键盘监听，来推进对话
        gameFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //空格键下一句话
                if (e.getKeyCode() == e.VK_SPACE) {
                    autoDialogue.stop();
                    //删除原对话
                    dialogLPane.remove(imgIco);
                    dialogLPane.remove(text);
                    gamePanel.remove(dialogLPane);
                    count++;
                    //如果对话结束，则关闭对话界面，计数复位
                    if (count >= messages.length) {
                        inConversation = false;
                        gameFrame.removeKeyListener(this);
                        count = 0;
                        return;
                    }
                    //对话位置1
                    if (count % 2 == 1) {
                        x = 430;
                        y = 570;
                    } else {
                        x = 430;
                        y = 570;
                        //对话位置2
                    }

                    //输出新对话框
                    imgIco.setIcon(new ImageIcon(characters[count]));
                    imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX_72, GAME_PIX_72);
                    text.setBounds(100 + insets.left, 20 + insets.top, w[count] - 100, h[count]);
                    text.setText(messages[count]);
                    dialogBgImg.setBounds(0, 0, w[count], h[count]);
                    dialogLPane.setBounds(x, y, w[count], h[count]);
                    dialogLPane.add(imgIco, 2, 0);
                    dialogLPane.add(text, 3, 0);
                    gamePanel.add(dialogLPane);
                    gamePanel.repaint();
                }
                if(e.getKeyCode() == e.VK_ENTER){
                    //删除对话
                    autoDialogue.stop();
                    dialogLPane.remove(imgIco);
                    dialogLPane.remove(text);
                    gamePanel.remove(dialogLPane);
                    //退出对话
                    inConversation = false;
                    gameFrame.removeKeyListener(this);
                    return;
                }
                if(e.getKeyCode() == e.VK_U){
                    autoDialogue.start();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
