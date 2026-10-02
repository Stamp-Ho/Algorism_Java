package s7465_ChangYong;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] root = new int[N];
			for(int i = 0; i < N; i++) root[i] = i;
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken()) - 1;
				int e = Integer.parseInt(st.nextToken()) - 1;
				
				union(root, s, e);
				
			}
			int cnt = 0;
			boolean[] visited = new boolean[N];
			
			for(int i = 0; i < N; i++) {
				int rootNode = unionFind(root, i);
				if(!visited[rootNode]) {
					//해당 그룹은 처음 만남
					visited[rootNode] = true;
					cnt ++;
				}
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
			
		}
		System.out.println(sb);
	}
	
	static void union(int[] root, int s, int e) {
		root[unionFind(root, Math.max(s, e))] = unionFind(root, Math.min(s, e));
	}
	
	static int unionFind(int[] root, int x) {
		if(root[x] == x) return x;
		else return root[x] = unionFind(root, root[x]);
	}
}
