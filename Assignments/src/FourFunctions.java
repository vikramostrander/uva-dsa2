import java.util.Scanner;
import java.lang.Math;

public class FourFunctions {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int func = s.nextInt();
		if(func == 1 || func == 2 || func == 3 || func == 4) {
			double[] arr = new double[5];
			for(int i = 0; i < 5; i++) {
				arr[i] = s.nextInt();
			}
			if(func == 1) System.out.printf("%.2f\n",average(arr));
			if(func == 2) System.out.println(median(arr));
			if(func == 3) System.out.printf("%.2f\n",stddev(arr));
			if(func == 4) System.out.println(mode(arr));
		}
	}
	
	public static double average(double[] arr) {
		double avg = 0;
		for(double i : arr) {
			avg += i;
		}
		return avg/5;
	}
	
	public static double median(double[] arr) {
		int m1 = 0, m2 = 0, m3 = 0;
		for(int i = 0; i < 5; i++) {
			if(arr[m1] < arr[i]) m1 = i;
			if(arr[m3] > arr[i]) m3 = i;
		}
		m2 = m3;
		for(int i = 0; i < 5; i++) {
			if(arr[m2] < arr[i] && i != m1) m2 = i;
		}
		for(int i = 0; i < 5; i++) {
			if(arr[m3] < arr[i] && i != m1 && i != m2) m3 = i;
		}
		return (double)arr[m3];
	}
	
	public static double stddev(double[] arr) {
		double avg = average(arr);
		double[] arr2 = new double[5];
		for(int i = 0; i < 5; i++) {
			arr2[i] = Math.pow((arr[i] - avg), 2);
		}
		double count = 0;
		for(double d : arr2) {
			count += d;
		}
		return Math.sqrt(count/4);
	}
	
	public static int mode(double[] arr) {
		int count = 0, max = 0, mode = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 5; j++) {
				if(arr[i] == arr[j]) count++;
			}
			if(count > max) {
				max = count;
				mode = (int)arr[i];
			}
			count = 0;
		}
		return mode;
	}
}
