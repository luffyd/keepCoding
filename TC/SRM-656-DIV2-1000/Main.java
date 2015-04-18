import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int LIMIT = 1000000007;

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(new Main().countPermutations(5, new int[] { 3 }));
	}

	public int countPermutations(int N, int[] pos) {
		int a[] = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = i + 1;
		}

		// initialization!!
		boolean checker[] = new boolean[N];
		for (int i = 0; i < pos.length; i++) {
			checker[pos[i] - 1] = true;
		}

		int count = 0;
		if (checkForCondition(a, checker)) {
			count++;
		}
		while (nextPermutation(a)) {
			if (checkForCondition(a, checker)) {
				count++;
				count %= LIMIT;
			}
		}
		return count;
	}

	public boolean checkForCondition(int a[], boolean checker[]) {
		for (int i = 0; i < a.length - 1; i++) {
			if ((checker[i] && a[i] > a[i + 1]) || (!checker[i] && a[i] < a[i + 1])) {
				return false;
			}
		}
		return true;
	}

	public boolean nextPermutation(int a[]) {
		int i = a.length - 2;
		while (i >= 0) {
			if (a[i] < a[i + 1]) {
				break;
			}
			i--;
		}
		if (i == -1) {
			return false;
		}
		int j = a.length - 1;
		while (j > i) {
			if (a[i] < a[j]) {
				break;
			}
			j--;
		}
		int t = a[j];
		a[j] = a[i];
		a[i] = t;
		Arrays.sort(a, i + 1, a.length);
		return true;
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
