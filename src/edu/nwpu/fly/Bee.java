package edu.nwpu.fly;

import java.util.Random;

/** ??????????? ????н??? */
public class Bee extends FlyingObject implements Award{
	private int xSpeed = 1;   //x??????????
	private int ySpeed = 2;   //y??????????
	private int awardType;    //????????
	
	/** ????????? */
	public Bee(){
		this.image = ShootGame.bee;
		width = 2*ShootGame.bullet.getHeight();
		height = 2*ShootGame.bullet.getHeight();
		y = -height;
		Random rand = new Random();
		x = rand.nextInt(ShootGame.WIDTH - width);
		awardType = rand.nextInt(2);   //????????????
	}
	
	/** ?????????? */
	public int getType(){
		return awardType;
	}

	/** ??紦?? */
	@Override
	public boolean outOfBounds() {
		return y>ShootGame.HEIGHT;
	}

	/** ???????б??? */
	@Override
	public void step() {      
		
		this.image = RotateImage.Rotate(this.image, 90);
		x += xSpeed;
		y += ySpeed;
		if(x > ShootGame.WIDTH-width){  
			xSpeed = -1;
		}
		if(x < 0){
			xSpeed = 1;
		}
	}
}