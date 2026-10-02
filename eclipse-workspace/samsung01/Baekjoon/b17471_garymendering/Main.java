package b17471_garymendering;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N;
		int[] pops;
		ArrayList<Integer>[] edges;
		N = Integer.parseInt(st.nextToken());
		pops = new int[N];
		edges = new ArrayList[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			pops[i] = Integer.parseInt(st.nextToken());
			edges[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			for(int j = 0; j < K; j++)
				edges[i].add(Integer.parseInt(st.nextToken()) - 1);
		}
		
		int result = Integer.MAX_VALUE;
		for(int bm = 1; bm < (1 << N) - 1; bm++) { // 0000 Í≥? 1111 Î∞∞Ï†ú.
			int group1 = 0;
			int group2 = 0;
			Queue<Integer> que1 = new LinkedList<Integer>();
			Queue<Integer> que2 = new LinkedList<Integer>();
			for(int i = 0 ; i < N; i ++) {
				if((bm & (1 << i)) > 0) {
					// ÎΩëÏïò?ùå
					if(que1.isEmpty()) que1.add(i);
					group1 |= 1 << i;
				}else {
					if(que2.isEmpty()) que2.add(i);
					group2 |= 1 << i;
				}
			}
			
			int sum1 = 0;
			int visited = (1 << que1.peek());
			while(!que1.isEmpty()) {
				int currentNode = que1.poll();
				sum1 += pops[currentNode];
				for(int i = 0; i < edges[currentNode].size(); i++) {
					int nextNode = edges[currentNode].get(i);
					if((group1 & (1 << nextNode)) != 0 && (visited & (1 << nextNode)) == 0) {
						//Í∑∏Î£π?óî ?Üç?ïò?Çò, Î∞©Î¨∏?ïú ?†Å ?óÜ?ùå
						visited |= 1 << nextNode;
						que1.add(nextNode);
					}
				}
			}
			if(visited != group1) continue;

			int sum2 = 0;
			visited = (1 << que2.peek());
			while(!que2.isEmpty()) {
				int currentNode = que2.poll();
				sum2 += pops[currentNode];
				for(int i = 0; i < edges[currentNode].size(); i++) {
					int nextNode = edges[currentNode].get(i);
					if((group2 & (1 << nextNode)) != 0 && (visited & (1 << nextNode)) == 0) {
						//Í∑∏Î£π?óî ?Üç?ïò?Çò, Î∞©Î¨∏?ïú ?†Å ?óÜ?ùå
						visited |= 1 << nextNode;
						que2.add(nextNode);
					}
				}
			}
			if(visited != group2) continue;
			
			result = Math.min(result, Math.abs(sum1 - sum2));
			
		}
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
		
		
	}
	
}
