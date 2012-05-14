package com.netcracker.edu.coverage.scalarfield;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.netcracker.edu.coverage.Bounds;
import com.netcracker.edu.coverage.Building;
import com.netcracker.edu.coverage.data.OSMRandomProblemLoader;

public class MainClass_1
{
	
	
	/**
	 * @param args
	 */
	private static void main(String[] args) throws IOException
	{
		Object idMap = "map-1.osm";
		int discrete = 3;
//		int houseNum = 48;
		int size = 700;
		System.out.println("Loading data...");
		OSMRandomProblemLoader rpl = new OSMRandomProblemLoader();
		rpl.setMapId(idMap);
		rpl.loadMap();
		Bounds bounds = rpl.getCoverageMap().getBounds();
		System.out.println("Map bounds: "+bounds.getMinlat() + " " + bounds.getMaxlat() + " " + bounds.getMinlon() + " " + bounds.getMaxlon());
//		double sizeMult = bounds.height < bounds.width ? bounds.width / size : bounds.height / size;
//		sizeMult = 1.0;
		CreatePicture cp = new CreatePicture(bounds, size);
		cp.setConductivity(55);
		cp.setInts(1);

		List<Building> buildings = rpl.getCoverageMap().getBuildings();

		cp.setBuildings(buildings);
//		cp.setBounds(rpl.getBounds(), size);
		
//		cp.addPoP(new Presense(10, 10, true));
//		cp.addPoP(new Presense(30, 10, true));
//		cp.addPoP(new Presense(50, 50, true));
//		cp.addPoP(new Presense(10, 30, true));
		
//		Random rnd = new Random();
//
//		for(int i = 0; i < houseNum/6; i++)
//		{
//			double gcx = rnd.nextDouble()*(size-50);
//			double gcy = rnd.nextDouble()*(size-50);
//			cp.addPoP(new Presense(gcx + rnd.nextDouble()*50, gcy + rnd.nextDouble()*50, true));
//			cp.addPoP(new Presense(gcx + rnd.nextDouble()*50, gcy + rnd.nextDouble()*50, true));
//			cp.addPoP(new Presense(gcx + rnd.nextDouble()*50, gcy + rnd.nextDouble()*50, true));
//			cp.addPoP(new Presense(gcx + rnd.nextDouble()*50, gcy + rnd.nextDouble()*50, true));
//			cp.addPoP(new Presense(gcx + rnd.nextDouble()*50, gcy + rnd.nextDouble()*50, true));
//			cp.addPoP(new Presense(gcx + rnd.nextDouble()*50, gcy + rnd.nextDouble()*50, true));
//		}

		System.out.println("Process picture...");
		double[][] field = cp.processPicture();

		NumberFormat fr = new DecimalFormat("#00.0 ");
//		for(int i = 0; i < field.length; i++)
//		{
//			for(int j = 0; j < field[i].length; j++)
//				System.out.print(fr.format(field[i][j]));
//			System.out.println("");
//		}
//		final Frame f = new Frame();
//		f.setSize(414, 424);
//		f.addWindowListener(new WindowAdapter() {
//	         public void windowClosing(WindowEvent e) {
//	             f.dispose();
//	          }
//	       });
//		Visualizator vs = new Visualizator();
//		vs.setField(field);
//		vs.discrete = 3;
//		f.add(vs);
//		f.setVisible(true);

		System.out.println("Start picture writing...");
		BufferedImage bi = new BufferedImage((int)size, (int)size, BufferedImage.TYPE_INT_ARGB);
		System.out.println("Image size: " + bi.getWidth() + " " + bi.getHeight());
		Graphics2D g = bi.createGraphics();

		for(int i = 0; i < field.length; i++)
		{
			int y = field.length-i;
			for(int j = 0; j < field[i].length; j++)
			{
				float val = (float)(field[i][j]);
				if(val > 1.0)
					val = 1.0f;
				if(val < -1.0)
					val = -1.0f;
				if(val > 0.0)
				{
					val = (float)Math.floor(val * discrete)/discrete;
					g.setColor(new Color(1.0f - 0.55f*val, 1.0f - 0.0f*val, 1.0f - 0.55f*val));
				}
				else
				{
					val = -(float)Math.floor(val * discrete)/discrete;
					g.setColor(new Color(1.0f - 0.0f*val, 1.0f - 0.1f*val, 1.0f - 0.1f*val));
				}

//				setForeground(new Color(1.0f, val, val));
//				System.out.println("Draw ["+i+"]["+j+"] in "+g.getColor().toString());
				g.drawRect(j, y, 0, 0);

			}
		}
		g.setColor(Color.black);
		int[] xPoints = new int[100];
		int[] yPoints = new int[100];
		double dw = 10./4;
		for(Building p : buildings)
		{
			for(int i = 0; i < p.polygon.count(); i++)
			{
				xPoints[i] = (int)((p.polygon.getX(i)-cp.x)*cp.wMult);
				yPoints[i] = size - (int)((p.polygon.getY(i)-cp.y)*cp.hMult);

				//System.out.println("Coords: " + (int)(coords[i][0] / dw) + ":"+ (int)(coords[i][1] / dw));
			}
			if(p.service)
				g.setColor(new Color(0.4f, 0.8f, 0.4f));
			else
				g.setColor(new Color(1.0f, 0.3f, 0.3f));
			g.fillPolygon(xPoints, yPoints, p.polygon.count());
//			g.setColor(Color.black);
//			g.drawPolygon(xPoints, yPoints, coords.length);

		}


		try {
			ImageIO.write(bi, "GIF", new File("output/"+idMap+"_scalar_field.gif"));
		} catch (IOException ex) {
			Logger.getLogger(MainClass_1.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
