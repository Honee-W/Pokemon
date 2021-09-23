package edu.nwpu.pokemon.ui;

import edu.nwpu.pokemon.npc.Prop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static edu.nwpu.pokemon.System.*;

public class BagUI {
	public static JLayeredPane bagLPane = new JLayeredPane();
	static {
        // 初始化 预测面板
        bagLPane.setLayout(null);
        bagLPane.setBounds(6 * GAME_PIX_72, GAME_PIX_72, GAME_PIX_72 * 11, GAME_PIX_72 * 11);
        bagLPane.setBackground(Color.BLACK);   // 黑色背景
        bagLPane.setOpaque(true);
        bagLPane.setVisible(false);
    }
	
	public static void displayObjects() {
		 inConversation = true;
	     bagLPane.setVisible(true);
	     int cnt=0;
	     if(Prop.isHasJump) {
	    	 JLabel nameLabel = new JLabel("名称：风之罗盘" ); 
	    	 JLabel descriptionLabel = new JLabel("功能描述：开启跳跃楼层功能");
	    	 nameLabel.setBounds(115, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
             nameLabel.setForeground(Color.WHITE);
             nameLabel.setFont(new Font("Serif", 0, 25));
             
             descriptionLabel.setBounds(115, 24 + 96 * cnt + GAME_PIX_72 / 2, 10 * GAME_PIX_72, GAME_PIX_72 / 2);
             descriptionLabel.setForeground(Color.WHITE);
             descriptionLabel.setFont(new Font("Serif", 0, 25));
             
             ImageIcon ii = new ImageIcon("res/Deprecated/item/images/itemProperty_15.png");
             ii.setImage(ii.getImage().getScaledInstance(GAME_PIX_72,GAME_PIX_72,Image.SCALE_DEFAULT));
             JLabel headLabel = new JLabel(ii);
             headLabel.setBounds(25, 24 + 96 * cnt,GAME_PIX_72,GAME_PIX_72);
             cnt++;
             
             bagLPane.add(nameLabel);
             bagLPane.add(descriptionLabel);
             bagLPane.add(headLabel);
	     }
	     if(Prop.isHasForecast) {
	    	JLabel nameLabel = new JLabel("名称：圣光辉" ); 
	    	JLabel descriptionLabel = new JLabel("功能描述：开启查看怪物数据功能");
	    	nameLabel.setBounds(115, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setFont(new Font("Serif", 0, 25));
            
            descriptionLabel.setBounds(115, 24 + 96 * cnt + GAME_PIX_72 / 2, 10 * GAME_PIX_72, GAME_PIX_72 / 2);
            descriptionLabel.setForeground(Color.WHITE);
            descriptionLabel.setFont(new Font("Serif", 0, 25));
            
            ImageIcon ii = new ImageIcon("res/Deprecated/item/images/itemProperty_18.png");
            ii.setImage(ii.getImage().getScaledInstance(GAME_PIX_72,GAME_PIX_72,Image.SCALE_DEFAULT));
            JLabel headLabel = new JLabel(ii);
            headLabel.setBounds(25, 24 + 96 * cnt,GAME_PIX_72,GAME_PIX_72);
            cnt++;
            
            bagLPane.add(nameLabel);
            bagLPane.add(descriptionLabel);
            bagLPane.add(headLabel);
	     }
	     if(Prop.isHasCross) {
	    	 	JLabel nameLabel = new JLabel("名称：幸运十字架" ); 
		    	JLabel descriptionLabel = new JLabel("功能描述：触发和仙女的对话");
		    	nameLabel.setBounds(115, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
	            nameLabel.setForeground(Color.WHITE);
	            nameLabel.setFont(new Font("Serif", 0, 25));
	            
	            descriptionLabel.setBounds(115, 24 + 96 * cnt + GAME_PIX_72 / 2, 10 * GAME_PIX_72, GAME_PIX_72 / 2);
	            descriptionLabel.setForeground(Color.WHITE);
	            descriptionLabel.setFont(new Font("Serif", 0, 25));
	            
	            ImageIcon ii = new ImageIcon("res/Deprecated/item/images/itemProperty_14.png");
	            ii.setImage(ii.getImage().getScaledInstance(GAME_PIX_72,GAME_PIX_72,Image.SCALE_DEFAULT));
	            JLabel headLabel = new JLabel(ii);
	            headLabel.setBounds(25, 24 + 96 * cnt,GAME_PIX_72,GAME_PIX_72);
	            cnt++;
	            
	            bagLPane.add(nameLabel);
	            bagLPane.add(descriptionLabel);
	            bagLPane.add(headLabel);
	     }
	     if(Prop.isHasBlueStick) {
	    	 	JLabel nameLabel = new JLabel("名称：冰之灵杖" ); 
		    	JLabel descriptionLabel = new JLabel("功能描述：特殊任务物品，内部似乎封印着无穷的力量");
		    	nameLabel.setBounds(115, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
	            nameLabel.setForeground(Color.WHITE);
	            nameLabel.setFont(new Font("Serif", 0, 25));
	            
	            descriptionLabel.setBounds(115, 24 + 96 * cnt + GAME_PIX_72 / 2, 10 * GAME_PIX_72, GAME_PIX_72 / 2);
	            descriptionLabel.setForeground(Color.WHITE);
	            descriptionLabel.setFont(new Font("Serif", 0, 25));
	            
	            ImageIcon ii = new ImageIcon("res/Deprecated/item/images/ziyuan5_24.png");
	            ii.setImage(ii.getImage().getScaledInstance(GAME_PIX_72,GAME_PIX_72,Image.SCALE_DEFAULT));
	            JLabel headLabel = new JLabel(ii);
	            headLabel.setBounds(25, 24 + 96 * cnt,GAME_PIX_72,GAME_PIX_72);
	            cnt++;
	            
	            bagLPane.add(nameLabel);
	            bagLPane.add(descriptionLabel);
	            bagLPane.add(headLabel);
	     }
	     if(Prop.isHasGreenStick) {
	    	 	JLabel nameLabel = new JLabel("名称：心之灵杖" ); 
		    	JLabel descriptionLabel = new JLabel("功能描述：特殊任务物品，内部似乎封印着无穷的力量");
		    	nameLabel.setBounds(115, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
	            nameLabel.setForeground(Color.WHITE);
	            nameLabel.setFont(new Font("Serif", 0, 25));
	            
	            descriptionLabel.setBounds(115, 24 + 96 * cnt + GAME_PIX_72 / 2, 10 * GAME_PIX_72, GAME_PIX_72 / 2);
	            descriptionLabel.setForeground(Color.WHITE);
	            descriptionLabel.setFont(new Font("Serif", 0, 25));
	            
	            ImageIcon ii = new ImageIcon("res/Deprecated/item/images/ziyuan5_27.png");
	            ii.setImage(ii.getImage().getScaledInstance(GAME_PIX_72,GAME_PIX_72,Image.SCALE_DEFAULT));
	            JLabel headLabel = new JLabel(ii);
	            headLabel.setBounds(25, 24 + 96 * cnt,GAME_PIX_72,GAME_PIX_72);
	            cnt++;
	            
	            bagLPane.add(nameLabel);
	            bagLPane.add(descriptionLabel);
	            bagLPane.add(headLabel);
	     }
	     if(Prop.isHasRedStick) {
	    	 	JLabel nameLabel = new JLabel("名称：炎之灵杖" ); 
		    	JLabel descriptionLabel = new JLabel("功能描述：特殊任务物品，内部似乎封印着无穷的力量");
		    	nameLabel.setBounds(115, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
	            nameLabel.setForeground(Color.WHITE);
	            nameLabel.setFont(new Font("Serif", 0, 25));
	            
	            descriptionLabel.setBounds(115, 24 + 96 * cnt + GAME_PIX_72 / 2, 10 * GAME_PIX_72, GAME_PIX_72 / 2);
	            descriptionLabel.setForeground(Color.WHITE);
	            descriptionLabel.setFont(new Font("Serif", 0, 25));
	            
	            ImageIcon ii = new ImageIcon("res/Deprecated/item/images/ziyuan5_26.png");
	            ii.setImage(ii.getImage().getScaledInstance(GAME_PIX_72,GAME_PIX_72,Image.SCALE_DEFAULT));
	            JLabel headLabel = new JLabel(ii);
	            headLabel.setBounds(25, 24 + 96 * cnt,GAME_PIX_72,GAME_PIX_72);
	            cnt++;
	            
	            bagLPane.add(nameLabel);
	            bagLPane.add(descriptionLabel);
	            bagLPane.add(headLabel);
	     }
	     if(Prop.isHasHammer) {
	    	 	JLabel nameLabel = new JLabel("名称：星光神榔" ); 
		    	JLabel descriptionLabel = new JLabel("功能描述：特殊任务物品,似乎有些奇怪的作用");
		    	nameLabel.setBounds(115, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
	            nameLabel.setForeground(Color.WHITE);
	            nameLabel.setFont(new Font("Serif", 0, 25));
	            
	            descriptionLabel.setBounds(115, 24 + 96 * cnt + GAME_PIX_72 / 2, 10 * GAME_PIX_72, GAME_PIX_72 / 2);
	            descriptionLabel.setForeground(Color.WHITE);
	            descriptionLabel.setFont(new Font("Serif", 0, 25));
	            
	            ImageIcon ii = new ImageIcon("res/Deprecated/item/images/itemProperty_16.png");
	            ii.setImage(ii.getImage().getScaledInstance(GAME_PIX_72,GAME_PIX_72,Image.SCALE_DEFAULT));
	            JLabel headLabel = new JLabel(ii);
	            headLabel.setBounds(25, 24 + 96 * cnt,GAME_PIX_72,GAME_PIX_72);
	            cnt++;;
	            
	            bagLPane.add(nameLabel);
	            bagLPane.add(descriptionLabel);
	            bagLPane.add(headLabel);
	     }
	     if(cnt==0) {
	    	 	JLabel Label = new JLabel("当前背包内无物品" ); 
		    	Label.setBounds(115, 24 + 96 * cnt, 3 * GAME_PIX_72, GAME_PIX_72 / 2);
		    	Label.setForeground(Color.WHITE);
	            Label.setFont(new Font("Serif", 0, 25));                   
	            bagLPane.add(Label);    
	     }

	     bagLPane.addKeyListener(new KeyListener() {
		     @Override
		     public void keyTyped(KeyEvent e) {

		     }

		     @Override
		     public void keyPressed(KeyEvent e) {
		     	if (e.getKeyCode() == e.VK_B)
		     	{
				inConversation = false;
				bagLPane.removeAll();
				bagLPane.setVisible(false);
				bagLPane.removeKeyListener(this);
		     	}
		     }
		     @Override
		     public void keyReleased(KeyEvent e) {

		     }
	     });
	 }
}
