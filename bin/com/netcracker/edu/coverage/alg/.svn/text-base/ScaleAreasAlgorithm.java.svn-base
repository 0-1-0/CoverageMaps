package com.netcracker.edu.coverage.alg;

import java.util.ArrayList;

import com.netcracker.edu.coverage.AbstractCoverageAlgorithm;
import com.netcracker.edu.coverage.Building;
import com.netcracker.edu.coverage.CoverageHelperAlgorithm;
import com.netcracker.edu.coverage.CoverageMap;
import com.netcracker.edu.coverage.MPolygon;
import com.netcracker.edu.coverage.Pnt;

public class ScaleAreasAlgorithm extends CoverageHelperAlgorithm {	
	public static final String ScaleAreasParamTag = "ScaleAreasD";

	public ScaleAreasAlgorithm() {
		super();
	}
	public ScaleAreasAlgorithm(AbstractCoverageAlgorithm source) {
		super(source);
	}

	@Override
	protected double getDouble(String key) {
		double result = super.getDouble(key);
		if(Double.isNaN(result)) result = 0.0005;//"ScaleAreasD"
		return result;
	}
	/**
	 * @see com.netcracker.edu.coverage.AbstractCoverageAlgorithm#modifyMap(com.netcracker.edu.coverage.CoverageMap)
	 */
	protected CoverageMap modifyMap(CoverageMap map) {
		Double maxd = getDouble(ScaleAreasParamTag);
		
		for(Building b: map.getBuildings()){
			ArrayList<Pnt> xP= new ArrayList<Pnt>();	
			
			for(Long id: b.pointIds) if(b.areas.get(id)!= null){
				MPolygon poly = b.areas.get(id);
				Pnt p = map.getAllpoints().get(id);
				
				for(int i = 0; i < poly.count(); i++){
					double dx = poly.getX(i) - p.getX();
					double dy = poly.getY(i) - p.getY();
					double r = Math.sqrt(dx*dx + dy*dy);
					
					if(r > maxd){
						poly.setX(i, p.getX() + dx*maxd/r);
						poly.setY(i, p.getY() + dy*maxd/r);
					}
					
					xP.add(new Pnt(poly.getX(i), poly.getY(i)));
				}
			}
			
			xP = new ConvexHull().quickHull(xP);
			
			MPolygon p = new MPolygon();
			for(int i = 0; i < xP.size(); i++)
				p.add(xP.get(i).getX(), xP.get(i).getY());
			
			b.areas.clear();
			b.areas.put((Long) b.pointIds.toArray()[0], p);
		}
		return map;
	}

}
