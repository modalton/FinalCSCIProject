import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class GameCard extends JPanel {
	BufferedImage background = null;
	BufferedImage test;
	public GameCard() {
		super();
		getBG();
	}
	
	public GameCard(BorderLayout borderLayout) {
		// TODO Auto-generated constructor stub
		super(borderLayout);
		getBG();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(background, 0, 0, null);
		
		
	}
	
	private BufferedImage getBG() {
        try {
        	background = ImageIO.read(new File("src/Images/background_diamond.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return background;
	}


	
}
