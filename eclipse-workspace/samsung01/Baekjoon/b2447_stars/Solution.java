package b2447_stars;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static String[] result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		result = new String[N];
		for(int i = 0; i < N; i++)
			result[i] = "";
		
		print(N / 3, 0, false);
		
		for(int i = 0; i < N; i++)
			System.out.println(result[i]);
		
	}

	public static void print(int size, int startIndex, boolean isBlank) {
		//27*27?˜ ê²½ìš°, size = 9
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(size == 1)
					if(i * j == 1 || isBlank)
						result[startIndex + i] += " ";
					else
						result[startIndex + i] += "*";
				else {
					if(i * j == 1 || isBlank)
						print(size / 3, startIndex + i * size, true);
					else
						print(size / 3, startIndex + i * size, false);
				}
			}
		}
		
	}

}
