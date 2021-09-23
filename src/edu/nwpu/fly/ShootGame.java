package edu.nwpu.fly;

import edu.nwpu.pushbox.pushbox.Main;

import java.awt.Font;

import static edu.nwpu.pokemon.data.Map.LvMap;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class ShootGame extends JPanel{
	public static final int WIDTH = 400; // ????
	public static final int HEIGHT = 654; // ????
	/** ?????????: START RUNNING PAUSE GAME_OVER */
	private int state;
	private static final int START = 0;
	private static final int RUNNING = 1;
	private static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	public static final int SUCCESS = 4;

	private int score = 0; // ?÷?
	private Timer timer; // ?????
	private int intervel = 1000 / 100; // ?????(????)

	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	public static BufferedImage boss;
	public static BufferedImage success;

	public int getState() {
		return state;
	}

	private FlyingObject[] flyings = {}; // ?л?????
	public static Bullet[] bullets = {}; // ???????
	private Hero hero = new Hero(); // ????
	private Boss Tboss = new Boss(); // boss
	private int bossBlood = 2;
	int flyEnteredIndex = 0; // ????????????
	
	public JFrame frame;
	static { // ???????飬??????????
		try {
			background = ImageIO.read(new File(System.getProperty("user.dir")+"/res/fly/background.jpg"));
			start = ImageIO.read(new File(System.getProperty("user.dir")+"/res/fly/start.png"));
			airplane = ImageIO.read(new File(System.getProperty("user.dir")+"/res/fly/bullet.png"));
			bee = ImageIO.read(new File(System.getProperty("user.dir")+"/res/fly/bee.png"));
			bullet = ImageIO.read(new File(System.getProperty("user.dir")+"/res/fly/bullet.png"));
			hero0 = ImageIO.read(new File(System.getProperty("user.dir")+"/res/fly/hero0.png"));
			hero1 = ImageIO.read(new File(System.getProperty("user.dir")+"/res/fly/hero1.png"));
			pause = ImageIO.read(new File(System.getProperty("user.dir")+"/res/fly/pause.png"));
			gameover = ImageIO.read(new File(System.getProperty("user.dir")+"/res/fly/gameover.png"));
			boss = ImageIO.read(new File(System.getProperty("user.dir")+"/res/fly/boss.png"));
			success = ImageIO.read(new File(System.getProperty("user.dir")+"/res/fly/success.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** ?? */
	@Override
	public void paint(Graphics g) {
		paintBackground(g); // ???????
		paintHero(g); // ??????
		paintBullets(g); // ?????
		paintFlyingObjects(g); // ????????
		paintScore(g); // ??????
		paintState(g); // ???????
		paintBoss(g); // ??boss
		paintBlood(g);  //?????
		//jProgressBar.paint(g);
	}

	public void paintBackground(Graphics g) {
		g.drawImage(background, 0, 0, null); 
	}
	
	public void paintBlood(Graphics g) {
		int blood = Math.abs(bossBlood - score);
		int width = WIDTH * blood / bossBlood;
		g.setColor(Color.WHITE);
		g.drawRect(WIDTH/5, 8, WIDTH/2 , 10);
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 10);
		g.setColor(Color.GREEN);
		g.setFont(font);
		g.drawString(100 * blood / bossBlood + "%", WIDTH * 3 / 4, 18);
		g.setColor(Color.RED);
		g.fillRect(WIDTH/5, 8, width/2 , 10);
	}
	
	/** ?????? */
	public void paintHero(Graphics g) {
		g.drawImage(hero.getImage(), hero.getX(), hero.getY(),hero.getWidth(),hero.getHeight(), null);
	}

	/** ????? */
	public void paintBullets(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2, b.getY(),
					null);
		}
	}

	public void paintBoss(Graphics g) {
		
		g.drawImage(boss,WIDTH/2-100,0,200,200,null);
	}
	
	
	/** ???????? */
	public void paintFlyingObjects(Graphics g) {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			g.drawImage(f.getImage(), f.getX(), f.getY(), f.getWidth(),f.getHeight(),null);
		}
	}

	/** ?????? */
	public void paintScore(Graphics g) {
		int x = 10; // x????
		int y = HEIGHT - 60; // y????
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18); // ????
		g.setColor(Color.GREEN);
		g.setFont(font); // ????????
		//g.drawString("SCORE:" + score, x, y); // ??????
		//y=y+20; // y??????20
		g.drawString("LIFE:" + hero.getLife(), x, y); // ????
	}

	/** ??????? */
	public void paintState(Graphics g) {
		switch (state) {
		case START: // ??????
			//g.drawImage(start, 0, 0, null);
			break;
		case PAUSE: // ?????
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER: // ????????
			g.drawImage(gameover, 0, 0, null);
			break;
		case SUCCESS:
			g.drawImage(success, 0, 0,gameover.getWidth(),gameover.getHeight(), null);
		}
	}

	
	public void run() {
		frame = new JFrame("Boss战");
		ShootGame game = new ShootGame(); // ??????
		game.setBounds(0, 0, WIDTH, HEIGHT);
		frame.add(game); // ?????????JFrame??
		frame.setSize(WIDTH, HEIGHT); // ?????С
		frame.setAlwaysOnTop(true); // ??????????????
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ????????
		frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // ???????????
		frame.setLocationRelativeTo(null); // ?????????λ??
		frame.setVisible(true); // ???????paint
		game.action(); // ???????
	}

	/** ??????д??? */
	public void action() {
		// ?????????
		MouseAdapter l = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) { // ??????
				if (state == RUNNING) { // ???????????????--?????λ??
					int x = e.getX();
					int y = e.getY();
					hero.moveTo(x, y);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) { // ??????
				if (state == PAUSE) { // ???????????
					state = RUNNING;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) { // ??????
				if (state == RUNNING) { // ???δ??????????????????
					state = PAUSE;
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) { // ?????
				switch (state) {
				case START:
					state = RUNNING; // ????????????
					break;
				case GAME_OVER: // ????????????????
					flyings = new FlyingObject[0]; // ????????
					bullets = new Bullet[0]; // ??????
					hero = new Hero(); // ???????????
					score = 0; // ?????
					state = START; // ???????????
					break;
				}
			}
		};
		this.addMouseListener(l); // ?????????????
		this.addMouseMotionListener(l); // ?????????????

		timer = new Timer(); // ?????????
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (state == RUNNING) { // ??????
					enterAction(); // ????????
					stepAction(); // ?????
					shootAction(); // ???????
					bangAction(); // ??????????
					//jProgressBar.setValue(100-score);
					outOfBoundsAction(); // ??????????Ｐ???
					checkGameOverAction(); // ??????????
					checkSuccess();
				}
				repaint(); // ??棬????paint()????
				if(state == GAME_OVER || state == SUCCESS) {
					if(state == SUCCESS){
						Main pushBox = new Main();
						pushBox.main(null);
					}
					timer.cancel();
					frame.dispose();
				}
			}

		}, intervel, intervel);
	}

	/** ???????? */
	public void enterAction() {
		flyEnteredIndex++;
		if (flyEnteredIndex % 10 == 0) { // 100?????????????????--10*40
			FlyingObject obj = nextOne(); // ????????????????
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = obj;
		}
	}

	/** ????? */
	public void stepAction() {
		for (int i = 0; i < flyings.length; i++) { // ???????????
			FlyingObject f = flyings[i];
			f.step();
		}

		for (int i = 0; i < bullets.length; i++) { // ????????
			Bullet b = bullets[i];
			b.step();
		}
		hero.step(); // ?????????
	}

	/** ??????????? */
	public void flyingStepAction() {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			f.step();
		}
	}

	int shootIndex = 0; // ???????

	/** ??? */
	public void shootAction() {
		shootIndex++;
		if (shootIndex % 20 == 0) { // 300???????
			Bullet[] bs = hero.shoot(); // ????????
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length); // ????
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length,
					bs.length); // ???????
		}
	}

	/** ???????????????? */
	public void bangAction() {
		for (int i = 0; i < bullets.length; i++) { // ???????????
			Bullet b = bullets[i];
			bang(b,i); // ????????????????????
			bangBoss(b, i);
		}
	}

	/** ??????????Ｐ??? */
	public void outOfBoundsAction() {
		int index = 0; // ????
		FlyingObject[] flyingLives = new FlyingObject[flyings.length]; // ??????????
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if (!f.outOfBounds()) {
				flyingLives[index++] = f; // ??????????
			}
		}
		flyings = Arrays.copyOf(flyingLives, index); // ?????????????????

		index = 0; // ?????????0
		Bullet[] bulletLives = new Bullet[bullets.length];
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if (!b.outOfBounds()) {
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index); // ???????????????
	}

	/** ?????????? */
	public void checkGameOverAction() {
		if (isGameOver()==true) {
			state = GAME_OVER; // ?????
		}
	}
	
	public void checkSuccess() {
		int boss_x = 8;
		int boss_y = 5;
		
		if(score >= bossBlood) {
			state = SUCCESS;
			LvMap[0][boss_x][boss_y] = 0;
		}
	}

	/** ???????????? */
	public boolean isGameOver() {
		
		for (int i = 0; i < flyings.length; i++) {
			int index = -1;
			FlyingObject obj = flyings[i];
			if (hero.hit(obj)) { // ????????????????????
				hero.subtractLife(); // ????
				hero.setDoubleFire(0); // ??????????
				index = i; // ?????????????????
			}
			if (index != -1) {
				FlyingObject t = flyings[index];
				flyings[index] = flyings[flyings.length - 1];
				flyings[flyings.length - 1] = t; // ?????????????????????

				flyings = Arrays.copyOf(flyings, flyings.length - 1); // ?????????????
			}
		}
		
		return hero.getLife() <= 0;
	}

	/** ???????????????????? */
	public void bang(Bullet bullet, int bulletIndex) {
		int index = -1; // ???е??????????
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if (obj.shootBy(bullet)) { // ?ж???????
				index = i; // ????????е???????????
				break;
			}
		}
		if (index != -1) { // ?л??е??????
			FlyingObject one = flyings[index]; // ????????е??????

			FlyingObject temp = flyings[index]; // ?????е??????????????????????
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = temp;

			flyings = Arrays.copyOf(flyings, flyings.length - 1); // ???????????????(???????е?)

			Bullet Btemp = bullets[bulletIndex]; // ?????е???????????????????
			bullets[bulletIndex] = bullets[bullets.length - 1];
			bullets[bullets.length - 1] = Btemp;

			bullets = Arrays.copyOf(bullets, bullets.length - 1); // ????????????(???????е?)
			
			// ???one??????(??????????????)
			if (one instanceof Enemy) { // ??????????????????
				Enemy e = (Enemy) one; // ??????????
				score += e.getScore(); // ???
			} else { // ????????????????
				Award a = (Award) one;
				int type = a.getType(); // ???????????
				switch (type) {
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire(); // ???????????
					break;
				case Award.LIFE:
					hero.addLife(); // ???ü???
					break;
				}
			}
		}
	}

	public void bangBoss(Bullet bullet,int bulletIndex) {
		if(Tboss.shootBy(bullet)) {
			score += Tboss.getScore();
			Bullet Btemp = bullets[bulletIndex]; // ?????е???????????????????
			bullets[bulletIndex] = bullets[bullets.length - 1];
			bullets[bullets.length - 1] = Btemp;

			bullets = Arrays.copyOf(bullets, bullets.length - 1); // ????????????(???????е?)
		}
	}
	/**
	 * ????????????
	 * 
	 * @return ?????????
	 */
	public static FlyingObject nextOne() {
		Random random = new Random();
		int type = random.nextInt(20); // [0,20)
		if (type < 2) {
			return new Bee();
		} else {
			return new Airplane();
		}
	}
}