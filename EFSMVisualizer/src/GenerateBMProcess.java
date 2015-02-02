import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Scanner;


public class GenerateBMProcess {
	
	public static void main(String[] args) {
	

		try {
			
			String currentPath = System.getProperty("user.dir") + "\\";
			System.out.println(currentPath);
			Runtime runTime = Runtime.getRuntime();
			
			final String cmd[] = {"cmd", "java", " -version"};
			Process p = //runTime.exec("cmd dot -Tpng simple.dot -o out.png");
					new ProcessBuilder(cmd).start();
							
			//"cmd.exe", "dot", "-Tpng", currentPath + "simple.dot", "-o", currentPath + "out.png").start();
		
			// SequenceInputStream은 여러개의 스트림을 하나의 스트림으로 연결해줌.
			SequenceInputStream seqIn = new SequenceInputStream(
					p.getInputStream(), p.getErrorStream());
 
			// 스캐너클래스를 사용해 InputStream을 스캔함
//			Scanner s = new Scanner(process.getInputStream());
//			Scanner s = new Scanner(process.getErrorStream());
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
