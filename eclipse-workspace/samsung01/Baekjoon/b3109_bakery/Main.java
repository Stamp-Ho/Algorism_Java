package b3109_bakery;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] map;
	static boolean[][] visited;
	static int R;
	static int C;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new boolean[R][C]; // true = blank | false = blocked
		visited = new boolean[R][C];
		
		for(int r = 0; r < R; r++) {
			String s = br.readLine();
			for(int c = 0; c < C; c++)
				map[r][c] = s.charAt(c) == '.';
		}
		
		int result = 0;
		for(int r = 0; r < R; r++) {
			if(dfs(0, r))
				result += 1;
		}
		
		System.out.println(result);
		
		
	}
	
	static boolean dfs(int prevCol, int prevRow) {
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++)
				System.out.print(map[r][c] ? "." : 'x');
			System.out.println();
		}
		System.out.println();
		
		for(int i = -1; i <= 1; i++) {
			if(prevRow + i >= 0 && prevRow + i < R && map[prevRow + i][prevCol + 1]) {
				//?˜¤ë¥¸ìª½?— ê°? ê¸¸ì´ ?žˆ?Œ
				map[prevRow + i][prevCol + 1] = false;//blocked;
				if(prevCol + 1 == C - 1) return true;
				
				if(dfs(prevCol + 1, prevRow + i)) {
					return true;
				}else {
					map[prevRow + i][prevCol + 1] = true;
				}
			}
		}
		//ê°? ê¸¸ì„ ëª»ì°¾?Œ
		return false;
	}
	
	
}
