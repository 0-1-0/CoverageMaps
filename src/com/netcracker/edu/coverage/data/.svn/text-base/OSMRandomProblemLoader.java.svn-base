package com.netcracker.edu.coverage.data;

import java.io.File;
import java.io.FileInputStream;
import java.util.Random;

import com.netcracker.edu.coverage.Building;
import com.netcracker.edu.coverage.CoverageMap;

/**
 * Buildings - from OpenStreetMap FILE, service availability - randomly generated.
 * @author Nikolay Egorov
 */
public class OSMRandomProblemLoader extends RandomProblemLoader{
	private OSMBuildingsLoader osm = new OSMBuildingsLoader();
	

	/**
	 * @see com.netcracker.edu.coverage.data.RandomProblemLoader#loadCoverageMap(java.util.Random)
	 */
	protected CoverageMap loadCoverageMap(Random random) throws Exception {
		CoverageMap map = osm.loadCoverageMap(new FileInputStream(
				new File(getMapsPath(), ((String) getMapId()) + ".xml")), bounds);
		for (Building b : map.getBuildings()) {
			b.service = random.nextBoolean();
		}
		return map;
	}
}
