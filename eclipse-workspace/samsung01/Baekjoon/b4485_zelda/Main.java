package b4485_zelda;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		int test_case = 1;
		int N = -1;
		while(N != 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N == 0) return;
			
			int[][] map = new int[N][N];
			int[][] cnts = new int[N][N];

			PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> a[2] - b[2]);
			
			int result = 0;
			for(int i = 0; i < N; i ++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					cnts[i][j] = Integer.MAX_VALUE;
				}
			}
			
			que.add(new int[] {0, 0, map[0][0]});
			
			a: while(!que.isEmpty()) {
				int[] current = que.poll();
				
				int r = current[0];
				int c = current[1];
				int prevSum = current[2]; 
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
					
					int nextSum = prevSum + map[nr][nc];
					
					if(nr == N - 1 && nc == N - 1) {
						result = nextSum;
						break a;
					}
					if(cnts[nr][nc] > nextSum) {
						cnts[nr][nc] = nextSum;
						que.add(new int[]{nr, nc, nextSum});
					}
				}
				
			}
			
			
			
			System.out.println("Problem " + test_case++ + ": " + result);
		}
	}

}
