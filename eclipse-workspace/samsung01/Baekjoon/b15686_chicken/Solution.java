package b15686_chicken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int M;
	static int[][] stores;
	static int[][] houses;
	static int storeCnt;
	static int houseCnt;
	
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		
		stores = new int[13][2];
		houses = new int[2*N][2];
		storeCnt = 0;
		houseCnt = 0;
		
		for(int r = 0; r < N ;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				int info = Integer.parseInt(st.nextToken());
				map[r][c] = info;
				if(info == 2) stores[storeCnt++] = new int[] {r, c};
				if(info == 1) houses[houseCnt++] = new int[] {r, c};
			}
		}
		
		int aliveStores = 1 << storeCnt;

		dfs(0, aliveStores, 0);
		
		System.out.println(min);
	}
	
	static void dfs(int depth, int prevStores, int maxActivated) {
		if(depth == M) {
			min = Math.min(min, getTotalChickenDistance(prevStores));
			return ;
		}
		for(int i = maxActivated; i < storeCnt; i ++) {
			int nextMax = Math.max(i, maxActivated);
			if((prevStores & (1 << i)) == 0) {
				dfs(depth + 1, prevStores | (1 << i), nextMax);
			}
			
		}
	}
	
	static int getTotalChickenDistance(int aliveStores) {
		int result = 0;
		
		for(int i = 0; i < houseCnt; i++) {
			int chickenDistance = Integer.MAX_VALUE;
			for(int j = 0; j < storeCnt; j++) {
				if((aliveStores & (1 << j)) > 0) {
					//탐색 대상임.
					int dr = Math.abs(houses[i][0] - stores[j][0]);
					int dc = Math.abs(houses[i][1] - stores[j][1]);

					chickenDistance = Math.min(dr + dc, chickenDistance);
				}
			}
			result += chickenDistance;
		}
		
		return result;
	}

}
