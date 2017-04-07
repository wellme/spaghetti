package utils;

import java.awt.Color;

public abstract class Colors {

	public abstract RGB toRGB();
	public abstract HSV toHSV();
	
	public final Color toColor() {
		return new Color(toRGB().toInt());
	}
	
	
	public static class HSV extends Colors{
		
		private final double hue;
		private final double saturation;
		private final double value;
		
		public HSV(double hue, double saturation, double value){
			this.hue = hue;
			this.saturation = saturation;
			this.value = value;
		}
		
		public HSV toHSV(){
			return this;
		}
		
		public RGB toRGB(){
			if(saturation > 1 || value > 1 || saturation < 0 || value < 0)
				return new RGB(0);
			double hue = (Math.toDegrees(this.hue)%360+360)%360;
			double c = saturation*value;
			double h = hue/60;
			double x = c*(1-Math.abs(h%2-1));
			double r, g, b;
			switch((int)h){
				case 0: r = c; g = x; b = 0; break;
				case 1: r = x; g = c; b = 0; break;
				case 2: r = 0; g = c; b = x; break;
				case 3: r = 0; g = x; b = c; break;
				case 4: r = x; g = 0; b = c; break;
				case 5: r = c; g = 0; b = x; break;
				
				default:r = 0; g = 0; b = 0;
			}
			double m = value-c;
			r += m;
			g += m;
			b += m;
			return new RGB(r, g, b);
		}
		
	}
	
	public static class RGB extends Colors{
		
		private final int rgb;
		private final short r;
		private final short g;
		private final short b;
		
		public RGB toRGB(){
			return this;
		}
		
		public RGB(int rgb){
			this.rgb = rgb;
			r = (short) (rgb >> 16);
			g = (short) ((rgb >> 4) % (1 << 4));
			b = (short) (rgb % (1 << 4));
		}
		
		public RGB(int r, int g, int b){
			this((short) r, (short) g, (short) b);
		}
		public RGB(short r, short g, short b){
			this.r = r;
			this.g = g;
			this.b = b;
			this.rgb = (r << 16) + (g << 8) + b;
			//System.out.printf("rgb%h\tr%h\tg%h\tb%h%n", (r << 16) + (g << 8) + b, r << 16, g << 8, b);
		}
		public RGB(double r, double g, double b){
			this((short) (r*255), (short) (g*255), (short) (b*255));
		}
		
		public String toString(){
			return String.format("[%s, %s, %s]", r, g, b);
		}
		
		public int toInt(){
			return rgb;
		}
		
		//TODO
		public HSV toHSV() {
			float[] hsv = Color.RGBtoHSB(r, g, b, null);
			//System.out.println(hsv);
			return new HSV(hsv[0]*Math.PI*2, hsv[1], hsv[2]);
		}
	}
}
