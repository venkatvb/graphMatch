package helper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.Constants;
import logger.Log;

public class ImageHelper {
	
	public static String imagePath = "";

	public static void setImagePath(String path) {
		imagePath = path;
	}
	
	public static String getImagePath() {
		return ImageHelper.imagePath;
	}
	
	public static boolean isValidImagePath() {
		if(imagePath.equals("")) {
			return false;
		}
		return true;
	}
	
	public static boolean isValidImageFormat(String imageFormat) {
		for(String format : Constants.supportedImageFormats) {
			if(format.equalsIgnoreCase(imageFormat)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean storeImageAs(BufferedImage image, String imageFormat) throws Exception {
		if(isValidImagePath() == false) {
			throw new ImagePathNotValidException("Image path not set");
		}
		if(isValidImageFormat(imageFormat) == false) {
			throw new UnknownImageFormatException("Image Format not recognized");
		}
		try {
			ImageIO.write(image, imageFormat, new File(imagePath));
			Log.info("Image stored int the " + imagePath + " in the format " + imageFormat);
		} catch (IOException e) {
			Log.error("Canno't store the image in the path " + imagePath + " with the format " + imageFormat + " " + e.getMessage());
		}
		return true;
	}
}
