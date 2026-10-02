package b2667_houseComplex;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[][] map;
		int complexCnt = 0;
		
		int N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		int[] buildingCnts = new int[(N * N) / 2 + 1];
		for(int i = 0; i < (N * N) / 2 + 1; i++) buildingCnts[i] = 0;

		for(int r = 0; r < N; r++) {
			String input = br.readLine();
			for(int c = 0; c < N; c++)
				map[r][c] = input.charAt(c) - '0';
		}
		
		Queue<int[]> que = new LinkedList<int[]>();
		
		int[] dc = {1, 0, -1, 0};
		int[] dr = {0, 1, 0 , -1};
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] > 0) {
					complexCnt += 1;
					que.add(new int[] {r, c});
					map[r][c] = 0;
					
					while(!que.isEmpty()) {
						int[] curPos = que.poll();
						int cr = curPos[0];
						int cc = curPos[1];
						buildingCnts[complexCnt - 1] += 1;
						
						for(int d = 0; d < 4; d++) {
							int nr = cr + dr[d];
							int nc = cc + dc[d];
							if(nr < 0 || nc < 0 || nc >= N || nr >= N) continue;
							
							if(map[nr][nc] > 0) {
								map[nr][nc] = 0;
								que.add(new int[] {nr, nc});
							}
						}
					}
				}
				
				
			}
		}
		
		System.out.println(complexCnt);
		
		Arrays.sort(buildingCnts);
		
		int i = 0;
		int oC = 0;
		while(oC < complexCnt) {
			if(buildingCnts[i] != 0) {
				System.out.println(buildingCnts[i]);
				
				oC ++;
			}
			i++;
		}
		
		
	}
}
