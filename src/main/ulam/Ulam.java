package main.ulam;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import math.number.Complex;
import utils.Colors;

public class Ulam extends JPanel implements MouseListener,KeyListener{
	public static JFrame frame = new JFrame();
	public static BufferStrategy strategy;
	
	public static boolean toggle = false;
	
	
	
	public static int sizeX = 720;
	public static int sizeY = 720;
	public static double posX = 0;
	public static double posY = 0;
	public static double zoom = Math.pow(2, 0);
	
	public static final boolean useFullMonitor = false;
	
	
	public static final boolean DOSTRATEGY = true;
	public static final boolean DOSTRING = false;
	public static final boolean DECORATED = false;
	public static final boolean SAVE = false;
	public static int counter = 1;
	static long delay = 100000000l;
	
	public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int SIZEX = useFullMonitor ? screenSize.width : sizeX;
	public static final int SIZEY = useFullMonitor ? screenSize.height : sizeY;
	public static boolean requestUpdate = false;
	
	public int id;
	public static double[] values = new double[64];
	public static int mode = 0;
	
	
	public Ulam(int n){
		id = n;
	}
	
	public static Color[][] data = new Color[SIZEX][SIZEY];
	private Info[] filters = {
			(x,y,n,xRaw,yRaw) -> {
				Complex init = new Complex(x, y);
				Complex num = new Complex(x, y);
				for(int i = 0; i < id; i++){
					num = num.mul(num).add(init);
					if(num.getR() > 2){
						return i*50;
					}
				}
				return 0;
			},
			(x,y,n,xRaw,yRaw) -> {
				return new Colors.HSV(Math.atan2(y, x), 1d, 1d).toRGB().toHSV().toRGB().toInt();
			},
			(x,y,n,xRaw,yRaw) -> {
				return (1l<<id)*positiveAtan2(Math.floor(y),Math.floor(x))%(Math.PI*2) < 2 ? 0 : 0xffffff;
			},
			(x,y,n,xRaw,yRaw) -> {
				Complex num = new Complex(x,y).pow(new Complex(id,0)).sub(new Complex(1,0));
				return HSVtoRGB(num.getPhi(),1,Math.exp(-num.getR()/10000));
			},
			(x,y,n,xRaw,yRaw) -> {
				Complex num = new Complex(x,y);
				num = num.pow(new Complex(0,1).exp().sub(num.pow(new Complex(0,-1)).exp()));
				return HSVtoRGB(num.getPhi(),1,Math.exp(-num.getR()/128));
			},
			(x,y,n,xRaw,yRaw) -> {
				return (id*positiveAtan2(y,x))%(Math.PI*2) < 2 ? 0 : 0xffffff;
			},
			(x,y,n,xRaw,yRaw) -> {
				return isPrime(n) ? 0x000000 : 0xffffff;
			},

			(x,y,n,xRaw,yRaw) -> {
				Complex num = new Complex(0,0);
				Complex c = new Complex(x,y);
				for(int i = 0; i < id; i++){
					if(num.getR() > 2)
						return HSVtoRGB(i/10d,1,1);
					num = num.mul(num).add(c);
				}
				return 0;
			},
			
			new KMean(50, 5),
			
			(x,y,n,xRaw,yRaw) -> {
				return 0xffffff;
			},
	};
	
	
		//if(Math.abs((xRaw-SIZEX/2)*(yRaw-SIZEY/2)/2500) > 0.9 && Math.abs((xRaw-SIZEX/2)*(yRaw-SIZEY/2)/2500) < 1)
			//return 0xff0000;
	public static String[] renderString = new String[SIZEY/10];
	
	public static void main(String[] args) throws Exception{
		System.out.println(screenSize);
		if(DOSTRING)
			for(int i = 0;i<300;i++)
				for(int j = 0;j<SIZEY/10;j++)
					if(renderString[j] != null)
						renderString[j] += (char)(Math.random()*26+97);
					else
						renderString[j] = ""+(char)(Math.random()*26+97);

		frame.setUndecorated(!DECORATED);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setPreferredSize(useFullMonitor ? screenSize : new Dimension(sizeX, sizeY));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Ulam m = new Ulam(counter);
		frame.addMouseListener(m);
		frame.addKeyListener(m);
		if(DOSTRATEGY){
			frame.createBufferStrategy(2);
			strategy = frame.getBufferStrategy();
		}
		while(true){
			m = new Ulam(counter);
			long tempTime = System.currentTimeMillis();
			frame.add(m);

			//frame.pack();
			m.filters[mode].generate();
			frame.pack();
			
			if(SAVE){
				Robot robot = new Robot();
				
				BufferedImage bi = robot.createScreenCapture(new Rectangle(0,0,SIZEX,SIZEY));
				File file = new File("C:\\Users\\Escoto\\Desktop\\image.png");
				if(file.exists())
					file.delete();
				ImageIO.write(bi,"png",file);
				System.out.println("saved");
			}
			frame.remove(m);
		}
		
	}
	public static void doStrategy(Ulam m){
		do{
			do{
				Graphics g = strategy.getDrawGraphics();
				m.paintComponent(g);
				g.dispose();
			}
			while(strategy.contentsRestored());
			strategy.show();
		}
		while(strategy.contentsLost());
	}
	
	public void paintComponent(Graphics g){
		
		filters[mode].draw((Graphics2D) g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 50, 30);
		g.setColor(Color.BLACK);
		g.drawString("id : "+id, 0, 10);
		g.drawString("z : "+zoom, 0, 20);
		g.drawString("mode : "+mode, 0, 30);
		g.setColor(new Color(id*0x010101));
		if(DOSTRING)
			for(int i = 0;i<renderString.length;i++)
				g.drawString(renderString[i], 0, i*10);
		g.drawLine(0, SIZEY/2, SIZEX, SIZEY/2);
		g.drawLine(SIZEX/2, 0, SIZEX/2, SIZEY);
	}
	
	public static int toN(double x, double y){
		if(y == -x && y > 0)
			return (int) (4*y*y);
		if(-y < -x && -x < y)
			return (int) (y*(4*y-2)+y-x);
		if(x < y && y < -x)
			return (int) (x*(4*x-2)+x-y);
		if(-x < y && y < x)
			return (int) (x*(4*x-2)-x+y);
		return (int) (y*(4*y-2)-y+x);
	}
	public static double centerX(int x){
		return (x+posX-SIZEX/2)*zoom;
	}
	public static double centerY(int y){
		return (-(y+posY)+SIZEY/2)*zoom;
	}
	
	public static boolean isPrime(long n){
		if(n == 1 || n != 2 && n%2 == 0)
			return false;
		for(long i = 3;i*i<=n;i++)
			if(n%i == 0)
				return false;
		return true;
	}

	public void mouseClicked(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseExited(MouseEvent event){}

	public void mousePressed(MouseEvent event) {
		//System.out.println(e);
		int x = event.getX();
		int y = event.getY();
		double relativeX = (x-SIZEX/2+posX)*zoom;
		double relativeY = (-y+SIZEY/2+posY)*zoom;
		double angle = positiveAtan2(relativeX,relativeY);
		double r = Math.sqrt(relativeX*relativeX+relativeY*relativeY);
		System.out.printf("(%s,%s) -> (%s,%s), angle : %s (%s), r = %s%n",x,y,relativeX,relativeY,angle,Math.toDegrees(angle),r);
	}
	
	boolean typingMode = false;
	boolean smallIncrement = false;
	String number = "";

	public void mouseReleased(MouseEvent event){}
	public void keyReleased(KeyEvent event){
		switch(event.getKeyCode()){
			case KeyEvent.VK_CONTROL:smallIncrement = false;	break;
			case KeyEvent.VK_ALT :
				try{
					typingMode = false;
					counter = Integer.parseInt(number);
					number = "";
					break;
				}
				catch(NumberFormatException e){
					number = "";
					break;
				}
		}
	}
	double small = Math.pow(2,1d/(1<<4));
	public void keyTyped(KeyEvent event){}
	public void keyPressed(KeyEvent event){
		switch(event.getKeyCode()){
			case KeyEvent.VK_CONTROL:smallIncrement = true;				break;
			case KeyEvent.VK_ALT: 	typingMode = true;					break;
			case KeyEvent.VK_ESCAPE:System.exit(0);						break;
			case KeyEvent.VK_LEFT:	counter--;							break;
			case KeyEvent.VK_RIGHT:	counter++;							break;
			case KeyEvent.VK_UP:	zoom /= smallIncrement ? small : 2;	break;
			case KeyEvent.VK_DOWN:	zoom *= smallIncrement ? small : 2;	break;
			case KeyEvent.VK_W:		posY -= zoom*SIZEX/8;				break;
			case KeyEvent.VK_A:		posX -= zoom*SIZEY/8;				break;
			case KeyEvent.VK_S:		posY += zoom*SIZEY/8;				break;
			case KeyEvent.VK_D:		posX += zoom*SIZEX/8;				break;
			case KeyEvent.VK_Q:		mode++;								break;
			case KeyEvent.VK_E:		mode--;								break;
			case KeyEvent.VK_BACK_SPACE:
				posX = 0;
				posY = 0;
				zoom = 1;
				id = 0;
				break;
			
			default:
				if(typingMode && '0' <= event.getKeyChar() && event.getKeyChar() <= '9')
					number += event.getKeyChar();
		}
	}
	public static int HSVtoRGB(double hue, double s, double v){
		if(s > 1 || v > 1 || s < 0 || v < 0)
			return 0;
		hue = (Math.toDegrees(hue)%360+360)%360;
		double c = s*v;
		double h = hue/60;
		double x = c*(1-Math.abs(h%2-1));
		double r,g,b;
		switch((int)h){
			case 0: r = c; g = x; b = 0; break;
			case 1: r = x; g = c; b = 0; break;
			case 2: r = 0; g = c; b = x; break;
			case 3: r = 0; g = x; b = c; break;
			case 4: r = x; g = 0; b = c; break;
			case 5: r = c; g = 0; b = x; break;
			
			default:r = 0; g = 0; b = 0;
		}
		double m = v-c;
		r += m;
		g += m;
		b += m;
		return (int)(r*255)*0x010000+(int)(g*255)*0x000100+(int)(b*255)*0x000001;
	}
		
	public static double positiveAtan2(double y, double x){
		double val = Math.atan2(y,x);
		if(val < 0)
			return val+Math.PI*2;
		return val;
	}
}
