package edu.nwpu.pushbox.data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * 存储图片类
 * @author laomaotao
 */
public class ImageData {

    public static final int PLAYER_UP=1;
    public static final int PLAYER_DOWN=2;
    public static final int PLAYER_RIGHT=3;
    public static final int PLAYER_LEFT=4;

    public static HashMap<Integer, BufferedImage> map =new HashMap<>();

    public static BufferedImage backGround;
    public static BufferedImage goodEnding;
    public static BufferedImage badEnding;

    static{
        try {
            backGround=ImageIO.read(new File(System.getProperty("user.dir")+"/res/image/background.png"));
            goodEnding = ImageIO.read(new File(System.getProperty("user.dir")+"/res/image/GoodEnding.jpg"));
            badEnding = ImageIO.read(new File(System.getProperty("user.dir")+"/res/image/BadEnding.png"));

            map.clear();
            map.put(0, ImageIO.read(new File(System.getProperty("user.dir")+"/res/image/up.png")));
            map.put(1,ImageIO.read(new File(System.getProperty("user.dir")+"/res/image/down.png")));
            map.put(2,ImageIO.read(new File(System.getProperty("user.dir")+"/res/image/left.png")));
            map.put(3, ImageIO.read(new File(System.getProperty("user.dir")+"/res/image/right.png")));
            map.put(4,ImageIO.read(new File(System.getProperty("user.dir")+"/res/image/rock.png")));
            map.put(5,ImageIO.read(new File(System.getProperty("user.dir")+"/res/image/box.png")));
            map.put(6,ImageIO.read(new File(System.getProperty("user.dir")+"/res/image/goodDoor.png")));
            map.put(7,ImageIO.read(new File(System.getProperty("user.dir")+"/res/image/way.png")));
            map.put(8,ImageIO.read(new File(System.getProperty("user.dir")+"/res/image/badDoor.png")));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("图片加载失败!");
        }


    }
}
