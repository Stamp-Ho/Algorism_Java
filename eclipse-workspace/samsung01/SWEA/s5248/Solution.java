package s5248;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] union;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		// TODO Auto-generated method stub
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			union = new int[N];
			for(int i = 0; i < N; i++)
				union[i] = i;

			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				makeUnion(Math.min(a, b), Math.max(a, b));
			}
			Set<Integer> set = new HashSet<>();
			for(int i = 0; i < N; i++)
				set.add(findUnion(i));
			
			sb.append("#").append(tc).append(" ").append(set.size()).append("\n");
		}
		System.out.println(sb);
	}
	static int findUnion(int a) {
		if(union[a] == a) return a;
		else return union[a] = findUnion(union[a]);
	}
	static void makeUnion(int a, int b) {
		int rootA = findUnion(a);
		int rootB = findUnion(b);
		if(rootA != rootB) {
			union[rootB] = rootA;
		}
	}
}
