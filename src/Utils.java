import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Utils {
	public static Image getScaledImage(Image image, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(image, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}

	public static BufferedImage makeBackground() {
		
		BufferedImage image = new BufferedImage(Board.width, Board.height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.createGraphics();
		
		g.setColor(Color.darkGray);
		
		g.fillRect(0, 0, Board.width, Board.height);
		
		g.setColor(Color.white);

		for(int i = 0; i < Board.files; i++) {
			for(int j = 0; j < Board.ranks; j++) {
				if((i+j)%2==0)g.fillRect(i*Board.xW, j*Board.yH, Board.xW, Board.yH);
			}
		}	
		
//		try {
//			ImageIO.write(image, "png", new File("background.png"));
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
		
		return image;
	}
	
}
