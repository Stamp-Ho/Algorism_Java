package s5110_mergeSequence;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
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
			sb.append("#").append(tc);
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			LinkedList<Integer> seq = new LinkedList<>();
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				if(i == 0) {
					for(int j = 0; j < M; j++){
						seq.add(Integer.parseInt(st.nextToken()));
					}
				}else {
					int start = Integer.parseInt(st.nextToken());
					
					boolean added = false;
					int index = 0;
					for(int value : seq) {
						if(value > start) {
							seq.add(index, start);
							for(int j = 1; j < M; j++) {
								seq.add(++index, Integer.parseInt(st.nextToken()));
							}
							added = true;
							break;
						}
						index ++;
					}
					if(!added) {
						seq.add(start);
						for(int j = 1; j < M; j++) {
							seq.add(Integer.parseInt(st.nextToken()));
						}
					}
				}
			}
			Stack<Integer> stack = new Stack<>();
			for(int value : seq) stack.add(value);
			
			int size = stack.size();
			for(int i = 0; i < Math.min(10, size); i++)
				sb.append(" ").append(stack.pop());
			
			
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
