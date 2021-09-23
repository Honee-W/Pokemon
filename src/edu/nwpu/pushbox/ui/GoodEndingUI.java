package edu.nwpu.pushbox.ui;

import edu.nwpu.pushbox.pushbox.PushBox;
import edu.nwpu.pushbox.data.ImageData;

import javax.swing.*;
import java.awt.*;

/**
 * 好结局 类
 * @author laomaotao
 */
public class GoodEndingUI extends JPanel {

    private JFrame goodFrame;
    /**
     * 构造方法
     */
    public GoodEndingUI(){
        //初始化面板
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PushBox.GAME_PIX_72*12,PushBox.GAME_PIX_72*12));

        //设置并添加剧情框
        JTextArea jTextArea =new JTextArea();
        jTextArea.setText("恭喜您已成功通关!"+"\n"+"拯救出莎菲雅"+"\n"+"从此过上了幸福的生活");
        jTextArea.setForeground(Color.red);
        jTextArea.setFont(new Font("宋体",Font.BOLD,30));
        jTextArea.setOpaque(false);
        jTextArea.setBounds(PushBox.GAME_PIX_72*4,PushBox.GAME_PIX_72*5,PushBox.GAME_PIX_72*5,PushBox.GAME_PIX_72*5);
        this.add(jTextArea);
        //初始化框架，设置主面板
        goodFrame =new JFrame("美好前程");
        goodFrame.setContentPane(this);
        goodFrame.pack();
        goodFrame.setResizable(false);
        goodFrame.setLocationRelativeTo(null);
        goodFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        goodFrame.setVisible(true);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        //加载背景图
        g.drawImage(ImageData.goodEnding,0,0, PushBox.GAME_PIX_72*12,PushBox.GAME_PIX_72*12,null);
    }
}
