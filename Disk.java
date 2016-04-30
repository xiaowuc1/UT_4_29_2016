import java.io.*;
import java.util.*;
public class Disk {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int qq = Integer.parseInt(br.readLine());
		while(qq-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int diskR = Integer.parseInt(st.nextToken());
			double r = Math.hypot(x,y);
			System.out.printf("%.6f\n", Math.PI * diskR * diskR * (2 * Math.log(r)));
		}
	}

}
