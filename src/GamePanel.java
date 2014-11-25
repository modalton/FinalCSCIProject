import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.*;


public class GamePanel extends JPanel{
	Vector<Sprite> all_sprites = new Vector<Sprite>();
	
	GamePanel(){
		super();
		
		Sprite spritefactory = new Sprite();
		
		BufferedImage pic = spritefactory.getSprite(2,4);
		BufferedImage resizedImage = new BufferedImage(200, 200, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(pic, 0, 0, 200, 200, null);
		g.dispose();
		JLabel picLabel = new JLabel(new ImageIcon(resizedImage));
		add(picLabel);
		
	}
	
	
	public static void main(String[] args){
		JFrame temp = new JFrame();
		temp.add(new GamePanel());
		temp.setSize(300,200);
		temp.setVisible(true);
		temp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
