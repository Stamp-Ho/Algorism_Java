package b1717_expression_of_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] root;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()) + 1;
		int M = Integer.parseInt(st.nextToken());
		
		root = new int[N];
		for(int i = 0; i < N; i++) root[i] = i;
		
		StringBuilder sb = new StringBuilder();
		for(int cm = 0; cm < M; cm++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			if(command == 0) {
				root[unionFind(node2)] = unionFind(node1);				
			}else {
				boolean result = unionFind(node1) == unionFind(node2);
				sb.append(result ? "YES\n" : "NO\n");
			}
			
		}
		System.out.println(sb);
	}
	
	public static int unionFind(int x) {
		if(root[x] == x)
			return x;
		else return root[x] = unionFind(root[x]);
	}
}
