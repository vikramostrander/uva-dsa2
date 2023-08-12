import java.util.*;

class Company implements Comparable<Company> {
	String name;
	int cost1;
	int cost2;
	int totalCost;
	
	public Company(String name, int cost1, int cost2) {
		this.name = name;
		this.cost1 = cost1;
		this.cost2 = cost2;
	}
	
	public int compareTo(Company c) {
		if(this.totalCost != c.totalCost) return this.totalCost - c.totalCost;
		return this.name.compareTo(c.name);
	}
}

public class MovingBoxes {
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String line = s.nextLine();
		int cases = Integer.parseInt(line);
		String[] arr;
		Company[] companies;
		int b, m, c;
		ArrayList<Company> sorted;
		
		for(int i = 1; i <= cases; i++) {
			line = s.nextLine();
			arr = line.split(" ");
			b = Integer.parseInt(arr[0]);
			m = Integer.parseInt(arr[1]);
			c = Integer.parseInt(arr[2]);
			
			companies = new Company[c];
			for(int j = 0; j < c; j++) {
				line = s.nextLine();
				arr = line.split(" ");
				companies[j] = new Company(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
			}
			
			sorted = move(b, m, companies);
			Collections.sort(sorted);
			
			System.out.println("Case " + i);
			for(Company x : sorted) {
				System.out.println(x.name + " " + x.totalCost);
			}
		}
		
		s.close();
	}
	
	public static ArrayList<Company> move(int boxes, int mine, Company[] companies) {
		ArrayList<Company> sorted = new ArrayList<Company>();
		int half, remaining, cost;
		for(Company c : companies) {
			remaining = boxes;
			cost = 0;
			//System.out.println(c.name + ":");
			while(remaining > 0) {
				half = (int)Math.ceil(remaining/2.0);
				if(half <= mine) {
					if(remaining == mine) {
						break;
					}
					cost += c.cost1;
					remaining --;
				}
				else {
					if(c.cost2 <= c.cost1*half) {
						cost += c.cost2;
						remaining -= half;
					}
					else {
						cost += c.cost1;
						remaining--;
					}
				}
				//System.out.println("\t" + cost + " -> " + remaining);
			}
			//System.out.println("tc = " + cost);
			c.totalCost = cost;
			sorted.add(c);
		}
		return sorted;
	}
}
