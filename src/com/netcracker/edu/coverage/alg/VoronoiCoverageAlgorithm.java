package com.netcracker.edu.coverage.alg;

import java.util.HashMap;
import java.util.Map;

import com.netcracker.edu.coverage.AbstractCoverageAlgorithm;
import com.netcracker.edu.coverage.Building;
import com.netcracker.edu.coverage.CoverageCoreAlgorithm;
import com.netcracker.edu.coverage.CoverageMap;
import com.netcracker.edu.coverage.MPolygon;
import com.netcracker.edu.coverage.Pnt;
import com.netcracker.edu.coverage.alg.voronoi.Voronoi;

/**
 * Core algorithm based on Voronoi diagrams. 
 * @author Nikolay Egorov
 */
public class VoronoiCoverageAlgorithm extends CoverageCoreAlgorithm{
	private Map<Long, Pnt> points = new HashMap<Long, Pnt>();
	private HashMap<Long, MPolygon> polyStorage;

	public VoronoiCoverageAlgorithm() {
		super();
	}
	public VoronoiCoverageAlgorithm(AbstractCoverageAlgorithm source) {
		super(source);
	}

	protected void createDiagram(){
		polyStorage = new HashMap<Long, MPolygon>();
		HashMap<Integer, Long> idMapper = new HashMap<Integer, Long>();//index, id	
		
		float[][] pnts = new float[points.size()][2];
			
			int cnt = 0;
			for(Long id: points.keySet()){
				pnts[cnt][0] = (float)points.get(id).getX();
				pnts[cnt][1] = (float)points.get(id).getY();
				
				idMapper.put(cnt, id);
				cnt++;
			}	

			Voronoi myVoronoi = new Voronoi( pnts );
			MPolygon[] polys = myVoronoi.getRegions();	
			
			for(int i = 0; i < polys.length; i++){
				if(polys[i] != null)
				polyStorage.put(idMapper.get(i), polys[i]);
			}
			
		
		//normalizeDiagram();
	}

	public final HashMap<Long, MPolygon> getDiagram() {
		return polyStorage;
	}
	
	public void setPoints(Map<Long, Pnt> points) {
		this.points = points;
	}
	
	/**
	 * @see com.netcracker.edu.coverage.CoverageCoreAlgorithm#getCalculationProgress()
	 */
	public double getCalculationProgress() {
		throw new UnsupportedOperationException("Tracking calculation progress is not possible for VoronoiCoverageAlgorithm");
	}
	/**
	 * @see com.netcracker.edu.coverage.AbstractCoverageAlgorithm#modifyMap(com.netcracker.edu.coverage.CoverageMap)
	 */
	protected CoverageMap modifyMap(CoverageMap map) {
		setPoints(map.getAllpoints());
		createDiagram();
		
		for(Building b: map.getBuildings())
			for(Long id: b.pointIds)
				if(polyStorage.containsKey(id))
					b.areas.put(id, polyStorage.get(id));
		return map;
	}
}
