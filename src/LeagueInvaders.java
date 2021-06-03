import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	static final int WIDTH = 500;
	static final int HEIGHT = 800;
	
	GamePanel gp;
	
	LeagueInvaders(){
		frame = new JFrame();
		gp = new GamePanel();
	}
	
	public static void main(String[] args) {
		
		LeagueInvaders inv = new LeagueInvaders();
		inv.setup();
		
	}
	
	void setup() {
		frame.addKeyListener(gp);
		frame.add(gp);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
