import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(new Main().solve(1, 1, 2));
	}

	public int solve(int W, int H, int A) {
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < Math.sqrt(A) + 1; i++) {
			if (A % i == 0) {
				min = Math.min(min, getMin(W, H, i, A / i));
				min = Math.min(min, getMin(W, H, A / i, i));
			}
		}
		if (min == Integer.MAX_VALUE) {
			min = -1;
		}
		return min;
	}

	public int getMin(int W, int H, int a, int b) {
		if (W < a || H < b) {
			return Integer.MAX_VALUE;
		}
		int c = 0;
		while (W > a) {
			W = W / 2 + W % 2;
			c++;
		}
		while (H > b) {
			H = H / 2 + H % 2;
			c++;
		}
		return c;
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
