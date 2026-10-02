package s5215_HamburgerDiet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			int result = 0;
			
			Map<Integer, Integer> comb = new HashMap();
			comb.put(0, 0);
			for(int i = 0; i < N; i ++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int cal = Integer.parseInt(st.nextToken());
				Map<Integer, Integer> nextComb = new HashMap<>();
                
                for (Map.Entry<Integer, Integer> entry : comb.entrySet()) {
                	int prevCal = entry.getKey();
                	int prevScore = entry.getValue();
                	
                	int nextCal = prevCal + cal;
					if(nextCal > L) continue;
					
					int nextScore = prevScore + score;
					if (comb.containsKey(nextCal)) {
                        nextScore = Math.max(nextScore, comb.get(nextCal));
                    }
                    if (nextComb.containsKey(nextCal)) {
                        nextScore = Math.max(nextScore, nextComb.get(nextCal));
                    }
                    
                    nextComb.put(nextCal, nextScore);
                    result = Math.max(result, nextScore);
				}
                comb.putAll(nextComb);
			}
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}

}
