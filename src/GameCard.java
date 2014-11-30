import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GameCard extends JPanel {

	public GameCard() {
		super();
	}
	
	public GameCard(BorderLayout borderLayout) {
		// TODO Auto-generated constructor stub
		super(borderLayout);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		BufferedImage bg = getBG();
		g.drawImage(bg, 0, 0, null);
		
	}
	
	private BufferedImage getBG() {
		BufferedImage background = null;
        try {
        	background = ImageIO.read(new File("src/Images/background_diamond.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return background;
	}


	
}
