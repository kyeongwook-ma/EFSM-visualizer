package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageCasher {

	private static final String absPath = "./res";
	private static HashMap<String, Image> imageMap;

	static {
		imageMap = new HashMap<String,Image>();

		File directory = new File("./res");

		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				register(file.getPath());
			}
		}
	}


	public static void register(String fileName){
		Image img = null;

		try {
			img = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(img != null)
			imageMap.put(fileName, img);

	}

	public static Image getImage(String fileName) {
		Image img = imageMap.get(absPath + "/" + fileName);
		assert img != null;
		return img;
	}


}

