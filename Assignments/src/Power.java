import java.util.Scanner;

public class Power {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int base = scan.nextInt();
		int exp = scan.nextInt();
		System.out.println(power(base, exp));
		
	}

	public static long power ( int base , int exp ) {
		
		long pow = 1;
		for(int i = 0; i < exp; i++) {
			pow *= base;
		}
		return pow;
		
	}
	
}
