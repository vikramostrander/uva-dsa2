import java.util.*;

class Point implements Comparable<Point> {
	double x, y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double dist(Point p) {
		return Math.sqrt(Math.pow((this.x - p.x), 2) + Math.pow((this.y - p.y), 2));
	}
	
	@Override
	public int compareTo(Point p) {
		return (int)this.x - (int)p.x;
	}
	
	public void swap() {
		double temp = x;
		x = y;
		y = temp;
	}
}

public class trading {
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		String str;
		str = s.nextLine();
		int n = Integer.parseInt(str);
		
		while(n != 0) {
			ArrayList<Point> points = new ArrayList<Point>();
			ArrayList<Point> swapped = new ArrayList<Point>();
			
			String[] arr;
			for(int i = 0; i < n; i++) {
				str = s.nextLine();
				arr = str.split(" ");
				points.add(new Point(Double.parseDouble(arr[0]), Double.parseDouble(arr[1])));
				swapped.add(new Point(Double.parseDouble(arr[1]), Double.parseDouble(arr[0])));
			}
			Collections.sort(points);
			Collections.sort(swapped);
			for(Point p : swapped) p.swap();
						
			double dist = divide(points, 0, n - 1, swapped);
			if(dist >= 10000) System.out.println("infinity");
			else System.out.println(String.format("%.4f", dist));
			
			str = s.nextLine();
			n = Integer.parseInt(str);
		}
	}
	
	public static double divide(ArrayList<Point> points, int start, int end, ArrayList<Point> swapped) {
		if(end - start > 1) {
			int mid = (start + end) / 2;
			double left = divide(points, start, mid, swapped);
			double right = divide(points, mid + 1, end, swapped);
			double min = Math.min(left, right);
			return combine(points, start, end, mid, min, swapped);
		}
		if(end - start == 0) {
			return Double.MAX_VALUE;
		}
		return points.get(start).dist(points.get(end));
	}
	
	public static double combine(ArrayList<Point> points, int i, int j, int mid, double delta, ArrayList<Point> swapped) {		
		double deltaL = points.get(mid).x - delta;
		double deltaR = points.get(mid).x + delta;
				
		ArrayList<Point> strip = new ArrayList<Point>();
		for(Point p : swapped) {
			if(p.x >= deltaL && p.x <= deltaR) {
				strip.add(p);
			}
		}
		
		double temp;
		for(int a = 0; a < strip.size(); a++) {
			for(int b = a + 1; b <= a + 7; b++) {
				if(b == strip.size()) break;
				temp = strip.get(a).dist(strip.get(b));
				if(temp < delta) {
					delta = temp;
				}
			}
		}
		
		return delta;
	}
	
	public static void printArr(ArrayList<Point> arr) {
		for(Point p : arr) {
			System.out.printf("(%.2f, %.2f)", p.x, p.y);
		}
		System.out.println();
	}
	
	public static void printArr2(Point[] arr) {
		System.out.print("\t");
		for(Point p : arr) {
			System.out.printf("[%.2f, %.2f]", p.x, p.y);
		}
		System.out.println();
	}
}
