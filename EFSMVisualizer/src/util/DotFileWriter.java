package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DotFileWriter {
	

	public static void write(String fileName, DotLangGenerator generator) throws IOException {
	
		final String OUT_PATH = "./out/";
		
		FileWriter fw = new FileWriter(new File(OUT_PATH + fileName + ".dot"));
		fw.write(generator.generateDot());
		fw.close();
	}
}
