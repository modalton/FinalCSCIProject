import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JLabel;



public class GamePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	Vector<Sprite> all_sprites = new Vector<Sprite>();
	

	
	public GamePanel(){
		super();
		
		JLabel label = new JLabel();
		add(label);
		SpriteAnimation sa = new SpriteAnimation(label);
		sa.start();
		//legacy
		/*
		Sprite spritefactory = new Sprite();
		BufferedImage pic = spritefactory.getSprite(2,4);
		BufferedImage resizedImage = new BufferedImage(200, 200, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(pic, 0, 0, 200, 200, null);
		for (int i = 0; i < 10; i++) {
			
		}
		g.dispose();
		JLabel picLabel = new JLabel(new ImageIcon(resizedImage));
		add(picLabel);
		*/
		

		
	}
	
	
}
