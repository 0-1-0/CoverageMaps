package com.netcracker.edu.coverage.scalarfield;


import java.io.IOException;
import java.util.List;

import com.netcracker.edu.coverage.Bounds;
import com.netcracker.edu.coverage.Building;
import com.netcracker.edu.coverage.CoverageMap;
import com.netcracker.edu.coverage.alg.PopulateBuildingsAlgorithm;
import com.netcracker.edu.coverage.data.OSMRandomProblemLoader;

public class MainClass
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException 
	{
		Object idMap = "map-1.osm";
		int discrete = 4;
//		int houseNum = 48;
		int size = 1400;
		System.out.println("Loading data...");
		OSMRandomProblemLoader rpl = new OSMRandomProblemLoader();
		rpl.setMapId(idMap);
		rpl.loadMap();
		CoverageMap map = rpl.getCoverageMap();
		
		//added by evdokimov:
		PopulateBuildingsAlgorithm pba = new PopulateBuildingsAlgorithm();
		pba.setCoverageMap(map);
		map = pba.getModifiedCoverageMap();
			
		Bounds bounds = map.getBounds();
		System.out.println("Map bounds: "+bounds.getMinlat() + " " + bounds.getMaxlat() + " " + bounds.getMinlon() + " " + bounds.getMaxlon());
//		double sizeMult = bounds.height < bounds.width ? bounds.width / size : bounds.height / size;
//		sizeMult = 1.0;
		CreatePicture cp = new CreatePicture(bounds, size);
		double conductivity = size/(bounds.getWidth()*550);
		cp.setConductivity(conductivity);
		cp.setInts(1);

		List<Building> buildings = rpl.getCoverageMap().getBuildings();
		System.out.println("Houses number: " + buildings.size());
		cp.setBuildings(buildings);


		System.out.println("Process picture...");
		double[][] field = cp.processPicture();

//		NumberFormat fr = new DecimalFormat("#00.0 ");

		System.out.println("Start picture writing...");
		// TODO
		FieldWriter fWriter = new FieldWriter((int)size, (int)size);
		fWriter.setField(field);
		fWriter.setDiscrete(discrete);

		fWriter.writeField(null);
		fWriter.drawBuildings(buildings, cp);
		
		fWriter.writeFile("output/"+idMap+"_scalar_field.png");
		
		System.out.println("Picture has writen...");
	}
}
