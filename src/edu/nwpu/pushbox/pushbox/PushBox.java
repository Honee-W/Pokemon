package edu.nwpu.pushbox.pushbox;
import edu.nwpu.pushbox.data.BoxData;
import edu.nwpu.pushbox.data.ImageData;
import edu.nwpu.pushbox.data.PlayerData;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import static edu.nwpu.pushbox.data.MapData.Map;

/**
 * 推箱子 类
 *
 * 游戏核心交互类
 *
 * 绘制游戏主面板，交互函数，响应键盘输入
 *
 * @author  laomaotao
 */
public class PushBox extends JPanel {

    //像素值
    public static final int GAME_PIX_72 = 72;

    // 全局变量
    public static boolean inConversation = false;   // 允许键盘交互

    //玩家，箱子和地图
    public static HashMap<Integer, BufferedImage> imgSource = ImageData.map;
    public static PlayerData player = new PlayerData();
    public static BoxData box =new BoxData();
    //框架和面板
    public static JFrame gameFrame;
    public static JPanel gamePanel;


    //构造方法
    public PushBox(){
        setLayout(null);
        JOptionPane.showMessageDialog(null,"打败了最终BOSS，考验还没结束，进行命运的抉择吧!");
        gameFrame =new JFrame("命运的抉择");
    }

    //重写paintComponent()方法
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 =(Graphics2D) g;

        //绘制游戏主背景
        g2.drawImage(ImageData.backGround,0,0,PushBox.GAME_PIX_72*12,PushBox.GAME_PIX_72*12,null);


        //绘制 地图数据
        for(int x=0;x<12;x++){
            for(int y=0;y<12;y++){
                int id = Map[x][y];
                BufferedImage a = imgSource.get(id);
                g2.drawImage(a,GAME_PIX_72*y,GAME_PIX_72*x,null);
            }
        }

        //绘制 玩家
        g2.drawImage(player.draw(), player.getPosX()*GAME_PIX_72,player.getPosY()*GAME_PIX_72,null);

        //绘制 箱子
        g2.drawImage(box.draw(),box.getBox_X()*GAME_PIX_72,box.getBox_Y()*GAME_PIX_72,null);
    }


}


