package edu.nwpu.fly;

public class Boss extends FlyingObject implements Enemy {

	public Boss(){
		this.image = ShootGame.boss;
		width = 200;
		height = 200; 
		x = ShootGame.WIDTH/2-100;
		y = 0;
	}
	
	@Override
	public int getScore() {
		return 1;
	}

	@Override
	public boolean outOfBounds() {
		return true;
	}

	@Override
	public void step() {
		return;
	}

	public boolean shootBy(Bullet bullet) {
		int x = bullet.x;  //?????????
		int y = bullet.y;  //?????????
		return this.x<x && x<this.x+width && this.y<y && y<this.y+height;
	}
}
