package s1952_swimmingPool;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int day, month, month3, year;
	static int[] days = new int[12];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		// TODO Auto-generated method stub
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= T; tc ++) {
			st = new StringTokenizer(br.readLine());
			int result = 0;
			
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			month3 = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < 12; i++)
				days[i] = Integer.parseInt(st.nextToken());
			
			int[] prices = new int[12];

			for (int i = 0; i < 12; i++) {
			    int current = Math.min(days[i] * day, month);

			    int oneMonth = (i > 0 ? prices[i - 1] : 0) + current;

			    int threeMonths = (i >= 3 ? prices[i - 3] : 0) + month3;

			    prices[i] = Math.min(oneMonth, threeMonths);
			}
			if(prices[11] > year)
				result = year;
			else
				result = prices[11];
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

}
