package ch22;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class GraphWithArray {

	int[] v; // vertices (could be generified later)
	List<Integer>[] adj; // adjacency list
	int[] c; // color
	int[] d; // distance
	int[] p; // parent (could be generified later)
	boolean directed; // true if directed, false is undirected
	Queue<Integer> q; // Queue
	int time; // time
	int[] discovery; // discovery time
	int[] finishing; // finishing time

	public GraphWithArray() {
		this(true); // default to directed graph
	}

	public GraphWithArray(boolean directed) {
		this.directed = directed; // is graph directed?
	}

	public void createVertices(int... vertices) {
		v = new int[vertices.length];
		for (int i = 0; i < vertices.length; i++) {
			v[i] = vertices[i];
		}
		init();
	}

	public GraphWithArray edge(int from, int to) {
		int fi = index(from); // from index
		int ti = index(to); // to index
		if (!adj[fi].contains(ti))
			adj[fi].add(ti);
		if (!directed && !adj[ti].contains(fi))
			adj[ti].add(fi);
		return this;
	}

	private int index(int key) {
		for (int i = 0; i < v.length; i++) {
			if (v[i] == key)
				return i;
		}
		throw new RuntimeException("Vertex not found: " + key);
	}

	private void init() {
		c = new int[v.length];
		d = new int[v.length];
		p = new int[v.length];
		adj = new List[v.length];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		discovery = new int[v.length];
		finishing = new int[v.length];
	}

	public void bfs(int s) {
		for (int i = 0; i < v.length; i++) {
			c[i] = WHITE;
			d[i] = Integer.MAX_VALUE;
			p[i] = -1;
		}
		int si = index(s);
		c[si] = GRAY;
		d[si] = 0;
		p[si] = -1;
		q = new ArrayDeque<Integer>();
		q.add(si);
		while (!q.isEmpty()) {
			int ui = q.remove();
			for (Integer i : adj[ui]) {
				if (c[i] == WHITE) {
					c[i] = GRAY;
					d[i] = d[ui] + 1;
					p[i] = ui;
					q.add(i);
				}
			}
			c[ui] = BLACK;
			System.out.print(v[ui] + ", ");
		}
		System.out.println();
	}
	
	// assumed that bfs() is already called
	public void printPath(int s, int v) {
		int si = index(s);
		int vi = index(v);
		if (vi == si)
			System.out.print(this.v[si] + " -- > ");
		else if (p[vi] == -1)
			System.out.println("No path: " + this.v[si] + " -- > " + this.v[vi]);
		else {
			printPath(s, this.v[p[vi]]);
			System.out.print(this.v[vi] + " -- > ");
		}
	}
	
	public void dfs() {
		for (int i = 0; i < v.length; i++) {
			c[i] = WHITE;
			p[i] = -1;
		}
		time = 0;
		for (int i = 0; i < v.length; i++) {
			if (c[i] == WHITE)
				dfsVisit(i);
		}
	}

	/**
	 * DFS visit from index u 
	 * @param u
	 */
	private void dfsVisit(int u) {
		c[u] = GRAY;
		time++;
		discovery[u] = time;
		for (Integer v : adj[u]) {
			if (c[v] == WHITE) {
				p[v] = u;
				dfsVisit(v);
			}
		}
		c[u] = BLACK;
		time++;
		finishing[u] = time;
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < v.length; i++) {
			result += v[i] + " --> " + adj[i] + "\n";
		}
		return result;
	}

	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;

	public static void main(String[] args) {
		GraphWithArray g = new GraphWithArray();
		g.createVertices(1, 2, 3, 4, 5, 6);
		g.edge(1, 2).edge(1, 4).edge(2, 5).edge(3, 5).edge(3, 6).edge(4, 2)
				.edge(5, 4);
		g.bfs(1);
		System.out.println("c: " + Arrays.toString(g.c));
		System.out.println("d: " + Arrays.toString(g.d));
		System.out.println("p: " + Arrays.toString(g.p));
		g.printPath(1, 6);
	}

}
