/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.edu.coverage.scalarfield;

//import com.netcracker.edu.coverage.Building;
import com.netcracker.edu.coverage.Building;
import com.netcracker.edu.coverage.MPolygon;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author spitty
 */
public class FieldWriter {

	private int width, height;
	private double[][] field;
	private int discrete;
	private static final int MAX_DISCRETE = 15;
	private BufferedImage bufImage = null;
	
	public FieldWriter(int width, int height)
	{
		this.width = width;
		this.height = height;
		bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	public List<MPolygon> calcPolygon(double[][] field, double level)
	{
		List<MPolygon> res = new ArrayList<MPolygon>();
		MPolygon p = new MPolygon();
		int width = field[0].length;
		int height = field.length;
		boolean inRegion[][] = new boolean[height][width];
		Point points[][][] = new Point[height][width][4];
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				inRegion[i][j] = field[i][j] > level;
			}
		}
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				if(j != width - 1)
				{
					if(inRegion[i][j] ^ inRegion[i][j+1])
					{
						float x = j + 1.0f/(1.0f - (float)((field[i][j+1] - level)/(field[i][j]-level)));
						float y = i;
						// mirroring
						y = width - y;
						p.add(x, y);
						if(inRegion[i][j])
						{
							points[i][j][2] = new Point(x, y);
						}
						else
						{
							points[i][j+1][0] = new Point(x, y);
						}
					}
				}
				if(i != height - 1)
				{
					if(inRegion[i][j] ^ inRegion[i+1][j])
					{
						float x = j;
						float y = i + 1.0f/(1.0f - (float)((field[i+1][j] - level)/(field[i][j]-level)));
						y = width - y;
						p.add(x, y);
						if(inRegion[i][j])
						{
							points[i][j][3] = new Point(x, y);
						}
						else
						{
							points[i+1][j][1] = new Point(x, y);
						}
					}
				}
			}
		}
/*
		// TODO: Make sure that we not loose region in point(0,0)
		boolean createRegion = false;//inRegion[0][0];
		boolean visited[][] = new boolean[height][width];
		Polygon newp = new Polygon();
		int from = 0;
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
//				if(visited[i][j] == false)
//					System.out.println("[" + i + ":" + j + "] " + visited[i][j] + " from "+ from);
//				if(i == 4 && j == 1106)
//					System.out.println("wow!");
				if(visited[i][j] != true)
				{
					visited[i][j] = true;
					if(inRegion[i][j] == true)
					{

						if(points[i][j][0] == null && points[i][j][1] == null
								&& points[i][j][2] == null
								&& points[i][j][3] == null)
							continue;
						// if Polygon is not created yet
						if(!createRegion)
						{
							newp = new Polygon();
							if(points[i][j][0] == null)
							{
								newp.add(j, i);
							}
							else
							{
								newp.add(points[i][j][0].x, points[i][j][0].y);
							}
							from = 0;
						}

						if( (from == 0 && i == 0) || (from == 3 && j == 0) ||
								(from == 1 && j == width - 1) || (from == 2 && i == height - 1))
						{
							newp.add(j, i);
						}
						else
						{
							// from == 0 if we came from left point, 1 if from up point etc...
							switch(from)
							{
								case 0:
									if(points[i][j][1] != null)
									{
										newp.add(points[i][j][1].x, points[i][j][1].y);
									}else if(i > 0)
									{
										i = i -	1;
										from = 3;
										break;
									}
								case 1:
									if(points[i][j][2] != null)
									{
										newp.add(points[i][j][2].x, points[i][j][2].y);
									}else if(j < width - 2)
									{
										j = j + 1;
										from = 0;
										break;
									}
								case 2:
									if(points[i][j][3] != null)
									{
										newp.add(points[i][j][3].x, points[i][j][3].y);
									}else if(i < height - 2)
									{
										i = i + 1;
										from = 1;
										break;
									}
								case 3:
									if(points[i][j][0] != null)
									{
										newp.add(points[i][j][0].x, points[i][j][0].y);
									}else if(j > 0)
									{
										j = j - 1;
										from = 2;
										break;
									}
								case 4:
									if(points[i][j][1] != null)
									{
										newp.add(points[i][j][1].x, points[i][j][1].y);
									}else if(i > 0)
									{
										i = i -	1;
										from = 3;
										break;
									}
								case 5:
									if(points[i][j][2] != null)
									{
										newp.add(points[i][j][2].x, points[i][j][2].y);
									}else if(j < width - 2)
									{
										j = j + 1;
										from = 0;
										break;
									}
								case 6:
									if(points[i][j][3] != null)
									{
										newp.add(points[i][j][3].x, points[i][j][3].y);
									}else if(i < height - 2)
									{
										i = i + 1;
										from = 1;
										break;
									}
								case 7:
									if(points[i][j][0] != null)
									{
										newp.add(points[i][j][0].x, points[i][j][0].y);
									}else if(j > 0)
									{
										j = j - 1;
										from = 2;
										break;
									}

								default:
//									continue;
									// TODO: I am here
							}
						}
						createRegion = true;
					}
				}
				else if(createRegion)
				{
					res.add(newp);
					from = 0;
					createRegion = false;
				}

			}
		}
 */
		res.add(p);
		return res;
	}

	public Image writeField(String filename)
	{
//		System.out.println("Image size: " + bi.getWidth() + " " + bi.getHeight());
		Graphics2D g = bufImage.createGraphics();

		for(int i = 0; i < field.length; i++)
		{
			int y = field.length-i;
			for(int j = 0; j < field[i].length; j++)
			{
				float val = (float)(field[i][j]);

				// Remove illumination
				if(val > 1.0)
					val = 1.0f;
				if(val < -1.0)
					val = -1.0f;
				if(val > 0.0)
				{
					// Add discretization
					if(discrete > 0)
						val = (float)Math.floor(val * discrete)/discrete;

					g.setColor(new Color(1.0f - 0.55f*val, 1.0f - 0.0f*val, 1.0f - 0.55f*val));
				}
				else
				{
//					val = -(float)Math.floor(val * discrete)/discrete;
//					g.setColor(new Color(1.0f - 0.0f*val, 1.0f - 0.1f*val, 1.0f - 0.1f*val));
				}

//				setForeground(new Color(1.0f, val, val));
//				System.out.println("Draw ["+i+"]["+j+"] in "+g.getColor().toString());
				g.drawRect(j, y, 0, 0);

			}
		}

		List<MPolygon> regions = calcPolygon(field, 0.3);
		g.setColor(Color.blue);
		for(MPolygon p : regions)
		{
			for(int i = 0; i < p.count(); i++)
			{
				g.drawRect((int)p.getX(i), (int)p.getY(i), 1, 1);
			}
		}
		if(filename != null)
			writeFile(filename);
		return bufImage;
	}

	public Image drawBuildings(Collection<Building> buildings, CreatePicture cp)
	{
		Graphics2D g = bufImage.createGraphics();
		g.setColor(Color.black);
		int[] xPoints = new int[100];
		int[] yPoints = new int[100];
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
				xPoints[i] = (int)((x1-cp.x)*cp.wMult);
				yPoints[i] = height - (int)((y1-cp.y)*cp.hMult);

				//System.out.println("Coords: " + (int)(x1 / dw) + ":"+ (int)(y1 / dw));
			}
			if(p.service)
				g.setColor(new Color(0.4f, 0.8f, 0.4f));
			else
				g.setColor(new Color(1.0f, 0.3f, 0.3f));
			g.fillPolygon(xPoints, yPoints, length);
			g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
			g.fillRect((int)((avgx/length-cp.x)*cp.wMult) - 5, height - (int)((avgy/length-cp.y)*cp.hMult) + 5, 10, 10);
//			g.setColor(Color.black);
//			g.drawPolygon(xPoints, yPoints, coords.length);
		}
		return bufImage;
	}

	public void writeFile(String filename)
	{
		if(bufImage == null || filename == null)
			return;
		writeImage2File(bufImage, filename);
	}

	public static void writeImage2File(RenderedImage bi, String filename)
	{
		try {
			ImageIO.write(bi, "PNG", new File(filename));//"output/"+idMap+"_scalar_field.png"));
		} catch (IOException ex) {
			Logger.getLogger(FieldWriter.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void setDiscrete(int discrete) {
		if(discrete < 0)
			discrete = 1;
		if(discrete > MAX_DISCRETE)
			discrete = 0;
		this.discrete = discrete;
	}

	public void setField(double[][] field) {
		this.field = field;
	}

	public int getDiscrete() {
		return discrete;
	}

	public double[][] getField() {
		return field;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	private class Point
	{
		public float x, y;
		protected Point(float x, float y)
		{
			this.x = x;
			this.y = y;
		}
	}
}
