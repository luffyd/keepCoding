import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static int dp[][];

	public static boolean powerDefeatsVillan[][];

	public static int vd[];

	public static int pa[];

	public static int pc[];

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);

		while (true) {
			int pn = in.nextInt();
			int vn = in.nextInt();
			int E = in.nextInt();
			if (pn == 0 && vn == 0 && E == 0) {
				break;
			}

			HashMap<String, Integer> powerMap = new HashMap<String, Integer>();
			pa = new int[pn];
			pc = new int[pn];
			for (int i = 0; i < pn; i++) {
				powerMap.put(in.next(), i);
				pa[i] = in.nextInt();
				pc[i] = in.nextInt();
			}

			powerDefeatsVillan = new boolean[pn][vn];
			vd = new int[vn];
			for (int i = 0; i < vn; i++) {
				in.next();
				vd[i] = in.nextInt();
				String[] powers = in.next().split(",");
				for (int j = 0; j < powers.length; j++) {
					int powerIndex = powerMap.get(powers[j]);
					if (pa[powerIndex] >= vd[i]) {
						powerDefeatsVillan[powerMap.get(powers[j])][i] = true;
					}
				}
			}

			dp = new int[pn + 1][vn + 1];
			for (int i = 0; i < pn + 1; i++) {
				Arrays.fill(dp[i], -1);
			}
			int minEnergy = getMinimumEnergyToDefeat(pn, vn);
			if (minEnergy <= E) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
		}
	}

	public static int getMinimumEnergyToDefeat(int p, int v) {
		if (p < 0 || v < 0) {
			// fuck you
			return Integer.MAX_VALUE / 2;
		}

		if (v == 0) {
			// jujubi
			dp[p][v] = 0;
			return dp[p][v];
		}

		if (p == 0) {
			dp[p][v] = Integer.MAX_VALUE / 2;
			return dp[p][v];
		}

		if (dp[p][v] != -1) {
			return dp[p][v];
		}
		dp[p][v] = getMinimumEnergyToDefeat(p - 1, v);
		if (powerDefeatsVillan[p - 1][v - 1]) {
			dp[p][v] = Math.min(getMinimumEnergyToDefeat(p - 1, v - 1) + pc[p - 1], dp[p][v]);
		}

		return dp[p][v];
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
