package com.netcracker.edu.coverage.alg;

import com.netcracker.edu.coverage.AbstractCoverageAlgorithm;
import com.netcracker.edu.coverage.Building;
import com.netcracker.edu.coverage.CoverageHelperAlgorithm;
import com.netcracker.edu.coverage.CoverageMap;
import com.netcracker.edu.coverage.MPolygon;
import com.netcracker.edu.coverage.Pnt;

/**
 * Extends
 * @author Nikolay Egorov
 */
public class PopulateBuildingsAlgorithm extends CoverageHelperAlgorithm {
	/*
	 * Maximal destination between points in buildings in degrees
	 */
	public static final String PopulateBuildingsParamTag = "PopBuildingsD";

	public PopulateBuildingsAlgorithm() {
		super();
	}
	public PopulateBuildingsAlgorithm(AbstractCoverageAlgorithm source) {
		super(source);
	}

	@Override
	protected double getDouble(String key) {
		double result = super.getDouble(key);
		if(Double.isNaN(result)) result = 0.0002;//"PopBuildingsD"
		return result;
	}
	/**
	 * @see com.netcracker.edu.coverage.AbstractCoverageAlgorithm#modifyMap(com.netcracker.edu.coverage.CoverageMap)
	 */
	protected CoverageMap modifyMap(CoverageMap map) {
		Double maxd = getDouble(PopulateBuildingsParamTag);
		
		Long maxPointId = map.getMaxPointId();
		
		for(Building b0: map.getBuildings()){
			//adding points to buildings
			if(b0.polygon.count() > 2){
				MPolygon pb = b0.polygon;
				MPolygon pbNew = new MPolygon();
				
				pbNew.add(pb.getX(0), pb.getY(0));
				
				for(int k = 1; k < pb.count(); k++){									
					double dx = pb.getX(k) - pb.getX(k-1);
					double dy = pb.getY(k) - pb.getY(k-1);
					double r =   Math.sqrt(dx*dx + dy*dy);
					
					if(r > maxd){
						for(int q = 1; q <= r/maxd; q++){
							double nx = pb.getX(k-1) + q*dx*maxd/r;
							double ny = pb.getY(k-1) + q*dy*maxd/r;
							
							pbNew.add(nx, ny);
							
							b0.pointIds.add(maxPointId);
							map.getAllpoints().put(maxPointId, new Pnt(nx, ny));	
							maxPointId += 1;										
							
						}
					}								
					pbNew.add(pb.getX(k), pb.getY(k));									
				}
				
				b0.polygon = pbNew;
			}
		}
		map.setMaxPointId(maxPointId);
		return map;
	}

}
