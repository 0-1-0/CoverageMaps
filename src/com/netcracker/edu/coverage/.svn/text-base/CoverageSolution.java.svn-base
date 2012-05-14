package com.netcracker.edu.coverage;

/**
 * Produces the final result of coverage visualization - either a picture or data for some viewer.
 * @author Nikolay Egorov
 * @author Alexey Evdokimov
*/
public interface CoverageSolution {
	/**
	 * Defines where to write the solution. Is called before adding areas.
	 */
	void setMapId(Object idMap);
	/**
	 * Process the area(s) of the given building.
	 * Building contains information whether the services are available in the area.
	 * If building is absent (e.g. for wastes) the caller should pass virtual building to this method.
	 * Note: if some algorithms process areas for different services separately,
	 * 	and the signature of this method can be extended by the index of service.
	 */
	void addBuilding(Building b);
	/**
	 * Completes the solution creation process.
	 */
	void drawSolution();
	
	void setBounds(Bounds bounds);
}
