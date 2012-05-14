package com.netcracker.edu.coverage.data;

import com.netcracker.edu.coverage.Bounds;
import com.netcracker.edu.coverage.CoverageProblemLoader;

/**
 *
 * @author Alexey Evdokimov
 */
public final class OSMRandomProblemLoaderTest extends ProblemLoaderTest {

	protected Object getIdMap() {
		return "map-3.osm";
	}
	protected Bounds getBounds() {
		return null;
	}
	protected Object[] getIdServices() {
		return null;
	}

	protected CoverageProblemLoader initLoader() throws Exception {
		return new OSMRandomProblemLoader();
	}

}
