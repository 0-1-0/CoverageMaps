package com.netcracker.edu.coverage.scalarfield;

import java.util.List;

import com.netcracker.edu.coverage.Bounds;
import com.netcracker.edu.coverage.Building;

public class CreatePicture
{

	public double conductivity = 15;
	// Radius = conductivity; 
	public double x, y, width, height;
	//private double sizeMult;
	public double hMult, wMult;
	private double dw;
//	private ArrayList<Presense> pops = new ArrayList<Presense>();
	private List<Building> buildings;
	
	public CreatePicture()
	{
	}
	public CreatePicture(Bounds bounds, double size)
	{
		setBounds(bounds, size);
	}
	public CreatePicture(double width, double height, double size)
	{
		this.x = 0;
		this.y = 0;
		this.width = width;
		this.height = height;
//		sizeMult = height < width ? width / size : height / size;
		this.wMult = size / width;
		this.hMult = size / height;
	}
	
	
	public boolean setSize(double x, double y, double width, double height)
	{
		if(width <= 0 || height <= 0)
			return false;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		return true;
	}
	public void setBounds(Bounds bounds, double size)
	{
		this.x = bounds.getMinlon();
		this.y = bounds.getMinlat();
		this.width = bounds.getWidth();
		this.height = bounds.getHeight();
//		sizeMult = bounds.height < bounds.width ? bounds.width / size : bounds.height / size;
		this.wMult = size / width;
		this.hMult = size / height;
	}

	public void setBuildings(List<Building> nb)
	{
		buildings = nb;
	}
	public double getWidth()
	{
		return width;
	}
	public double getHeight()
	{
		return height;
	}
	public boolean setConductivity(double conductivity)
	{
		if(conductivity < 0)
			return false;
		this.conductivity = conductivity;
		return true;
	}
	
	public double[][] processPicture()
	{
		double[][] field = new double[(int)Math.ceil(height*hMult)][(int)Math.ceil(width*wMult)];
		System.out.println("field["+(int)Math.ceil(height*hMult)+"]["+(int)Math.ceil(width*wMult)+"]");
		int patternhalf = (int)(conductivity / dw);
		double[][] pattern = new double[(int)(2 * patternhalf + 1)][(int)(2 * patternhalf + 1)];
		
		pattern[patternhalf][patternhalf] = 100.0;
		
		double calvalue, d;
		for(int i = 0; i < patternhalf; i++)
		{
			for(int j = 0; j < patternhalf; j++)
			{
				d = Math.sqrt(i*i + j*j)/patternhalf;
//				if(d < 0.3)
//					calvalue = 1.0;
//				else
//					calvalue = 1.0  - d;
				calvalue = Math.exp(-d*d*4.0) - Math.exp(-4.0);
				//1.0 - 1.0/(d-1.0);
				if(calvalue > 0.0)
				{
					pattern[patternhalf + i][patternhalf + j] = calvalue;
					pattern[patternhalf + i][patternhalf - j] = calvalue;
					pattern[patternhalf - i][patternhalf + j] = calvalue;
					pattern[patternhalf - i][patternhalf - j] = calvalue;
				}
			}
		}
//		NumberFormat fr = new DecimalFormat("#000.0");
//		for(int i = 0; i < pattern.length; i++)
//		{
//			for(int j = 0; j < pattern[i].length; j++)
//				System.out.print(fr.format(pattern[i][j]) + " ");
//			System.out.println();
//		}
		
//		for(Presense p : pops)
		double avgx, avgy;
		for(Building p : buildings)
		{
			avgx = 0.0;
			avgy = 0.0;
			int length = p.polygon.count();
			for(int i = 0; i < length; i++)
			{
				double x1 = p.polygon.getX(i), y1 = p.polygon.getY(i);
				avgx += x1;
				avgy += y1;
//				System.out.println("Coords: " + (int)((x1 - x)*wMult) + ":"+ (int)((y1 - y)*hMult));
				field = applyPattern(field, pattern, p.service,(int)((x1 - x)*wMult), (int)((y1 - y)*hMult));
			}

			field = applyPattern(field, pattern, p.service,(int)((avgx/length - x)*wMult), (int)((avgy/length- y)*hMult));
		}
//		for(Building p : buildings)
//		{
//			coords = p.polygon.getCoords();
//			for(int i = 0; i < coords.length; i++)
//			{
//				field[(int)(coords[i][0] / dw)][(int)(coords[i][1] / dw)] = 0;
//			}
//		}
		return field;
	}
	private double[][] applyPattern(double[][] field, double[][] pattern, boolean pres, int x, int y)
	{
		if(field == null || field[0] == null)
			return field;
		if(pattern == null || pattern[0] == null)
			return field;
		if(x < 0 || x >= field[0].length)
			return field;
		if(y < 0 || y >= field.length)
			return field;
//		System.out.println("applyPattern in ["+x+"]["+y+"]");
		int patternhalf = pattern.length / 2;
		int hx, hy;
		for(int i = 0; i < pattern.length; i++)
		{
			for(int j = 0; j < pattern[i].length; j++)
			{
				hx = x - patternhalf + i;
				hy = y - patternhalf + j;
				if(checkCoords(field, hx, hy))//, field[i].length, field.length))
				{
					if(pres)
					{
						if(field[hy][hx] > 0)
							field[hy][hx] = Math.sqrt(pattern[i][j]*pattern[i][j] + field[hy][hx]*field[hy][hx]);
						else
							field[hy][hx] += pattern[i][j];
					}
					else
					{
						if(field[hy][hx] < 0)
							field[hy][hx] = -Math.sqrt(pattern[i][j]*pattern[i][j] + field[hy][hx]*field[hy][hx]);
						else
							field[hy][hx] -= pattern[i][j];
					}
 				}
				else
				{
//					System.out.println("Error in checking coords: "+hx+":"+hy);
				}
			}
		}
		return field;
	}
	private final boolean checkCoords(double[][] field, int x, int y)//, int width, int height)
	{
//		System.out.println("checkCoords(" + x + ", " + y + ", " + width + ", " + height);
		if(x < 0 || x >= field[0].length || y < 0 || y >= field.length)
			return false;
		return true;
	}
	
	public void setInts(double dw)
	{
		this.dw = dw;
	}
//	public boolean addPoP(Presense p)
//	{
//		return pops.add(p);
//	}
//	public boolean delPoP(Presense p)
//	{
//		return pops.remove(p);
//	}

}
