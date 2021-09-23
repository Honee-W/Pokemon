package edu.nwpu.pushbox.ui;

import edu.nwpu.pushbox.pushbox.PushBox;
import edu.nwpu.pushbox.data.ImageData;

import javax.swing.*;
import java.awt.*;

/**
 * 坏结局 类
 * @author laomaotao
 */
public class BadEndingUI extends JPanel {
    private JFrame badFrame;
    /**
     * 构造方法
     */
    public BadEndingUI(){
        //初始化面板
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PushBox.GAME_PIX_72*12,PushBox.GAME_PIX_72*12));

        //设置并添加剧情框
        JTextArea jTextArea =new JTextArea();
        jTextArea.setText("很遗憾您抉择错误!"+"\n"+"一失足"+"\n"+"成千古恨");
        jTextArea.setForeground(Color.RED);
        jTextArea.setFont(new Font("宋体",Font.BOLD,30));
        jTextArea.setOpaque(false);
        jTextArea.setBounds(PushBox.GAME_PIX_72*4,PushBox.GAME_PIX_72*5,PushBox.GAME_PIX_72*5,PushBox.GAME_PIX_72*5);
        this.add(jTextArea);
        //初始化框架，设置主面板
        badFrame =new JFrame("挑战失败");
        badFrame.setContentPane(this);
        badFrame.pack();
        badFrame.setResizable(false);
        badFrame.setLocationRelativeTo(null);
        badFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        badFrame.setVisible(true);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        //加载背景图
        g.drawImage(ImageData.badEnding,0,0, PushBox.GAME_PIX_72*12,PushBox.GAME_PIX_72*12,null);

    }
}
