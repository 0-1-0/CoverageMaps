package com.netcracker.edu.coverage.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import com.netcracker.edu.coverage.Bounds;
import com.netcracker.edu.coverage.Building;
import com.netcracker.edu.coverage.CoverageSolution;
import com.netcracker.edu.coverage.MPolygon;

/**
 * Saves to a picture. Can work with 1 service only.
 * @author Nikolay Egorov
 */
public class GifRendererSolution implements CoverageSolution{
	private Object mapId;
	protected String outputPath = "output";
	private int width = 700, height = 700;	
	
	private Collection<Building> buildings = new LinkedList<Building>();
	private double maxlat;
	private double maxlon;
	private double minlat;
	private double minlon;
	
	protected void render(Graphics graphics) {
		Graphics2D g = (Graphics2D)graphics;
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		
		for(Building b1:buildings){
			  g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0));
			  if(b1.service){
				  g.setColor(Color.green);
				  g.fillPolygon(b1.polygon.getAWTPolygon());
				  
				  g.setColor(Color.blue);
				  for(int i = 0; i < b1.polygon.count(); i++){
					  g.fillRect((int)b1.polygon.getX(i), (int)b1.polygon.getY(i), 2, 2);
				  }
			  }else{
				  g.setColor(Color.red);
				  g.fillPolygon(b1.polygon.getAWTPolygon());
			  }
			  
			  g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));
			  for(MPolygon p:b1.areas.values()){
//				  if(p.count()<3)//workaround of VoronoiCoverageAlgorithm bug 
//					  continue;
				  Polygon ap = p.getAWTPolygon();
				  if(b1.service){
					  g.setColor(Color.orange);
					  g.fillPolygon(ap);
				  }
				  g.setColor(Color.magenta);
				  g.drawPolygon(ap);			  
			  }
			  
		}		
		
		g.dispose();
	}

	@Override
	public void drawSolution() {
		try {
			BufferedImage b = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			render(b.createGraphics());
			ImageIO.write(b, "gif", new File(outputPath, mapId + ".gif"));
		} catch (IOException e) {
			System.out.println("GifRendererSolution Error!");
			e.printStackTrace();
		}
	}

	@Override
	public void addBuilding(Building b) {
		b.polygon.translateCoords(maxlat, minlat, maxlon, minlon, height, width);
		for(MPolygon p:b.areas.values()){
			p.translateCoords(maxlat, minlat, maxlon, minlon, height, width);
		}
		
		buildings.add(b);		
	}

	@Override
	public void setMapId(Object Id) {
		mapId = Id;		
	}

	@Override
	public void setBounds(Bounds bounds) {
		minlat = bounds.getMinlat();
		minlon = bounds.getMinlon();
		maxlon = bounds.getMaxlon();
		maxlat = bounds.getMaxlat();
	}

	/**
	 * Sets the size of an image in pixels.
	 */
	public void setSize(int width, int height) {
		this.height = height;
		this.width = width;
	}

}
