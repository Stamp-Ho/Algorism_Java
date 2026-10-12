package s1251_intoOne;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static double E;
	static int[][] islands;
	static int[] unions;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		// TODO Auto-generated method stub
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= T; tc++) {
			long result = 0;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			islands = new int[N][2];
			unions = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				islands[i][0] = Integer.parseInt(st.nextToken());
				unions[i] = i;
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)
				islands[i][1] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			E = Double.parseDouble(st.nextToken());
			
			makeEdges();
			result = kruskal();
			
			result = (long) (result * E + 0.5);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		
	}
	
	static PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong((a) -> a[2]));
	
	static void makeEdges() {
		//음. node가 1000개면 1000 * 999 / 2 개의 간선이 있을 듯 하다.
		for(int i = 0; i < N; i++) {
			for(int j = i; j < N; j++) {
				long dx = Math.abs(islands[i][0] - islands[j][0]);
				long dy = Math.abs(islands[i][1] - islands[j][1]);
				long dist = dx * dx + dy * dy;
				pq.add(new long[] {i, j, dist});
			}
		}
	}
	
	static long kruskal() {
		long result = 0;
		while(!pq.isEmpty()) {
			long[] cur = pq.poll();
			int i = (int)cur[0];
			int j = (int)cur[1];
			long dist = cur[2];
			if(findUnion(i) == findUnion(j)) continue;
			makeUnion(i, j);
			result += dist;
		}
		
		return result;
	}
	
	static int findUnion(int x) {
		if(unions[x] == x) return x;
		return unions[x] = findUnion(unions[x]);
	}
	static void makeUnion(int x, int y) {
		unions[findUnion(y)] = findUnion(x);
	}
}
