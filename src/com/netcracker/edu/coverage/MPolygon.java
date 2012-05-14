package com.netcracker.edu.coverage;

import java.awt.Polygon;
import java.io.Serializable;
import java.util.Arrays;

public class MPolygon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2577161675821624613L;
	
	double[] xcoords;
	double[] ycoords;
	int count;
	
	public MPolygon(){
		this(8);
	}

	public MPolygon(int points){
		xcoords = new double[points];
		ycoords = new double[points];
		count = 0;
	}

	public void add(double x, double y){
		if(count + 1 == xcoords.length){
			xcoords = Arrays.copyOf(xcoords, xcoords.length*2);
			ycoords = Arrays.copyOf(ycoords, ycoords.length*2);
		}
		
		xcoords[count] = x;
		ycoords[count] = y;
		
		count += 1;
	}

	public int count(){
		return count;
	}
	
	public  Polygon getAWTPolygon(){
		Polygon p = new Polygon();
		
		for(int i = 0; i < count; i++)
			p.addPoint((int)xcoords[i], (int)ycoords[i]);
		
		return p;
	}
	
	public double getX(int i){
		return xcoords[i];
	}
	
	public double getY(int i){
		return ycoords[i];
	}
	
	public void setX(int i ,double v){
		xcoords[i] = v;
	}
	
	public void setY(int i ,double v){
		ycoords[i] = v;
	}
	
	public void translateCoords(double maxlat, double minlat, double maxlon, double minlon, double maxHeight, double maxWidth){
		for(int i = 0; i < count; i++){
			double x = xcoords[i];
			double y = ycoords[i];
			
//			y /= maxSize;
//			x /= maxSize;
			
			y = maxHeight*(maxlat - y) / (maxlat - minlat);
			x = maxWidth*(x - minlon) / (maxlon - minlon);
			
			xcoords[i] = x;
			ycoords[i] = y;
		}
	}
}