package main.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.List;
import java.util.Scanner;

import main.model.ModelManager;
import main.model.entity.EFSM;
import main.model.entity.User;

public class DotUtil {

	public static void dotFileWrite(String fileName, EFSM bm) throws IOException {
	
		final String OUT_PATH = "./out/";
		
		FileWriter fw = new FileWriter(new File(OUT_PATH + fileName + ".dot"));
		fw.write(bm.generateDot());
		fw.close();
	}
	
	private static void generateDotFrmDB() {

		List<User> users = ModelManager.getInstance().getAllUsers();

		for(User u : users) {
			int userId = u.getId();
			EFSM bm = u.getBehaviorModel();
			
			

			try {
				dotFileWrite( "User_" + String.valueOf(userId), bm);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void generateBMImg() {
		
		generateDotFrmDB();
		
		try {

			File dotFile[] = new File("./out/").listFiles();
			
			for(File f : dotFile) {
				
				String dotFileName = f.getPath();
				
				if(!dotFileName.contains(".dot")) continue;
				
				String outFileName = f.getName() + "_out.png";
				outFileName = "./img/" + outFileName.replace(".dot", "");
				
				final String cmd[] = {"/bin/sh",
						"dot" + " -Tpng " + dotFileName + " -o "  + outFileName};
				Process p = new ProcessBuilder(cmd).start();
				
				// SequenceInputStream은 여러개의 스트림을 하나의 스트림으로 연결해줌.
				SequenceInputStream seqIn = new SequenceInputStream(
						p.getInputStream(), p.getErrorStream());

				// 스캐너클래스를 사용해 InputStream을 스캔함
				Scanner s = new Scanner(seqIn);

				while (s.hasNextLine() == true) {
					// 표준출력으로 출력
					System.out.println(s.nextLine());
				}

				// 외부 프로그램 반환값 출력 (이 부분은 필수가 아님)
				System.out.println("Exit Code: " + p.exitValue());

			}

		
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
