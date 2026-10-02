package s1767_prosessor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static ArrayList<int[]> inside;
	static int N;
	static int[][] pros;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			inside = new ArrayList<int[]>();
			pros = new int[N][N];
			
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < N; c++) {
					pros[r][c] = Integer.parseInt(st.nextToken());
					if(r == 0 || c == 0 || r == N - 1 || c == N - 1 || pros[r][c] == 0) {
						//do nothing
					}else {
						inside.add(new int[] {r, c});
						pros[r][c] = inside.size();
					}
				}
			}
			maxC = 0;
			minL = Integer.MIN_VALUE;
			dfs(0, 0, 0, pros);
			
			System.out.println("#" + tc + " " + minL);
		}
		
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static int maxC = 0;
	static int minL = Integer.MIN_VALUE;
	static void dfs(int depth, int connected, int length, int[][] map) {
		if(maxC < connected) {
			maxC = connected;
			minL = length;
		}else if(maxC == connected && length < minL) {
			minL = length;
		}
		if(depth == inside.size() || inside.size() - depth + connected < maxC) return ;
		
		
		int[] curPos = inside.get(depth);
		int r = curPos[0];
		int c = curPos[1];
		a: for(int d = 0; d < 4; d++) {
			int[][] tempMap = new int[N][N];
			for(int i = 0; i < N; i++)
				tempMap[i] = Arrays.copyOf(map[i], map[i].length);
			
			int dist = 1;
			while(true) {
				int nr = r + dr[d] * dist;
				int nc = c + dc[d] * dist++;
				if(checkOutside(nr, nc, N, N)) break;
				if(tempMap[nr][nc] > 0) {
					continue a; //길이 막힘
				}
				tempMap[nr][nc] = depth + 1;
			}
			// 현재 방향으로 연결 가능.
			dfs(depth + 1, connected + 1, length + dist - 2, tempMap);
			
			if(dist == 2) return; // 이보다 작은 길은 없고, 큰 길은 선택 이유가 없음. 연결하지 않을 이유도 없음. 그만.
			
		}
		
		//연결 못했음
		dfs(depth + 1, connected, length, map);
		
		
		
	}
	static boolean checkOutside(int r, int c, int R, int C) {
		return r < 0 || c < 0 || r >= R || c >= C;
	}
}
