import java.io.*;

public class TestCaseGeneration {
	public static void main(String[] args) {
		int n = 10;
		System.out.println(n);
		
		for(int i = 0; i < n; i++) {
			int x = (int)(Math.random()*50);
			if((int)(Math.random()*2) == 1) x *= -1;
			System.out.println(0 + " " + x);
		}
		
		System.out.println(0);
	}
}
