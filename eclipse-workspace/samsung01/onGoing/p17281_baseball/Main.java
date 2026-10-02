package p17281_baseball;
//https://baekjoon.song-queue-ssfay11.cloud/problems/17281

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int score = 0;
		for(int tc = 0; tc < N; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int[] hitters = new int[9];
			for(int i = 0; i < 9; i ++) {
				hitters[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(hitters);
			
			int baseCnt = 0;
			int outCnt = 0;
			
			a: while(outCnt < 3) {
				for(int i = 0; i < 9; i++) {
					int cHit = hitters[8 - i];
					if(cHit > 0) {
						if(baseCnt + cHit >= 4) score ++;
						baseCnt += cHit;
					}else {
						//out
						outCnt ++;
						if(outCnt >= 3) break a;
					}
				}
			}
		}
		System.out.println(score);
	}
}
