package com.netcracker.edu.coverage;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * The input, output and intermediate data of coverage areas processing:
 *  the results of the problem loading / preprocessing
 *  as well as the results of algorithms producing coverage areas (in building.areas).
 * Note: the main visualizer class is responsible for transformation 
 * from {@link CoverageMap} to {@link CoverageSolution}:
 *	<code> solution.setMapId(loader.getMapId());
		solution.setBounds(map.getBounds());
		for(Building b: map.getBuildings())
			solution.addBuilding(b);				
		solution.drawSolution();
	</code>
 * @author Nikolay Egorov
 */
public class CoverageMap implements Serializable {
	private static final long serialVersionUID = 4823363965199530478L;
	
	private List<Building> buildings;
	private Map<Long, Pnt> allpoints;
	private Bounds bounds;
	private Long maxPointId;
	
	public CoverageMap(){
		buildings = new LinkedList<Building>();
		allpoints = new HashMap<Long, Pnt>();
		bounds = new Bounds();
		maxPointId = new Long(0);
	}
	
	public CoverageMap(Collection<Building> b, Map<Long, Pnt> pnts, Bounds bnds){
		buildings = new LinkedList<Building>();
		buildings.addAll(b);
		setAllpoints(pnts);
		bounds = bnds;
	}

//	public void addBuilding(Building b){
//		buildings.add(b);
//		for( b.polygon.){
//			allpoints.put(id, ..);
//		}
//	}
	
//	public void addPoint(Pnt p){
//		where to add?
//	}
	
//	public void addPointToBuilding(int buildingNumber, Pnt p){
//		where is id?
//		buildings.get(buildingNumber);
//		allpoints.put(id, p);
//		if(((Long)id) > maxPointId)
//			maxPointId = id;
//	}
	
	public final Bounds getBounds() {
		return bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

	public final List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public final Map<Long, Pnt> getAllpoints() {
		return allpoints;
	}

	public void setAllpoints(Map<Long, Pnt> allpoints) {
		this.allpoints = allpoints;
		
		maxPointId = new Long(0);
		for(Long id: allpoints.keySet()){
			if(((Long)id) > maxPointId)
				maxPointId = id;
		}
	}

	public final Long getMaxPointId() {
		return maxPointId;
	}

	public void setMaxPointId(Long maxPointId) {
		this.maxPointId = maxPointId;
	}

}
