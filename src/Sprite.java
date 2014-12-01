
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

    private BufferedImage spriteSheets;
    private boolean isBatter;
    private boolean alreadyDone = false; 
    
    public Sprite (boolean isBatter){
    	this.isBatter = isBatter;
    }

    public BufferedImage loadSprite() {

        BufferedImage sprites = null;
    	/*BufferedImage spriteBat = null;
        BufferedImage spritePitch = null;
*/
        try {
        	String current_directory = System.getProperty("user.dir");
        	if (isBatter){
        		sprites = ImageIO.read(new File("src/Images/RBIBaseball94Sheet4.gif"));
        	} else {
        		sprites = ImageIO.read(new File("src/Images/RBIBaseball94Sheet1.gif"));
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
	        if (xGrid >= 4){
	        	xGrid = xGrid - 4;
	        	alreadyDone = true;
	        }
	        
	        if (xGrid >= 0 && xGrid < 4 && yGrid <= 1){
	        	if (yGrid == 1){
	        		return spriteSheets.getSubimage(414+xGrid*38, 164 + yGrid*90, 30, 88);
	        	} else
	        	return spriteSheets.getSubimage(414+xGrid*38, 168 + yGrid*90, 30, 90);
	        }
	        else {
	        	alreadyDone = false;
	        	try {
	        		if (xGrid < 7) {
	        			return spriteSheets.getSubimage(435+xGrid*37, 153 + yGrid*90,32, 90);
	        		}
	        		else {
	        			return spriteSheets.getSubimage(435+xGrid*37, 153 + yGrid*90,32, 90);
	        		}
	        	}
	        	catch (Exception e) {
	        		System.out.println("PROBLEM: (x,y) " + "(" + xGrid + "," + yGrid + ")" );
	        		return null;
	        	}
	        }
        } else{
        	 if (xGrid >= 13){
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
 	        	try {
 	        		return spriteSheets.getSubimage(382+xGrid*27, 155 + yGrid*60, 27, 50);
 	        	}
 	        	catch (Exception e) {
	        		System.out.println("PITCHER: " + "PROBLEM: (x,y) " + "(" + xGrid + "," + yGrid + ")" );
	        		return null;
 	        	}
 	        }
        }
    }

}



