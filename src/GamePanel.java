import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font subtextFont;
	Timer frameDraw;
	Rocketship rs = new Rocketship(250,700,50,50);
	ObjectManager obj = new ObjectManager(rs);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Timer alienSpawn;
	
	void startGame() {
		alienSpawn = new Timer(1000, obj);
		alienSpawn.start();
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
	void updateMenuState(){
		
	}
	void updateGameState(){
		obj.update();
		if(rs.isActive == false) {
			currentState = END;
		}
		
	}
	void updateEndState(){
	
	}
	
	GamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 48);
		subtextFont = new Font("Arial", Font.PLAIN, 30);
		frameDraw = new Timer(1000/60,this);
	    frameDraw.start();
	    if (needImage) {
	        loadImage ("space.png");
	    }
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(currentState==MENU) {
			drawMenuState(g);
		}
		else if(currentState==GAME) {
			drawGameState(g);
		}
		else if(currentState==END) {
			drawEndState(g);
		}
	}
	
	void drawMenuState (Graphics g){
		g.setColor(Color.blue);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.yellow);
		g.drawString("LEAGUE INVADERS", 15, 350);
		g.setFont(subtextFont);
		g.drawString("Use arrow keys to move and", 50, 410);
		g.drawString("space to shoot", 130, 440);
		g.drawString("PRESS ENTER TO START", 50, 500);
	}
	void drawGameState (Graphics g){
		
		if (gotImage) {
			g.drawImage(image, 0, 0, 500, 800, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 500, 800);
		}
		obj.draw(g);
		String s = Integer.toString(obj.getScore());
		g.setFont(titleFont);
		g.setColor(Color.yellow);
		g.drawString(s, 25, 50);
	}

	void drawEndState (Graphics g){
		g.setColor(Color.red);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.yellow);
		g.drawString("GAME OVER", 100, 400);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(currentState == END) {
				currentState = MENU;
			}
			else {
				currentState++;
			}
			if(currentState == GAME) {
				startGame();
			}
			if(currentState == END) {
				alienSpawn.stop();
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			if(rs.y < 800) {
				rs.up();
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			if(rs.y < 800) {
				rs.down();
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(rs.x > 0) {
				rs.left();
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(rs.x < 500) {
				rs.right();
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(currentState == GAME) {
				obj.addProjectile(rs.getProjectile());
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
