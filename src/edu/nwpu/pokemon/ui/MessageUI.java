package edu.nwpu.pokemon.ui;

import edu.nwpu.pokemon.data.ImageData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static edu.nwpu.pokemon.System.*;


/**
 * 跳出信息框
 * @author 老毛桃
 * @version v1.0.0
 */
public class MessageUI {

    // 提示信息面板
    public static JLayeredPane messageLPane = new JLayeredPane();
    public static JLabel messageLabel = new JLabel();

    static {
        // 初始化 信息面板
        messageLPane.setLayout(null);
        messageLPane.setBounds(10, 270, GAME_PIX_72 * 18 - 20, 150);
        messageLabel.setBounds(0, 0, GAME_PIX_72 * 18 - 20, 150);
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(new Font("Serif", 0, 50));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 绘制消息面板
        JLabel messageBackground = new JLabel(new ImageIcon(ImageData.blankBgImg));
        messageBackground.setLayout(null);
        messageBackground.setBounds(0, 0, GAME_PIX_72 * 18 - 20, 150);
        messageBackground.setBorder(BorderFactory.createLineBorder(new Color(204, 102, 0), 8, true));
        messageLPane.add(messageBackground, 1, 0);
        messageLPane.add(messageLabel, 2, 0);
        messageLPane.setOpaque(true);
        //消息还未完成，暂不可视
        messageLPane.setVisible(false);
    }

        public static void displayMessage(String message) {
            //窗口可视化
            messageLPane.setVisible(true);
            inConversation = true;
            //设置定时器
            Timer animat = new Timer(500, new ActionListener() {
                int count = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    count++;
                    if (count == 2) {
                        messageLPane.setVisible(false);
                        inConversation = false;
                        //触发第二次动作，消息界面关闭
                        ((Timer) e.getSource()).stop();
                    }
                    //输出要写的文档
                    messageLabel.setText(message);
                    //刷新界面显示
                    gameFrame.repaint();
                }
            });
            //定时器初始化延迟
            animat.setInitialDelay(0);
            //定时器启动
            animat.start();
        }
}
