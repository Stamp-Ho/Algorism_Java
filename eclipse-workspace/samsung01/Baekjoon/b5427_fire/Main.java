package b5427_fire;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		Queue<int[]> escapeQue = new LinkedList<int[]>();
		Queue<int[]> fireQue = new LinkedList<int[]>();

		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		a: for(int test_case = 0; test_case < T; test_case++) {
			escapeQue.clear();
			fireQue.clear();
			st = new StringTokenizer(br.readLine());
			
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[h][w];
			for(int r = 0; r < h; r++) {
				String s = br.readLine();
				for(int c = 0; c < w; c++) {
					char input = s.charAt(c);
					map[r][c] = input;
					if(input == '*')
						fireQue.add(new int[] {r, c});
					else if(input == '@')
						escapeQue.add(new int[] {r, c, 0});
				}
			}
			
			while(!escapeQue.isEmpty()) {
				
				// 불 먼저 번지기
				int fireQueSize = fireQue.size();
				for(int i = 0; i < fireQueSize; i++) {
					int[] curFire = fireQue.poll();
					int cFR = curFire[0];
					int cFC = curFire[1];

					for(int d = 0; d < 4; d++) {
						int nFR = cFR + dr[d];
						int nFC = cFC + dc[d];
						
						if(nFR < 0 || nFC < 0 || nFR >= h || nFC >= w) continue;
						
						if(map[nFR][nFC] != '#' && map[nFR][nFC] != '*') {
							//해당 영역이 벽이 아니고 불이 아님.
							map[nFR][nFC] = '*';
							fireQue.add(new int[] {nFR, nFC});
						}
					}
				}
				
				//불 번졌으니, 이동해보기
				
				int escapeQueSize = escapeQue.size();
				for(int i = 0; i < escapeQueSize; i++) {
					int[] curEsc = escapeQue.poll();
					for(int d = 0; d < 4; d++) {
						int nFR = curEsc[0] + dr[d];
						int nFC = curEsc[1] + dc[d];
						
						if(nFR < 0 || nFC < 0 || nFR >= h || nFC >= w) {
							System.out.println(curEsc[2] + 1);
							continue a;
						}
						
						if(map[nFR][nFC] == '.') {
							map[nFR][nFC] = (char) ('0' + curEsc[2] + 1);
							escapeQue.add(new int[] {nFR, nFC, curEsc[2] + 1});
						}
					}
				}
				
			}
			
			System.out.println("IMPOSSIBLE");
			
			
		}
	}
}
