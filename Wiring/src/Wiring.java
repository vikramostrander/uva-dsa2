import java.util.*;

class Edge {
	public int weight;
	public String node;
	
	public Edge(String node, int weight) {
		this.node = node;
		this.weight = weight;
	}
}

class Node implements Comparable<Node> {
	public String type;
	public String node;
	public ArrayList<Edge> adj;
	public String color;
	public int weight;
	public String parentSwitch;
	
	public Node(String node, String type) {
		this.node = node;
		this.type = type;
		adj = new ArrayList<Edge>();
		weight = Integer.MAX_VALUE;
		color = "WHITE";
		parentSwitch = "n/a";
	}
	
	@Override
	public int compareTo(Node node) {
		return this.weight - node.weight;
	}
}

public class Wiring {
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String line = s.nextLine();
		String[] arr = line.split(" ");
		
		int numNodes = Integer.parseInt(arr[0]);
		int numEdges = Integer.parseInt(arr[1]);
		
		HashMap<String, Node> graph = new HashMap<String, Node>();
		String curSwitch = "n/a";
		String start = "";
		
		for(int i = 0; i < numNodes; i++) {
			line = s.nextLine();
			arr = line.split(" ");
			if(arr[1].equals("breaker")) start = arr[0];
			if(arr[1].equals("switch")) curSwitch = arr[0];
			graph.put(arr[0], new Node(arr[0], arr[1]));
			if(arr[1].equals("light")) graph.get(arr[0]).parentSwitch = curSwitch;
		}
		
		for(int i = 0; i < numEdges; i++) {
			line = s.nextLine();
			arr = line.split(" ");
			graph.get(arr[0]).adj.add(new Edge(arr[1], Integer.parseInt(arr[2])));
			graph.get(arr[1]).adj.add(new Edge(arr[0], Integer.parseInt(arr[2])));
		}
		
		s.close();
		System.out.println(prim(graph, start));
	}
	
	public static int prim(HashMap<String, Node> graph, String start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		addNode(pq, graph, start, 0);
		int total = 0;
		Node n;
		while(pq.size() > 0) {
			//printPQ(pq);
			n = pq.poll();
			n.color = "BLACK";
			total += n.weight;
			//System.out.println("popped: " + n.node + ", " + n.weight);
			for(Edge e : graph.get(n.node).adj) {
				if(!isAllowed(graph, n.node, e.node)) { /*ignore edge*/ }
				else if(graph.get(e.node).color.equals("WHITE")) {
					addNode(pq, graph, e.node, e.weight);
					//System.out.println("\t" + e.node + " " + e.weight);
				}
				else if(graph.get(e.node).color.equals("GRAY") && e.weight < graph.get(e.node).weight) {
					pq.remove(graph.get(e.node));
					addNode(pq, graph, e.node, e.weight);
				}
			}
		}
		return total;
	}
	
	public static void addNode(PriorityQueue<Node> pq, HashMap<String, Node> graph, String node, int weight) {
		graph.get(node).color = "GRAY";
		graph.get(node).weight = weight;
		pq.add(graph.get(node));
	}
	
	public static boolean isAllowed(HashMap<String, Node> graph, String n1, String n2) {
		if(graph.get(n1).type.equals("breaker")) {
			if(graph.get(n2).type.equals("light")) return false;
			else return true;
		}
		else if(graph.get(n1).type.equals("switch")) {
			if(graph.get(n2).type.equals("light") && graph.get(n2).parentSwitch.equals(n1)) {
				//System.out.println("allowed: " + n1 + " " + n2);
				return true;
			}
			else return false;
		}
		else if(graph.get(n1).type.equals("light")) {
			if(graph.get(n2).type.equals("light") && graph.get(n2).parentSwitch.equals(graph.get(n1).parentSwitch)) return true;
			else return false;
		}
		else if(graph.get(n1).type.equals("outlet")) {
			if(graph.get(n2).type.equals("light")) return false;
			else return true;
		}
		else if(graph.get(n1).type.equals("box")) {
			if(graph.get(n2).type.equals("light")) return false;
			else return true;
		}
		return false;
	}
	
	public static void printPQ(PriorityQueue<Node> pq) {
		System.out.print("pq:\t");
		for(Node n : pq) {
			System.out.print("(" + n.node + "," + n.weight + ") ");
		}
		if(pq.size() > 1) {
			Node n = pq.poll();
			System.out.print("Compare top with next: " + n.compareTo(pq.peek()));
			pq.add(n);
		}
		System.out.println();
	}
}