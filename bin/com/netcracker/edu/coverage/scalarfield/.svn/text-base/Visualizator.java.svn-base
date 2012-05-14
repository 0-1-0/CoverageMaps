package com.netcracker.edu.coverage.scalarfield;

import java.applet.Applet;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;

public class Visualizator extends Applet
{
	
	private double[][] field;
	public int discrete = 5;
	
	private static final long serialVersionUID = 1L;

	public void setField(double[][] nf)
	{
		field = nf;
	}
	
	public void paint(Graphics g)
	{
		NumberFormat ft = new DecimalFormat("#000.00 ");

		for(int i = 0; i < field.length; i++)
		{
			for(int j = 0; j < field[i].length; j++)
			{		
				float val = (float)(field[i][j]);
				if(val > 1.0)
					val = 1.0f;
				if(val < 0.0)
					val = 0.0f;
				val = (float)Math.floor(val * discrete)/discrete;
				g.setColor(new Color(1.0f , 1.0f - 0.8f*val, 1.0f - 0.8f*val));
//				setForeground(new Color(1.0f, val, val));
				g.drawRect(i, j, 0, 0);
				
			}
		}
	}
}
