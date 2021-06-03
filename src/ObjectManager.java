import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random rand = new Random();
	int score = 0;
	
	int getScore() {
		return score;
	}
	
	ObjectManager(Rocketship rs){
		rocket = rs;
	}
	
	void addProjectile(Projectile projectiles) {
		this.projectiles.add(projectiles);
	}
	
	void addAlien(){
		aliens.add(new Alien(rand.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	
	void update() {
		for(int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
			if(aliens.get(i).y>LeagueInvaders.HEIGHT) {
				aliens.get(i).isActive=false;
			}
		}
		
		for(int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
			if(projectiles.get(i).y>LeagueInvaders.HEIGHT) {
				projectiles.get(i).isActive=false;
			}
		}
		rocket.update();
		
		checkCollision();
		purgeObjects();
	}
	
	void draw(Graphics g) {
		rocket.draw(g);
		for(int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		for(int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
	}
	
	void purgeObjects(){
		for(int i = 0; i < aliens.size(); i++) {
			if(aliens.get(i).isActive==false) {
				aliens.remove(i);
			}
		}
		for(int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).isActive==false) {
				projectiles.remove(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}
	
	void checkCollision(){
		for(int i = 0; i < aliens.size(); i++) {
			if (rocket.collisionBox.intersects(aliens.get(i).collisionBox)) {
				aliens.get(i).isActive = false;
				rocket.isActive = false;
			}
			for(int a = 0; a < projectiles.size(); a++) {
				if (projectiles.get(a).collisionBox.intersects(aliens.get(i).collisionBox)) {
					aliens.get(i).isActive = false;
					score++;
					projectiles.get(a).isActive = false;
				}
			}
		}
	}
}
