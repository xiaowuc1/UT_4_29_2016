import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*; 
import java.util.*;
import java.util.regex.*;
/*
	  br = new BufferedReader(new FileReader("input.txt"));
	  pw = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
	  br = new BufferedReader(new InputStreamReader(System.in));
	  pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
 */


public class Fun {
	private static BufferedReader br;
	private static StringTokenizer st;
	private static PrintWriter pw;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		//int qq = 1;
		//int qq = Integer.MAX_VALUE;
		int qq = readInt();
		for(int casenum = 1; casenum <= qq; casenum++)	{
			int v = readInt();
			int e = readInt();
			LinkedList<Edge>[] edges = new LinkedList[v];
			for(int i = 0; i < v; i++) {
				edges[i] = new LinkedList<Edge>();
			}
			int[] from = new int[e];
			int[] to = new int[e];
			int[] fun = new int[e];
			int[] cost = new int[e];
			for(int i = 0; i < e; i++) {
				from[i] = readInt()-1;
				to[i] = readInt()-1;
				fun[i] = readInt();
				cost[i] = readInt();
				edges[from[i]].add(new Edge(to[i], fun[i], cost[i]));
			}
			double min = 0;
			double max = 100;
			final double INF = 1e20;
			for(int qqq = 0; qqq < 30; qqq++) {
				double mid = (min+max)/2;
				double[] dp = new double[v];
				Arrays.fill(dp, -INF);
				dp[0] = 0;
				for(int a = 0; a < v; a++) {
					for(int i = 0; i < e; i++) {
						if(dp[from[i]] == -INF) continue;
						dp[to[i]] = Math.max(dp[to[i]], dp[from[i]] + fun[i] - cost[i] * mid);
					}
				}
				LinkedList<Integer> q = new LinkedList<Integer>();
				boolean[] seen = new boolean[v];
				for(int i = 0; i < e; i++) {
					if(dp[from[i]] == -INF) continue;
					if(dp[from[i]] + fun[i] - cost[i] * mid > dp[to[i]] && !seen[to[i]]) {
						q.add(to[i]);
						dp[to[i]] = INF;
						seen[to[i]] = true;
					}
				}
				while(!q.isEmpty()) {
					int curr = q.removeFirst();
					for(Edge out: edges[curr]) {
						if(!seen[out.d] && dp[curr] + out.f - out.c * mid > dp[out.d]) {
							dp[out.d] = dp[curr] + out.f - out.c * mid;
							seen[out.d] = true;
							q.add(out.d);
						}
					}
				}
				if(dp[v-1] > 0) {
					min = mid;
				}
				else {
					max = mid;
				}
			}
			pw.printf("%.6f\n", min);
		}
		exitImmediately();
	}
	
	static class Edge {
		public int d, f, c;

		public Edge(int d, int f, int c) {
			super();
			this.d = d;
			this.f = f;
			this.c = c;
		}
		
	}
	
	private static void exitImmediately() {
		pw.close();
		System.exit(0);
	}

	private static long readLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	private static double readDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}

	private static int readInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	private static String nextLine() throws IOException  {
		if(!br.ready()) {
			exitImmediately();
		}
		st = null;
		return br.readLine();
	}

	private static String nextToken() throws IOException  {
		while(st == null || !st.hasMoreTokens())  {
			if(!br.ready()) {
				exitImmediately();
			}
			st = new StringTokenizer(br.readLine().trim());
		}
		return st.nextToken();
	}
}