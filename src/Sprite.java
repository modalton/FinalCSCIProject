
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

    private static BufferedImage spriteSheets;
    static boolean isBatter;
    boolean alreadyDone = false; 
    
    public Sprite (boolean isBatter){
    	this.isBatter = isBatter;
    }

    public static BufferedImage loadSprite() {

        BufferedImage sprites = null;
    	/*BufferedImage spriteBat = null;
        BufferedImage spritePitch = null;
*/
        try {
        	String current_directory = System.getProperty("user.dir");
        	if (isBatter){
        		sprites = ImageIO.read(new File("AAFP/src/Images/RBIBaseball94Sheet4.gif"));
        	} else {
        		sprites = ImageIO.read(new File("AAFP/src/Images/RBIBaseball94Sheet1.gif"));
        	}
            /*sprites[0] = spriteBat;
            sprites[1] = spritePitch;*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprites;
    }

    public BufferedImage getSprite(int xGrid, int yGrid) {
        if (spriteSheets == null) {
            spriteSheets = loadSprite(); //Just changed it to an array of bufferedImages
        }

        if (isBatter){
	        if (xGrid >= 4 && !alreadyDone){
	        	xGrid = xGrid - 4;
	        	alreadyDone = true;
	        }
	        
	        if (xGrid >= 0 && xGrid < 4 && yGrid <= 1){
	        	if (yGrid == 1){
	        		return spriteSheets.getSubimage(414+xGrid*38, 153 + yGrid*90, 30, 90);
	        	} else
	        	return spriteSheets.getSubimage(414+xGrid*38, 165 + yGrid*90, 30, 90);
	        }
	        else {
	        	alreadyDone = false;
	        	return spriteSheets.getSubimage(435+xGrid*38, 153 + yGrid*90, 30, 90);
	        }
        } else{
        	 if (xGrid >= 13 && !alreadyDone){
 	        	xGrid = xGrid - 13;
 	        	alreadyDone = true;
 	        }
 	        
        
 	        if (xGrid >= 0 && xGrid < 13 && yGrid <= 1){
 	        	if (yGrid == 1){
 	        		if (xGrid >= 8){
 	        			return spriteSheets.getSubimage(382+xGrid*29, 155 + yGrid*50, 32, 60);
 	        		} else
 	        			return spriteSheets.getSubimage(382+xGrid*32, 155 + yGrid*50, 28, 50);
 	        	} else
 	        		return spriteSheets.getSubimage(382+xGrid*27, 155 + yGrid*60, 27, 50);
 	        }
 	        else {
 	        	alreadyDone = false;
	        		return spriteSheets.getSubimage(382+xGrid*27, 155 + yGrid*60, 27, 50);
 	        }
        }
    }

}



