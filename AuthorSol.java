import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/*

k is always <= n
happiness of a friend = h[i] = max + min 
total = h[0] + h[1] + ... + h[n-1]
n = w[0] + w[1] + ... + w[k-1]
 
the max and min can be the same number

1
11 3
2 10 11 11 12 12 13 19 20 23 23
2 3 6

23 20
23 19 13
12 12 11 10 2
= 93

23 20
23 13 12
19 12 11 11 10 2
= 99

23 23
20 19 13
12 ... 2
= 93

last k numbers
23 19
23 13 12
20 ... 2
= 99

1
4 3
1000000000 1000000000 1000000000 1000000000
1 1 2

 */

public class AuthorSol {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt(), k = fs.nextInt();
			long[] a = fs.readLongArray(n);
			long[] w = fs.readLongArray(k);
			shuffleSort(a);
			shuffleSort(w);
			for (int i = 0; i < k / 2; i++) {
				long temp = w[i];
				w[i] = w[k-i-1];
				w[k-i-1] = temp;
			}
			ArrayList<Long>[] arr = new ArrayList[k];
			for (int i = 0; i < k; i++) {
				arr[i] = new ArrayList<>();
			}
			int ind = 0;
			for (int i = 0; i < n - k; i++) {
				while (w[ind] == arr[ind].size() + 1) {
					ind++;
				}
				arr[ind].add(a[i]);
			}
			long ans = 0;
			ind = 1;
			for (int i = 0; i < k; i++) {
				ans += a[n-i-1];
				if (arr[i].size() > 0) {
					ans += arr[i].get(0);
				} else {
					ans += a[n-ind];
					ind++;
				}
			}
			out.println(ans);
		}
		out.close();
	}
	
	static final Random rnd = new Random();
	
	static void shuffleSort(long[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newIndex = rnd.nextInt(n);
			long temp = a[newIndex]; //change this
			a[newIndex] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
