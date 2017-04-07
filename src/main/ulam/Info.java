package main.ulam;

import java.awt.Color;
import java.awt.Graphics2D;

public interface Info {
	
	public int call(double x, double y, int n, double xRaw, double yRaw);
	
	
	public default void generate(){
		for(int x = 0;x<Ulam.SIZEX;x++)
			for(int y = 0;y<Ulam.SIZEY;y++)
				Ulam.data[x][y] = new Color(call(Ulam.centerX(x),Ulam.centerY(y),Ulam.toN(Ulam.centerX(x),Ulam.centerY(y)),x,y));
	}
	
	public default void draw(Graphics2D g){
		try{
			for(int x = 0;x<Ulam.SIZEX;x++)
				for(int y = 0;y<Ulam.SIZEY;y++){
					g.setColor(Ulam.data[x][y]);
					g.drawRect(x, y, 1, 1);
				}
		} catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Whoops");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
