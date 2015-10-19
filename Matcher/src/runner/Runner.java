package runner;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import parser.GxlToGiraphParser;

public class Runner {
	public static void main(String args[]) throws IOException, SAXException, ParserConfigurationException {
		GxlToGiraphParser parser = new GxlToGiraphParser("F:\\Graph Databases\\gxl\\enzyme_1.gxl");
		parser.parse();
	}
}
