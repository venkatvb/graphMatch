package parser;

import graph.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import logger.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GxlToGiraphParser {
	
	public String pathToGxlFile = "";
	Graph graph;
	
	public GxlToGiraphParser(String path) {
		this.pathToGxlFile = path;
	}
	
	public void setPathToGxl(String path) {
		this.pathToGxlFile = path;
	}
	
	public boolean parse() throws IOException, SAXException, ParserConfigurationException {
		if(pathToGxlFile.equals("")) {
			throw new FileNotFoundException();
		}
		graph = new Graph();
		File xmlFile = new File(pathToGxlFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		NodeList nodes = doc.getElementsByTagName("node");
		for( int i=0; i<nodes.getLength(); i++ ) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				Integer currentNodeId = Integer.parseInt(element.getAttribute("id"));
				Log.info("Adding the edge " + currentNodeId.toString());
				graph.addNode(currentNodeId);
			}
		}
		
		return true;
	}
}
