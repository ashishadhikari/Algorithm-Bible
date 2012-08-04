package ch22;

public class Main {

	public static void main(String[] args) {
		// GGraph<Integer> g = new GGraph<Integer>();
		// g.createVertices(1, 2, 3, 4, 5, 6);
		// g.edge(1, 2).edge(1, 4).edge(2, 5).edge(3, 5).edge(3, 6).edge(4, 2)
		// .edge(5, 4);
		// g.bfs(1);
		// System.out.println("c: " + Arrays.toString(g.c));
		// System.out.println("d: " + Arrays.toString(g.d));
		// System.out.println("p: " + Arrays.toString(g.p));
		// g.printPath(1, 6);
		Graph<String> g = new Graph<String>();
		g.createVertices("undershorts", "pants", "belt", "shirt", "tie",
				"jacket", "shoes", "socks", "watch");
		g.edge("undershorts", "pants").edge("pants", "belt")
				.edge("pants", "shoes").edge("belt", "jacket")
				.edge("socks", "shoes").edge("shirt", "belt")
				.edge("shirt", "tie").edge("tie", "jacket")
				.edge("undershorts", "shoes");
		// g.bfs("shirt");
		// g.printPath("shirt", "jacket");
		// System.out.println();
		// System.out.println(Arrays.toString(g.v));
		// System.out.println(Arrays.toString(g.finishing));
		System.out.println("topo: " + g.topologicalSort());
		// System.out.println("Adjacency List");
		// for (int i = 0; i < 9; i++) {
		// System.out.println(g.v[i].toString() + " --> "
		// + g.adj[i].toString());
		// }
		// System.out.println(g);


	}
	
}
