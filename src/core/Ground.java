package core;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Ground {
	BufferedImage image;
	int x,y;//����
	int width,heigth;//���
	Ground() throws Exception{
		image=ImageIO.read(getClass().getResource("ground.png"));
		width=image.getWidth();
	    heigth=image.getHeight();
	    x=0;
	    y=498;
	}
void step(){
	if(x==-360)x=0;
	x--;
}
}
