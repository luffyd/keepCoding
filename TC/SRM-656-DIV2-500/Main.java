import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static int A;

	public static int B;

	public static int m;

	public static int t;

	public static void main(String[] args) throws FileNotFoundException {
		int data[] = new int[] { 3, 6, 10, 9, 2 };
		System.out.println(new Main().expectedDeliciousness(data));
	}

	public double expectedDeliciousness(int[] d) {
		double eD = 0;
		boolean b[] = new boolean[d.length];
		for (int i = 0; i < d.length; i++) {
			b[i] = true;
			eD += getEd(b, d, d[i], 1d / d.length, d.length - 1, i);
			b[i] = false;
		}
		return eD;
	}

	public double getEd(boolean b[], int d[], double cD, double p, int remaining, int index) {
		double eD = 0;
		if (remaining == 0) {
			return p * cD;
		}
		for (int i = 0; i < d.length; i++) {
			if (!b[i]) {
				if (i < index) {
					b[i] = true;
					eD += getEd(b, d, d[i] + cD, p * 1d / remaining, remaining - 1, i);
					b[i] = false;
				}
				else {
					eD += (p * 1d / remaining) * cD;
				}
			}
			else {
				continue;
			}
		}
		return eD;
	}
}

class InputReader {
	public BufferedReader reader;

	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
		tokenizer = null;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

}
