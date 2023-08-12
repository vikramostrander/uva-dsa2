import java.util.Scanner;

public class MinMax {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int count = s.nextInt();
		int[] numArr = new int[count];
		for(int i = 0; i < count; i++) {
			numArr[i] = s.nextInt();
		}
		System.out.println(secondMax(numArr));
		System.out.println(secondMin(numArr));
	}

	public static int secondMax(int[] numbers) {
		int max = 0, next = 0;
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] > numbers[max]) max = i;
			if(numbers[i] < numbers[next]) next = i;
		}
		for(int i = 0; i < numbers.length; i++) {
			if(i == max) {}
			else if(numbers[i] > numbers[next]) next = i;
		}
		return numbers[next];
	}
	
	public static int secondMin(int[] numbers) {
		int min = 0, next = 0;
		for(int i = 0; i < numbers.length; i++) {
			if(numbers[i] < numbers[min]) min = i;
			if(numbers[i] > numbers[next]) next = i;
		}
		for(int i = 0; i < numbers.length; i++) {
			if(i == min) {}
			else if(numbers[i] < numbers[next]) next = i;
		}
		return numbers[next];
	}
}
