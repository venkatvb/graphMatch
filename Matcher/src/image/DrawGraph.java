package image;

import graph.Graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawGraph {
	public static final int WIDTH = 2000;
	public static final int HEIGHT = 2000;
	public void draw(Graph graph) {
		BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bufferedImage.createGraphics();
		Font font = new Font("TimesRoman", Font.BOLD, 20);
		g.setFont(font);
		g.setBackground(Color.RED);
		String message = "Graph image";
		FontMetrics fontMetrics = g.getFontMetrics();
		int stringWidth = fontMetrics.stringWidth(message);
		int stringHeight = fontMetrics.getAscent();
		g.setPaint(Color.BLACK);
		g.drawString(message, (WIDTH - stringWidth) / 2, HEIGHT / 2 + stringHeight / 4);
		g.drawArc(10, 01, 10, 10, 10, 10);
		g.drawOval(20, 20, 20, 20);
		try {
			ImageIO.write(bufferedImage, "PNG", new File("F:\\Graph Databases\\Images\\graphimage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
