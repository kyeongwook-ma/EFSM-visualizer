package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.List;
import java.util.Scanner;

import model.EFSM;
import model.User;
import model.UserBehaviorModels;
import model.db.DBHelper;
import model.db.UserLogGetter;

public class DotUtil {

	private static void dotFileWrite(String fileName, DotLangGenerator generator) throws IOException {
	
		final String OUT_PATH = "./out/";
		
		FileWriter fw = new FileWriter(new File(OUT_PATH + fileName + ".dot"));
		fw.write(generator.generateDot());
		fw.close();
	}
	
	public static void generateDotFrmDB() {
		DBHelper.getInstance().constructModel(new UserLogGetter());

		List<User> users = UserBehaviorModels.getInstance().getAllUsers();

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

	public static void generateImgFrmDot() {
		try {

			final String cmd[] = {"cmd", "/c", "dot" +
					" -Tpng " + "./out/" +"User_1.dot" +  
					" -o "  + "./img/" +" out.png"};
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
			System.exit(p.exitValue()); // 외부 프로그램의 반환값을, 이 자바 프로그램 자체의 반환값으로 삼기

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
