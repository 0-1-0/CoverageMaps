package com.netcracker.edu.coverage.data;

import com.netcracker.edu.coverage.Bounds;
import com.netcracker.edu.coverage.CoverageMap;
import com.netcracker.edu.coverage.CoverageProblemLoader;

/**
 * The only thing remained for subclass is loadMap() method where this.map should be set.
 * @author Alexey Evdokimov
 */
public abstract class AbstractProblemLoader implements CoverageProblemLoader {
	private Object mapId;
	protected Bounds bounds;
	
	protected CoverageMap map;

	/**
	 * @see com.netcracker.edu.coverage.CoverageProblemLoader#getCoverageMap()
	 */
	public final CoverageMap getCoverageMap() {
		return map;
	}

	/**
	 * @see com.netcracker.edu.coverage.CoverageProblemLoader#getMapId()
	 */
	public final Object getMapId() {
		return mapId;
	}
	/**
	 * @see com.netcracker.edu.coverage.CoverageProblemLoader#setMapId(java.lang.Object)
	 */
	public void setMapId(Object Id) {
		this.mapId = Id;
	}

	/**
	 * @see com.netcracker.edu.coverage.CoverageProblemLoader#setBounds(com.netcracker.edu.coverage.Bounds)
	 */
	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

}
