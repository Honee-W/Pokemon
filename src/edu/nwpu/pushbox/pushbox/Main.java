package edu.nwpu.pushbox.pushbox;

import edu.nwpu.pushbox.ui.BadEndingUI;
import edu.nwpu.pushbox.ui.GoodEndingUI;
import edu.nwpu.pushbox.data.MapData;
import edu.nwpu.fly.ShootGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.StringTokenizer;

import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_LEFT;

/**
 * 驱动类
 * @author laomaotao
 */
public class Main {
    //存储玩家位置数据
    public static PlayerLocation playerLocation;
    public static BoxLocation boxLocation;

    public static void main(String[] args) {

        //关闭上一个界面

        //存储人物位置及朝向
        playerLocation =new PlayerLocation();
        playerLocation.saveLocation(PushBox.player.getPosX(),PushBox.player.getPosY(),PushBox.player.getToward());
        //存储箱子位置
        boxLocation =new BoxLocation();
        boxLocation.saveLocation(PushBox.box.getBox_X(),PushBox.box.getBox_Y());

        //创建主页面
        PushBox.gamePanel = new PushBox();
        PushBox.gamePanel.setPreferredSize(new Dimension(PushBox.GAME_PIX_72*12,PushBox.GAME_PIX_72*12));

        PushBox.gameFrame.addKeyListener(new DirectionListener());
        PushBox.gameFrame.addKeyListener(new ReturnListener());
        PushBox.gameFrame.setContentPane(PushBox.gamePanel);
        PushBox.gameFrame.setResizable(false);
        PushBox.gameFrame.pack();
        PushBox.gameFrame.setLocationRelativeTo(null);
        PushBox.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PushBox.gameFrame.setVisible(true);
    }

    /**
     * 内部类，监听按键事件，根据按键改变人物及箱子位置
     */
    static class DirectionListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (!PushBox.inConversation)
                switch (e.getKeyCode()) {

                    case VK_DOWN:   // 键盘 ↓
                        System.out.println("向下移动");
                        switch (MapData.Map[PushBox.player.getPosY()+1][PushBox.player.getPosX()]){
                            case 4:   //前面是岩石
                                break;
                            case 7:   //前面是路
                                if ((PushBox.player.getPosX() == PushBox.box.getBox_X())&&(PushBox.player.getPosY()+1==PushBox.box.getBox_Y()) ) {//前面是箱子
                                    switch (MapData.Map[PushBox.box.getBox_Y() + 1][PushBox.box.getBox_X()]) {
                                        case 4:  //箱子前面是岩石
                                            break;
                                        case 6:  //箱子前面是好结局
                                            new GoodEndingUI();
                                            break;
                                        case 8:  //箱子前面是坏结局
                                            new BadEndingUI();
                                            break;
                                        case 7:  //箱子前面是路
                                            PushBox.box.move(PushBox.box.getBox_X(), PushBox.box.getBox_Y() + 1);
                                            PushBox.player.move(PushBox.player.getPosX(),PushBox.player.getPosY() + 1);
                                            boxLocation.saveLocation(PushBox.box.getBox_X(),PushBox.box.getBox_Y());
                                            break;
                                    }
                                }else{
                                    PushBox.player.move(PushBox.player.getPosX(),PushBox.player.getPosY() + 1);
                                }
                                PushBox.player.setToward(1);
                                playerLocation.saveLocation(PushBox.player.getPosX(),PushBox.player.getPosY(),PushBox.player.getToward());
                                PushBox.gameFrame.repaint();
                                    break;
                            case 6://前面是门
                            case 8:
                                break;
                                }
                                break;
                    case VK_RIGHT:  // 键盘 →
                        System.out.println("向右移动");
                        switch (MapData.Map[PushBox.player.getPosY()][PushBox.player.getPosX()+1]){
                            case 4:   //前面是岩石
                                break;
                            case 7:   //前面是路
                                if ((PushBox.player.getPosX()+1 == PushBox.box.getBox_X())&&(PushBox.player.getPosY()==PushBox.box.getBox_Y()) ) {//前面是箱子
                                    switch (MapData.Map[PushBox.box.getBox_Y() ][PushBox.box.getBox_X()+1]) {
                                        case 4:  //箱子前面是岩石
                                            break;
                                        case 6:  //箱子前面是好结局
                                            new GoodEndingUI();
                                            break;
                                        case 8:  //箱子前面是坏结局
                                            new BadEndingUI();
                                            break;
                                        case 7:  //箱子前面是路
                                            PushBox.box.move(PushBox.box.getBox_X()+1, PushBox.box.getBox_Y());
                                            PushBox.player.move(PushBox.player.getPosX()+1,PushBox.player.getPosY());
                                            boxLocation.saveLocation(PushBox.box.getBox_X(),PushBox.box.getBox_Y());
                                            break;
                                    }
                                }else{
                                    PushBox.player.move(PushBox.player.getPosX()+1,PushBox.player.getPosY());
                                }
                                PushBox.player.setToward(3);
                                playerLocation.saveLocation(PushBox.player.getPosX(),PushBox.player.getPosY(),PushBox.player.getToward());
                                PushBox.gameFrame.repaint();
                                break;
                            case 6://前面是门
                            case 8:
                                break;
                        }
                        break;
                    case VK_UP:     // 键盘 ↑
                        System.out.println("向上移动");
                        switch (MapData.Map[PushBox.player.getPosY()-1][PushBox.player.getPosX()]){
                            case 4:   //前面是岩石
                                break;
                            case 7:   //前面是路
                                if ((PushBox.player.getPosX() == PushBox.box.getBox_X())&&(PushBox.player.getPosY()-1==PushBox.box.getBox_Y()) ) {//前面是箱子
                                    switch (MapData.Map[PushBox.box.getBox_Y() - 1][PushBox.box.getBox_X()]) {
                                        case 4:  //箱子前面是岩石
                                            break;
                                        case 6:  //箱子前面是好结局
                                            new GoodEndingUI();
                                            break;
                                        case 8:  //箱子前面是坏结局
                                            new BadEndingUI();
                                            break;
                                        case 7:  //箱子前面是路
                                            PushBox.box.move(PushBox.box.getBox_X(), PushBox.box.getBox_Y() - 1);
                                            PushBox.player.move(PushBox.player.getPosX(),PushBox.player.getPosY() - 1);
                                            boxLocation.saveLocation(PushBox.box.getBox_X(),PushBox.box.getBox_Y());
                                            break;
                                    }
                                }else{
                                    PushBox.player.move(PushBox.player.getPosX(),PushBox.player.getPosY() - 1);
                                }
                                PushBox.player.setToward(0);
                                playerLocation.saveLocation(PushBox.player.getPosX(),PushBox.player.getPosY(),PushBox.player.getToward());
                                PushBox.gameFrame.repaint();
                                break;
                            case 6://前面是门
                            case 8:
                                break;
                        }
                        break;
                    case VK_LEFT:   // 键盘 ←
                        System.out.println("向左移动");
                        switch (MapData.Map[PushBox.player.getPosY()][PushBox.player.getPosX()-1]){
                            case 4:   //前面是岩石
                                break;
                            case 7:   //前面是路
                                if ((PushBox.player.getPosX() -1== PushBox.box.getBox_X())&&(PushBox.player.getPosY()==PushBox.box.getBox_Y()) ) {//前面是箱子
                                    switch (MapData.Map[PushBox.box.getBox_Y() ][PushBox.box.getBox_X()-1]) {
                                        case 4:  //箱子前面是岩石
                                            break;
                                        case 6:  //箱子前面是好结局
                                            new GoodEndingUI();
                                            break;
                                        case 8:  //箱子前面是坏结局
                                            new BadEndingUI();
                                            break;
                                        case 7:  //箱子前面是路
                                            PushBox.box.move(PushBox.box.getBox_X()-1, PushBox.box.getBox_Y() );
                                            PushBox.player.move(PushBox.player.getPosX()-1,PushBox.player.getPosY() );
                                            boxLocation.saveLocation(PushBox.box.getBox_X(),PushBox.box.getBox_Y());
                                            break;
                                    }
                                }else{
                                    PushBox.player.move(PushBox.player.getPosX()-1,PushBox.player.getPosY() );
                                }
                                PushBox.player.setToward(2);
                                playerLocation.saveLocation(PushBox.player.getPosX(),PushBox.player.getPosY(),PushBox.player.getToward());
                                PushBox.gameFrame.repaint();
                                break;
                            case 6://前面是门
                            case 8:
                                break;
                        }
                        break;
                }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    /**
     * 内部类，监听退出键，退回上一步
     */
    static  class ReturnListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==VK_ESCAPE){
                JOptionPane.showMessageDialog(null,"确认返回上一步操作吗?");
                //返回玩家位置信息并移动
                String s1= playerLocation.returnLocation();
                StringTokenizer tokenizer1 =new StringTokenizer(s1,"_");
                int lastX = Integer.parseInt(tokenizer1.nextToken());
                int lastY = Integer.parseInt(tokenizer1.nextToken());
                int lastToward =Integer.parseInt(tokenizer1.nextToken());
                System.out.println("上一步人物坐标为："+lastX+" "+lastY);
                System.out.println("上一步朝向为："+lastToward);
                PushBox.player.move(lastX,lastY);
                PushBox.player.setToward(lastToward);
                //返回箱子位置信息并移动
                String s2 =boxLocation.returnLocation();
                StringTokenizer tokenizer2 =new StringTokenizer(s2,"_");
                int boxLastX=Integer.parseInt(tokenizer2.nextToken());
                int boxLastY=Integer.parseInt(tokenizer2.nextToken());
                System.out.println("上一步箱子坐标为："+boxLastX+" "+boxLastY);
                PushBox.box.move(boxLastX,boxLastY);
                PushBox.gameFrame.repaint();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
