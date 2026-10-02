package b1197_MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		ArrayList<int[]>[] edges = new ArrayList[V];
		for(int i = 0; i < V; i++)
			edges[i] = new ArrayList<int[]>();
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			edges[s].add( new int[] {e, w} );
			edges[e].add(new int[] {s, w});
		}

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((e1, e2)-> e1[1] - e2[1]);
		
		pq.add(new int[] {0, 0});
		boolean[] visited = new boolean[V];
		int cnt = 0;
		int result = 0;
		while(cnt < V) {
			
			while(true) {
				int[] cur = pq.poll();
				int curStart = cur[0];
				int curWeight = cur[1];
				if(!visited[curStart]) {
					result += curWeight;
					cnt ++;
					visited[curStart] = true;
					for(int i = 0; i < edges[curStart].size(); i++)
						pq.add(edges[curStart].get(i));
					break;
				}
				
			}
		}
		
		System.out.println(result);
		
	}
}
