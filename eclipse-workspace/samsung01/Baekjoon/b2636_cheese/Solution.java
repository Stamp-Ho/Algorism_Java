package b2636_cheese;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt( st.nextToken() );
		int C = Integer.parseInt( st.nextToken() );
		
		int[][] map = new int[R][C];
		
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int[] dirC = {1, 0, -1, 0};
		int[] dirR = {0, 1, 0, -1};
		
		Queue<int[]> que = new LinkedList<int[]>();
		
		que.add(new int[]{0, 0});
		
		int result = 0;
		int time = 0;
		while(true) {
		
			boolean[][] visited = new boolean[R][C];
			while(!que.isEmpty()) {
				int[] curPos = que.poll();
				int curR = curPos[0];
				int curC = curPos[1];
				 
				for(int d = 0; d < 4; d++) {
					int nextR = curR + dirR[d];
					int nextC = curC + dirC[d];
					if(nextR < 0 || nextC < 0 || nextR >= R || nextC >= C) continue;
					
					if(map[nextR][nextC] == 0) {
						if(!visited[nextR][nextC]) {
							visited[nextR][nextC] = true;
							que.add(new int[] {nextR, nextC});
						}
					}
					else {
						map[nextR][nextC] = 2;
					}
				}
			}
			que.add(new int[]{0, 0});
			

			for(int r = 0; r < R; r++) { 
				for(int c = 0; c < C; c++) {
					System.out.print(map[r][c] + " ");
				}
				System.out.println();
			}
			result = 0;
			boolean isLast = true;
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					if(map[r][c] == 2) {
						result ++;
						map[r][c] = 0;
					}
					if(map[r][c] == 1)
						isLast = false;
					if(map[r][c] == -1)
						map[r][c] = 0;
					}
			}
			time ++;

			
			if(isLast) break;
			
			
		}
		
		System.out.println(time);
		System.out.println(result);
	}
	

}
