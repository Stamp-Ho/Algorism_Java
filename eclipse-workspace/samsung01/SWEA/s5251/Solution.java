package s5251;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		// TODO Auto-generated method stub
		
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			
			int result = 0;
			
			st = new StringTokenizer(br.readLine());
			int E = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			int[] distance = new int[E + 1];
			for(int i = 1; i < E + 1; i++) distance[i] = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i ++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int newDistance = Integer.parseInt(st.nextToken());
				
				if(distance[start] + newDistance < distance[end])
					distance[end] = distance[start] + newDistance;
			}
			result = distance[E];
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	

}
