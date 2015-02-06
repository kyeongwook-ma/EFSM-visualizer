package test;

import java.awt.image.BufferedImage;

import junit.framework.Assert;
import model.ImageCacher;

import org.junit.Test;

import util.DotUtil;

public class TestImageCacher {
	
	@Test
	public void testInitImgCacher() {
		ImageCacher.load();
	}
	
	@Test
	public void testImgLoad() {
		
		DotUtil.generateBMImg();
		
		try {
			BufferedImage image = ImageCacher.getImage("User_1.dot");
			Assert.assertEquals(false, image == null);
			System.out.println("loaded");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
