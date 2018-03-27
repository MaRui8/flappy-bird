package core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BirdGame {
	public static void main(String[] args) throws Exception {
		JFrame J=new JFrame("FlappyBird");
		J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭按钮的动作默认为退出
		//设置窗体初步尺寸，后期调整
		J.setSize(432,644);
		MyPanel1 mp=new MyPanel1();
		J.add(mp);
		Dimension displaySize=Toolkit.getDefaultToolkit().getScreenSize();//获得显示器大小对象
		Dimension frameSize = J.getSize(); //窗口大小
		//窗口大小不能大于显示器大小
		if(frameSize.width > displaySize.width){
			frameSize.width=displaySize.width;
		}
		if (frameSize.height > displaySize.height){
			frameSize.height=displaySize.height;
		}
		J.setLocation((displaySize.width - frameSize.width) / 2 ,(displaySize.height - frameSize.height) / 2);//居中显示器显示
		//设置窗体的可见性，要求最后显示
//		J.setUndecorated(true);//将边框变为不可见。
		J.setVisible(true);
		mp.action();
		
	}

}
@SuppressWarnings("serial")
class MyPanel1 extends JPanel 
{
	BufferedImage bg;
	Bird bird;
	Ground ground;
	Column column01;
	Column column02;
	int z;
	BufferedImage gameover;
	BufferedImage gamestart;
	boolean start=false;
	static boolean over=false;
	private int ThreadTime=1000;
	
	MyPanel1() throws Exception
	{
		bg=ImageIO.read(getClass().getResource("bg.png"));
		gameover=ImageIO.read(getClass().getResource("gameover.png"));
		gamestart=ImageIO.read(getClass().getResource("start.png"));
		bird=new Bird();
		ground=new Ground();
		column01=new Column(1);
		column02=new Column(2);
}
	public void paint(Graphics g)
	{
		g.drawImage(bg, 0,0, null);
		
		Graphics2D g2d=(Graphics2D) g;
		g2d.rotate(-bird.alpha, bird.x, bird.y);
		g.drawImage(bird.image, bird.x-bird.width/2-10,bird.y-bird.heigth/2, null);
		g2d.rotate(bird.alpha, bird.x, bird.y);
		
		
		if(bird.x==column01.x||bird.x==column02.x)
		{
			bird.score++;
			Toolkit.getDefaultToolkit().beep();
		}
		g.drawImage(column02.image,column01.x,column01.y, null);
		g.drawImage(column01.image, column02.x,column02.y, null);
		g.drawImage(ground.image, ground.x,ground.y, null);
		if(/*bird.state==0*/!start)
			g.drawImage(gamestart,0,0,null);
		if(over)
			g.drawImage(gameover,0,0,null);
		bird.getScore(g);
	}
	
	void action() throws InterruptedException{//mp.action();调用
		
				MouseListener mouse=new MouseAdapter()
				{
					@Override
					public void mousePressed(MouseEvent e)
					{
						bird.flappy();
						if(over)
						{
							try 
							{
								start=false;
								over=false;
								bird=new Bird();
								ground=new Ground();
								column01=new Column(1);
								column02=new Column(2);
								bird.score=0;
							} catch (Exception e1) 
							{
								e1.printStackTrace();
							}
						}
						else{
							start=true;
						}
							
					}
				};	
					addMouseListener(mouse);
				while (true)
				{
				//加入鼠标监听事件，Jpanel定义的方法，继承后
					//方法的调用
					if(start&&!over)
					{
						ground.step();
						column01.step();
						column02.step();
						bird.step();
					}
					if(bird.hit(ground)||bird.hit(column02)||bird.hit(column01))
					{
						over=true;
						if(ReadAndWrite.getScore()<bird.score)
							ReadAndWrite.setScore(bird.score);
					}
					else 
					{
						over=false;
						bird.birdFly();
					}
					repaint();
		            Thread.sleep((ThreadTime-bird.score*5)/55);//时间是（毫秒值）
			}
		}
	}
