package com.netcracker.edu.coverage;

import java.io.Serializable;

public class Bounds implements Serializable {
	private static final long serialVersionUID = -3034721765952662470L;
	
	private double minlat;
	private double maxlat;
	private double minlon;
	private double maxlon;
	
	public Bounds(){
	}
	
	public Bounds(double minlat, double minlon, double maxlat, double maxlon){
		this.minlat = minlat;
		this.minlon = minlon;
		this.maxlat = maxlat;
		this.maxlon = maxlon;
	}

	public final boolean contains(double x, double y){
		return (x >= minlon && y >= minlat &&
				x < maxlon && y < maxlat);
	}
	public final boolean contains(Pnt p){
		return contains(p.getX(), p.getY());
	}
	public final double getWidth(){
		return maxlon - minlon;
	}
	public final double getHeight(){
		return maxlat - minlat;
	}
	
	public double getMinlat() {
		return minlat;
	}
	public void setMinlat(double minlat) {
		this.minlat = minlat;
	}
	public double getMaxlat() {
		return maxlat;
	}
	public void setMaxlat(double maxlat) {
		this.maxlat = maxlat;
	}
	public double getMinlon() {
		return minlon;
	}
	public void setMinlon(double minlon) {
		this.minlon = minlon;
	}
	public double getMaxlon() {
		return maxlon;
	}
	public void setMaxlon(double maxlon) {
		this.maxlon = maxlon;
	}
}
