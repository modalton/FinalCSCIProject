import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

    private static BufferedImage spriteSheet;
     

    public static BufferedImage loadSprite() {

        BufferedImage sprite = null;

        try {
        	String current_directory = System.getProperty("user.dir");
            sprite = ImageIO.read(new File( "src/Images/BaseballSheet1.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite();
        }

        return spriteSheet.getSubimage(xGrid *24 + 140, yGrid*112 + 50, 25, 40);
    }

}



