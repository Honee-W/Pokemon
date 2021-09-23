package edu.nwpu.pokemon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static edu.nwpu.pokemon.System.*;
import static java.awt.event.KeyEvent.*;

/**
 * 自动作战算法类
 * @author laomaotao
 */
public class Algorithm {
    //计时器
    protected Timer timer;

    //构造方法
    public Algorithm(){
        JOptionPane.showMessageDialog(null,"确定要开始挂机吗？");

        //匿名内部类，计时器，玩家定时移动
        timer = new Timer(500, new ActionListener() {   //频率0.1s
            @Override
            /**
             * 定时产生随机数，移动主人物
             * VK_LEFT =37, VK_UP=38, VK_RIGHT=39,VK_DOWN=40
             */
            public void actionPerformed(ActionEvent e) {
                int direction = (int) (Math.random() * 4 + 37);
                switch (direction)
                {
                    case 37://向左移动
                        if (playerBean_1.getPosX() - 1 < 11 && playerBean_1.getPosX() - 1 >= 0){
                            playerBean_1.setToward(0);
                            interaction(playerBean_1.getPosX() - 1, playerBean_1.getPosY());
                        }
                        break;
                    case VK_UP://向上移动
                        if (playerBean_1.getPosY() - 1 < 11 && playerBean_1.getPosY() - 1 >= 0){
                            playerBean_1.setToward(3);
                            interaction(playerBean_1.getPosX(), playerBean_1.getPosY() - 1);
                        }
                        break;
                    case VK_RIGHT://向右移动
                        if (playerBean_1.getPosX() + 1 < 11 && playerBean_1.getPosX() + 1 >= 0){
                            playerBean_1.setToward(2);
                            interaction(playerBean_1.getPosX() + 1, playerBean_1.getPosY());
                        }
                        break;
                    case VK_DOWN://向下移动
                        if (playerBean_1.getPosY() + 1 < 11 && playerBean_1.getPosY() + 1 >= 0){
                            playerBean_1.setToward(1);
                            interaction(playerBean_1.getPosX(), playerBean_1.getPosY() + 1);
                        }
                        break;
                }
                if(gameSec%10==0){
                    currentFloor++;
                }
            }
        });
        timer.start();
    }
}
