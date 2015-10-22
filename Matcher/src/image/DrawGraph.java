package image;

import graph.Graph;
import graph.Node;
import helper.ImageHelper;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import logger.Log;

public class DrawGraph {
	public static final int WIDTH = 2000;
	public static final int HEIGHT = 2000;
	public static final int NODE_POINT_WIDTH = 20;
	public static final int NODE_POINT_HEIGHT = 20;
	public static final int ADDITIONAL_X = 500;
	public static final int ADDITIONAL_y = 500;
	
	Graphics2D g;
	
	public int getCompressCoordinate(int value) {
		return (int) Math.sqrt(value);
	}
	
	public void drawFilledOval(int x, int y, int width, int height) {
		// Changing the x and y to point the center of the oval.
		int toSubtractX = width / 2;
		int toSubtractY = height / 2;
		x -= toSubtractX;
		y -= toSubtractY;
		x += ADDITIONAL_X;
		y += ADDITIONAL_y;
		g.fillOval(x, y, width, height);
	}
	
	public void drawOval(int x, int y, int width, int height) {
		// changing the x and y to point the center of the oval.
		int toSubtractX = width / 2;
		int toSubtractY = height / 2;
		x -= toSubtractX;
		y -= toSubtractY;
		x += ADDITIONAL_X;
		y += ADDITIONAL_y;
		g.drawOval(x, y, width, height);
	}
	
	public void drawNodePoint(int x, int y) throws Exception {
		Log.info("Drawing node point " + x + " " + y);
		this.drawFilledOval(x, y, NODE_POINT_WIDTH, NODE_POINT_HEIGHT);
	}
	
	public void drawCircle(int x, int y, int radius) throws Exception {
		Log.info("Drawing circle " + x + " " + y + " " + radius);
		this.drawOval(x, y, radius, radius);
	}
	
	public void drawNode(int x, int y, int radius) throws Exception {
		// Inputs the uncompressed coordinates compresses it, then draws oval and circle with the given radius.
		// Drawing the node point.
		x = getCompressCoordinate(x);
		y = getCompressCoordinate(y);
		this.drawNodePoint(x, y);
		// Drawing the circle with the given radius around the node point.
		radius = getCompressCoordinate(radius);
		this.drawCircle(x, y, radius);
	}
	
	public BufferedImage draw(Graph graph) throws Exception {
		BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = bufferedImage.createGraphics();
		g.setPaint(Color.BLACK);
		ArrayList<Node> nodes = graph.getNodeList();
		for(Node node : nodes) {
			int x = Integer.parseInt(node.getHashX());
			int y = Integer.parseInt(node.getHashY());
			int radius = Integer.parseInt(node.getEdgeHash());
			drawNode(x, y, radius);
		}
		return bufferedImage;
	}
	
	public boolean serializeImage(BufferedImage image, String path) throws Exception {
		ImageHelper.setImagePath(path);
		ImageHelper.storeImageAs(image, "PNG");
		return true;
	}
}
