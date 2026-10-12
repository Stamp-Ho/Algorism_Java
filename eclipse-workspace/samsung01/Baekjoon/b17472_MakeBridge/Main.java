package b17472_MakeBridge;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] map;
	static int groupIndex = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		makeGroups(); // BFS로 각 그룹을 하나의 숫자로 엮는다.
//		for(int r = 0; r < N; r++)
//			System.out.println(Arrays.toString(map[r]));
		
		findCandidates();
		int result = kruskal();
		System.out.println(result);
	}
	
	static int[] dr = new int[] {0, 1, 0, -1}; 
	static int[] dc = new int[] {1, 0, -1, 0};
	
	static Queue<int[]> que = new LinkedList<int[]>();
	
	static void makeGroups() {
		for(int r = 0; r < N; r++)
			for(int c = 0; c < M; c++)
				if(map[r][c] == 1) {
					que.add(new int[] {r, c});
					updateGroup(groupIndex);
					groupIndex ++;
				}
	}
	static void updateGroup(int groupIndex) {
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int r = cur[0];
			int c = cur[1];
			map[r][c] = groupIndex + 2;
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] != 1) continue;
				que.add(new int[] {nr, nc});
			}
		}
	}


	static PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);
	static void findCandidates() {		
		for(int r = 0; r < N; r++)
			for(int c = 0; c < M; c++) {
				if(map[r][c] > 0) {
					//섬에서 길 찾기
					finding: for(int d = 0; d < 2; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						// 오른쪽, 아래쪽만 보니까 0은 안봐도 되고, 그 칸이 바다가 아니면 다음.
						if(nr >= N || nc >= M || map[nr][nc] != 0) continue;
						int length = 1;
						while(true) {
							nr = r + dr[d] * length;
							nc = c + dc[d] * length;
							if(nr >= N || nc >= M) continue finding;
							if(map[nr][nc] != 0) {
								// 연결됨!
								int minGroup = Math.min(map[r][c], map[nr][nc]) - 2;
								int maxGroup = Math.max(map[r][c], map[nr][nc]) - 2;
								//System.out.println(minGroup + 2 + " " + (maxGroup + 2) + " " + (length - 1));
								if(minGroup != maxGroup && length > 2)
									pq.add(new int[] {minGroup, maxGroup, length - 1});
								break;
							}
							length ++;
						}
					}
				}
			}
	}
	
	static int[] unions;
	static int kruskal() {
		int result = 0;
		unions = new int[groupIndex];
		for(int i = 0; i < groupIndex; i++) unions[i] = i;
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			//System.out.println(Arrays.toString(cur));
			if(findUnion(cur[0]) != findUnion(cur[1])) {
				//해당 노드를 이으면 됨.
				makeUnion(cur[0], cur[1]);
				result += cur[2];
			}
			
		}
		if(result == 0) return -1;
		for(int i = 0; i < groupIndex; i++) {
			if(findUnion(i) != 0)
				return -1;
		}
		
		return result;
	}
	
	static int findUnion(int x) {
		if(unions[x] == x) return x;
		return unions[x] = findUnion(unions[x]);
	}
	
	static void makeUnion(int x, int y) {
		unions[findUnion(Math.max(x, y))] = findUnion(Math.min(x, y));
	}
}
