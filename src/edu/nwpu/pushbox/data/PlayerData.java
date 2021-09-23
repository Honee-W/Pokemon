package edu.nwpu.pushbox.data;

import java.awt.image.BufferedImage;

/**
 * 玩家类，记录玩家位置信息
 * @author laomaotao
 */
public class PlayerData {
    private int toward; //当前朝向 0上 1下 2左 3右
    private int posX;   //X 坐标值
    private int posY;   //Y 坐标值

    //构造方法，游戏开始时主教的角色属性
    public PlayerData(){
        this.toward=1;
        this.posX=5;
        this.posY=9;
    }

    //移动位置
    public void move(int x,int y){
        posX =x;
        posY =y;
    }

    //根据朝向换图片
    public BufferedImage draw(){
        if(toward==0)
            return ImageData.map.get(0);
        if(toward==1)
            return ImageData.map.get(1);
        if(toward==2)
            return ImageData.map.get(2);
        if(toward==3)
            return ImageData.map.get(3);
        return null;
    }

    //get,set方法
    public int getToward(){return toward;}
    public void setToward(int toward1){toward=toward1;}

    public int getPosX(){return posX;}
    public void setPosX(int posX1){posX=posX1;}

    public int getPosY(){return posY;}
    public void setPosY(int posY1){posY=posY1;}
}

