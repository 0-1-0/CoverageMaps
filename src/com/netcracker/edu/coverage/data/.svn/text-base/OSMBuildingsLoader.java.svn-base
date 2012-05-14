package com.netcracker.edu.coverage.data;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.netcracker.edu.coverage.Bounds;
import com.netcracker.edu.coverage.Building;
import com.netcracker.edu.coverage.CoverageMap;
import com.netcracker.edu.coverage.Pnt;

/**
 * Parser of OpenStreetMap (OSM) geographic data format: extracts buildings coordinates from it.
 * @author Nikolay Egorov
 */
public class OSMBuildingsLoader {
	
//	public double getMaxlat() {
//		return maxlat;
//	}
//
//	public double getMinlat() {
//		return minlat;
//	}
//
//	public double getMaxlong() {
//		return maxlong;
//	}
//
//	public double getMinlon() {
//		return minlon;
//	}

//	public LinkedList<Building> getBuildings() {
//		return buildings;
//	}
//
//	public HashMap<Long, Pnt> getAllPoints() {
//		return pointStorage;
//	}

//	private LinkedList<Building> buildings = new LinkedList<Building>();
//	private HashMap<Long, Pnt> pointStorage = new HashMap<Long, Pnt>(); // id, coords

	private static String stringValue(NamedNodeMap attrs, String name) {
		return ((Attr) attrs.getNamedItem(name)).getValue();
	}
	private static double doubleValue(NamedNodeMap attrs, String name) {
		return Double.parseDouble(stringValue(attrs, name));
	}

	/**
	 * @param input The XML input
	 * @param bounds The bounds to restrict the map to
	 * @return The map. Note: map.building.service is not set here
	 */
	public CoverageMap loadCoverageMap(InputStream input, Bounds bounds) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(input);

		if(bounds==null) {
			Node bs = doc.getElementsByTagName("bounds").item(0);
			bounds = new Bounds(
				doubleValue(bs.getAttributes(), "minlat"),
				doubleValue(bs.getAttributes(), "minlon"),
				doubleValue(bs.getAttributes(), "maxlat"),
				doubleValue(bs.getAttributes(), "maxlon"));
		}

		NodeList nodeLst = doc.getElementsByTagName("node");
		long maxPointId = 0;
		LinkedList<Building> buildings = new LinkedList<Building>();
		HashMap<Long, Pnt> pointStorage = new HashMap<Long, Pnt>(); // id, coords

		for (int s = 0; s < nodeLst.getLength(); s++) {

			NamedNodeMap attrs = nodeLst.item(s).getAttributes();

			double y = doubleValue(attrs, "lat");
			double x = doubleValue(attrs, "lon");

			Long id = Long.parseLong(stringValue(attrs, "id"));

			if (bounds.contains(x, y)) {
				pointStorage.put(id, new Pnt(x, y));
				if(maxPointId < id) 
					maxPointId = id;
			}
		}
		maxPointId += 1;
		
		NodeList ways = doc.getElementsByTagName("way");

		for (int s = 0; s < ways.getLength(); s++) {

			Node way = ways.item(s);
			NodeList nds = way.getChildNodes();

			Building b0 = new Building();
			boolean toAdd = true;

			for (int i = 0; i < nds.getLength() && toAdd; i++) {
				Node x = nds.item(i);
				HashMap<String, String> tags = new HashMap<String, String>();

				if (x.getNodeName().equalsIgnoreCase("nd")) {
					Long id = Long.parseLong(((Attr) x.getAttributes()
							.getNamedItem("ref")).getValue());
					Pnt nodepoint = pointStorage.get(id);

					toAdd = (nodepoint != null);

					if (toAdd) {
						b0.polygon.add(nodepoint.coord(0), nodepoint.coord(1));
						b0.pointIds.add(id);
					}
				}

				if (x.getNodeName().equalsIgnoreCase("tag")) {
					NamedNodeMap attrs = x.getAttributes();
					tags.put(stringValue(attrs, "k"), stringValue(attrs, "v"));
				}

				if (toAdd && tags.containsKey("building")
						&& tags.get("building").equalsIgnoreCase("yes")) {							
					buildings.add(b0);
				}
			}
		}		
		return new CoverageMap(buildings, pointStorage, bounds);
	}
}
