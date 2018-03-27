package core;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class Column {
	BufferedImage image;
	int x,y;//×ø±ê
	int width,heigth;//¿í¸ß
	Random ra=new Random();
	Column(int i) throws Exception{
		image=ImageIO.read(getClass().getResource("column.png"));
		width=image.getWidth();
	    heigth=image.getHeight();
	    x=500+245*(i-1);
	    y=-300-50*(i-1);
	}
	void step(){
		if(x<-width){
			x=432;
			y=-450+ra.nextInt(200);
		}
		x--;
	}

}
