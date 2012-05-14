package com.netcracker.edu.coverage;

import java.io.IOException;

/**
 * Loads information about buildings - their coordinates and service(s) availabilities in them.
 * {@link #getMapId()} is used to determine from where to load.
 * TODO: support multiple services identifiers (to describe Building.services).
* @author Nikolay Egorov
 * @author Alexey Evdokimov
*/
public interface CoverageProblemLoader {
	void setMapId(Object Id);
	Object getMapId();
	/**
	 * @param bounds Rectangle to filter buildings within it. Can be null (default).
	 */
	void setBounds(Bounds bounds);
	
	void loadMap() throws IOException;
	
	CoverageMap getCoverageMap();//all the data below is here now
	//Collection<Building> getBuildings();
	//HashMap<Long, Pnt> getAllPoints();
	
//	double getMaxlat();
//	double getMinlat() ;
//	 double getMaxlong();
//	 double getMinlon();

}
