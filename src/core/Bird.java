package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

public class Bird {
	BufferedImage image;
	BufferedImage bird0,bird1,bird2,bird3,bird4,bird5,bird6,bird7;
	String [] bufferedImageName=new String [8];
	BufferedImage [] bird=new BufferedImage[8];
	int x,y;//坐标
	int size=40;//尺寸
    int width,heigth;//宽高
    private int birdImage=0;
    private int index=0,index2=0;
    public int score=0;
    double g=9.8;//重力加速度
    double t=0.25;//两次位置的间隔时间
    double v0=20;//初始上抛速度
    double speed=v0;//是当前的上抛速度
    double s;//经过t时间以后的位移
    public double alpha;
    int fly=0;

	
    Bird() throws Exception{
	    image=ImageIO.read(getClass().getResource("0.png"));
	    for(int i=0;i<8;i++)
	    {
	    	bufferedImageName[i]=i+".png";
	    	bird[i]=ImageIO.read(getClass().getResource(bufferedImageName[i]));
	    	
	    }
	    /*
	    bird0=ImageIO.read(getClass().getResource("0.png"));
		bird1=ImageIO.read(getClass().getResource("1.png"));
		bird2=ImageIO.read(getClass().getResource("2.png"));
		bird3=ImageIO.read(getClass().getResource("3.png"));
		bird4=ImageIO.read(getClass().getResource("4.png"));
		bird5=ImageIO.read(getClass().getResource("5.png"));
		bird6=ImageIO.read(getClass().getResource("6.png"));
		bird7=ImageIO.read(getClass().getResource("7.png"));
		*/
	    width=image.getWidth();
	    heigth=image.getHeight();
	    x=170;
	    y=260;
    }
    
    
    public void setScore(int score)
    {
    	String s=""+score;
    	try
    	{
    		PrintWriter out=new PrintWriter("score.txt");
    		out.print(s);
    		out.close();
    	}
    	catch (Exception ex)
    	{
    		System.out.print(ex);
    	}
    	
    }
    
    public void getScore(Graphics g)
    {
    	Font font=new Font(Font.SANS_SERIF,20,50);
    	g.setFont(font);
    	g.setColor(Color.white);
    	String s=Integer.toString(score);
    	g.drawString("分数："+s+" 最高分："+ReadAndWrite.getScore(),0,100);
    }
    
	
    void step(){
    	double v0=speed;
    	s=v0*t-g*t*t*0.7;
    	y-=(int)s;
    	speed=v0-g*t;
    	alpha=Math.atan(s/32);
    }
    
    void flappy(){
    	speed=35;
    }
   
    public int getBirdImage()
    {
    	return birdImage;
    }
    
    public void birdFly()
    {
    	
    	if(index==0)
    		image=bird[0];
    	if(index==1)
    		image=bird[1];
    	if(index==2)
    		image=bird[2];
    	if(index==3)
    		image=bird[3];
    	if(index==4)
    		image=bird[4];
    	if(index==5)
    		image=bird[5];
    	if(index==6)
    		image=bird[6];
    	if(index==7)
    		image=bird[7];
    	index2++;
    	if(index2%5==0)
    		index=birdImage++%8;
    }
    
    boolean hit(Ground g){
    	if (y>498-width/2){
			//System.out.println("撞地");
    		return true;
    	}
    	return false;
    }
    
   boolean hit(Column c){
    	if ((x+size/2-10>c.x&&
    			x-size/2-10<c.x+c.width)
    			&&(y-size/2<c.y+c.heigth/2-75
    					||y+size/2>c.y+c.heigth/2+75))
    	{
    	///	System.out.println("撞柱子");
    		return true;
    	}
    	return false;
    }
   
 }
