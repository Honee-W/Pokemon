package edu.nwpu.fly;

/**
 * ?????:???????
 */
public class Bullet extends FlyingObject {
	private int speed = 10;  //????????
	
	/** ????????? */
	public Bullet(int x,int y){
		this.x = x;
		this.y = y;
		this.image = ShootGame.bullet;
	}

	/** ??? */
	@Override
	public void step(){   
		y-=speed;
	}

	/** ??紦?? */
	@Override
	public boolean outOfBounds() {
		return y<-height;
	}

}
