package parser;

import graph.Graph;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import logger.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GxlParser {
	
	public static final String NODE_TAG_NAME = "node";
	public static final String EDGE_TAG_NAME = "edge";
	public static final String FROM_ATTRIBUTE_NAME = "from";
	public static final String TO_ATTRIBUTE_NAME = "to";
	public static final String ID_ATTRIBUTE_NAME = "id";
	public static final String DISTANCE_ATTRIBUTE_VALUE = "distance0";
	public static final String NAME_ATTRIBUTE_NAME = "name";
	public static final String SEQUENCE_ATTRIBUTE_VALUE = "sequence";
	
	public String pathToGxlFile = "";
	Graph graph;
	
	public GxlParser(String path) {
		this.pathToGxlFile = path;
	}
	
	public void setPathToGxl(String path) {
		this.pathToGxlFile = path;
	}
	
	public Graph parse() throws Exception {
		// Returns a graph object after parsing the gxl file.
		if(pathToGxlFile.equals("")) {
			throw new FileNotFoundException();
		}
		graph = new Graph();
		File xmlFile = new File(pathToGxlFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		NodeList nodes = doc.getElementsByTagName(NODE_TAG_NAME);
		for( int i=0; i<nodes.getLength(); i++ ) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				Integer currentNodeId = Integer.parseInt(element.getAttribute(ID_ATTRIBUTE_NAME));
				Log.info("Adding the edge " + currentNodeId.toString());
				NodeList childNodes = element.getChildNodes();
				String sequence = "";
				for(int j=0; j<childNodes.getLength(); j++ ) {
					Node child = childNodes.item(j);
					if(child.getNodeType() == Node.ELEMENT_NODE) {
						Element childElement = (Element) child;
						String name = childElement.getAttribute(NAME_ATTRIBUTE_NAME);
						if(name.equals(SEQUENCE_ATTRIBUTE_VALUE)) {
							NodeList sequenceNodes = childElement.getChildNodes();
							for(int k=0; k<sequenceNodes.getLength(); k++ ) {
								Node sequenceNode = sequenceNodes.item(k);
								if(sequenceNode.getNodeType() == Node.ELEMENT_NODE) {
									Element sequenceElement = (Element) sequenceNode;
									sequence = sequenceElement.getTextContent();
								}
							}
						}
					}
				}
				graph.addNode(currentNodeId, sequence);
			}
		}
		nodes = doc.getElementsByTagName(EDGE_TAG_NAME);
		for(int i=0; i<nodes.getLength(); i++ ) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				Integer fromNodeId = Integer.parseInt(element.getAttribute(FROM_ATTRIBUTE_NAME));
				Integer toNodeId = Integer.parseInt(element.getAttribute(TO_ATTRIBUTE_NAME));
				NodeList childNodes = element.getChildNodes();
				Double distance = 0.0;
				for(int j=0; j<childNodes.getLength(); j++ ) {
					Node child = childNodes.item(j);
					if(child.getNodeType() == Node.ELEMENT_NODE) {
						Element childElement = (Element) child;
						String name = childElement.getAttribute(NAME_ATTRIBUTE_NAME);
						if(name.equals(DISTANCE_ATTRIBUTE_VALUE)) {
							NodeList distanceNodes = childElement.getChildNodes();
							for(int k=0; k<distanceNodes.getLength(); k++ ) {
								Node distanceNode = distanceNodes.item(k);
								if(distanceNode.getNodeType() == Node.ELEMENT_NODE) {
									Element distanceElement = (Element) distanceNode;
									distance = Double.parseDouble(distanceElement.getTextContent());
								}
							}
						}
					}
				}
				Log.info("Adding edge from : " 
						+ fromNodeId.toString() 
						+ ", to : "
						+ toNodeId.toString()
						+ ", with the distance : "
						+ distance.toString() );
				graph.addEdge(fromNodeId, toNodeId, distance);
			}
		}
		return graph;
	}
}
