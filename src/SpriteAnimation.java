import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;




public class SpriteAnimation extends Thread{
	private Sprite[] sprites;
	private int xSprite = 0, ySprite = 0;
	public JLabel label;
	boolean isBatter;
	
	public SpriteAnimation(JLabel jl, boolean isBatter) {
		this.isBatter = isBatter;
		if (isBatter){ //Loading batter animation
			sprites = new Sprite[14];
			for (int i = 0; i < 14; i++) {
				sprites[i] = new Sprite(isBatter);
			}
		} else{ //Loading pitcher animation
			sprites = new Sprite[23];
			for (int i = 0; i < 23; i++) {
				sprites[i] = new Sprite(isBatter);
			}
		}
		label = jl;
	}


	public void render() {
		BufferedImage pic = sprites[xSprite].getSprite(xSprite,ySprite);
		BufferedImage resizedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
		
		//Make the sprite transparent
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = environment.getDefaultScreenDevice();
		GraphicsConfiguration config = device.getDefaultConfiguration();
		resizedImage = config.createCompatibleImage(200, 200, Transparency.BITMASK);
		
		
		
		label.setIcon(new ImageIcon(resizedImage));
		label.revalidate();
		label.repaint();
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(pic, 0, 0, 200, 200, null);
		g.dispose();
	}

	public void update() {
		if (isBatter){
			xSprite++;
			if (xSprite == 4 && ySprite == 0){
				//xSprite = 0;
				ySprite++;
			}
			if (xSprite == 14 && ySprite == 1){
				xSprite = 0;
				ySprite = 0;
			}
		} else{
			xSprite++;
			if (xSprite == 13 && ySprite == 0){
				//xSprite = 0;
				ySprite++;
			}
			if (xSprite == 22 && ySprite == 1){
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				xSprite = 0;
				ySprite = 0;
				
			}
		}
	}
	public void run() {
		while (true) {
			for (int i = 0; i < sprites.length; i++){
				render();
				try {
					sleep(70);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				update();
	
			}
		}
	}


	
}


