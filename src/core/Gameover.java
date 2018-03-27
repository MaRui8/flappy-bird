package core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Gameover {
     BufferedImage gameover;
	
 

void MyPanel() throws IOException{
	gameover=ImageIO.read(getClass().getResource("gameover.png"));
}
 void paint(Graphics g){
	 g.drawImage(gameover, 0, 0, null);
}
}