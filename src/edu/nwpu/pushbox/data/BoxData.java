package edu.nwpu.pushbox.data;

import java.awt.image.BufferedImage;

/**
 * 箱子类 ，记录箱子位置
 * @author laomaotao
 */
public class BoxData {
    private int Box_X;
    private int Box_Y;
    //构造方法，箱子初始位置
    public BoxData(){
        Box_X =4;
        Box_Y =9;
    }

    //箱子坐标的get,set方法
    public int getBox_X() {
        return Box_X;
    }

    //箱子移动
    public void move(int x,int y){
        Box_X=x;
        Box_Y=y;
    }
    public BufferedImage draw(){
        return ImageData.map.get(5);
    }

    public void setBox_X(int box_X) {
        Box_X = box_X;
    }

    public int getBox_Y() {
        return Box_Y;
    }

    public void setBox_Y(int box_Y) {
        Box_Y = box_Y;
    }
}
