import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;




public class SpriteAnimation extends Thread{
	private Sprite[] sprites = new Sprite[5];
	private int currentSprite = 0;
	public JLabel label;
	
	public SpriteAnimation(JLabel jl) {
		for (int i = 0; i < 5; i++) {
			sprites[i] = new Sprite();
		}
		label = jl;
	}


	public void render() {
		BufferedImage pic = sprites[currentSprite].getSprite(currentSprite+5,4);
		BufferedImage resizedImage = new BufferedImage(200, 200, BufferedImage.TYPE_3BYTE_BGR);
		label.setIcon(new ImageIcon(resizedImage));
		label.revalidate();
		label.repaint();
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(pic, 0, 0, 200, 200, null);
		g.dispose();
	
	}

	public void update() {
		
		currentSprite++;
		if (currentSprite == 4)
			currentSprite = 0;
		
	}
	public void run() {
		while (true) {
			render();
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			update();

		}
	}


	
}


