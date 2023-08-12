public class FizzBuzz {
	
	public static void main(String[] args) {
		String print = "";
		for(int i = 1; i <= 1000; i++) {
			if(i % 3 == 0) print += "Fizz";
			if(i % 5 == 0) {
				if(print.equals("Fizz")) print += " ";
				print += "Buzz";
			}
			if(print == "") print += i;
			System.out.println(print);
			print = "";
		}
		
	}

}