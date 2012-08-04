package ch22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import java.util.ArrayDeque;

public class Graph<E> {

	/**
	 * List of vertices.
	 */
	private List<E> vertices;

	/**
	 * Map keyed from Vertex to Adjacency List.
	 */
	private Map<E, List<E>> adjMap;

	/**
	 * Map keyed from Vertex to Color.
	 */
	private Map<E, Integer> colorMap;

	/**
	 * Map keyed from Vertex to distance.
	 */
	private Map<E, Double> distanceMap;

	/**
	 * Map keyed from Vertex to its parent.
	 */
	private Map<E, E> parentMap;

	/**
	 * Flag to indicate whether this graph is directed or not.
	 */
	private boolean directed;

	/**
	 * Queue used for BFS traversal.
	 */
	private Queue<E> queue;

	/**
	 * Counter for time, used for DFS traversal.
	 */
	private int time;

	/**
	 * Map keyed from Vertex to discovery time.
	 */
	private Map<E, Integer> discoveryMap;

	/**
	 * May keyed from Vertex to finishing time.
	 */
	private Map<E, Integer> finishingMap;

	/* Visitors */

	/**
	 * List of DFS Finishing Visitors.
	 */
	private List<Visitor<E>> dfsFinishVisitors;

	public Graph() {
		this(true); // default to directed graph
	}

	public Graph(boolean directed) {
		this.directed = directed; // is graph directed?
	}

	public void createVertices(E... vertices) {
		this.vertices = Arrays.asList(vertices);
		adjMap = new HashMap<E, List<E>>();
		for (E e : vertices) {
			adjMap.put(e, new ArrayList<E>());
		}
		dfsFinishVisitors = new ArrayList<Visitor<E>>();
	}

	public Graph<E> edge(E from, E to) {
		if (!vertices.contains(from))
			throw new RuntimeException("No such vertex to add edge: " + from);
		if (!vertices.contains(to))
			throw new RuntimeException("No such vertex to add edge: " + to);
		if (!adjMap.get(from).contains(to))
			adjMap.get(from).add(to);
		if (!directed && adjMap.get(to).contains(from))
			adjMap.get(to).add(from);
		return this;
	}

	private void init() {
		colorMap = new HashMap<E, Integer>();
		distanceMap = new HashMap<E, Double>();
		parentMap = new HashMap<E, E>();
		discoveryMap = new HashMap<E, Integer>();
		finishingMap = new HashMap<E, Integer>();
		for (E e : vertices) {
			colorMap.put(e, WHITE);
			distanceMap.put(e, Double.POSITIVE_INFINITY);
		}
	}

	public void bfs(E s) {
		init();
		colorMap.put(s, GRAY);
		distanceMap.put(s, 0.0);
		queue = new ArrayDeque<E>();
		queue.add(s);
		while (!queue.isEmpty()) {
			E u = queue.remove();
			for (E i : adjMap.get(u)) {
				if (colorMap.get(i).equals(WHITE)) {
					colorMap.put(i, GRAY);
					distanceMap.put(i, distanceMap.get(u) + 1);
					parentMap.put(i, u);
					queue.add(i);
				}
			}
			colorMap.put(u, BLACK);
		}
	}

	/**
	 * assumed that bfs() is already called
	 * 
	 * @param s
	 *            source
	 * @param vertices
	 *            destination
	 */
	public void printPath(E src, E dest) {
		if (src.equals(dest))
			System.out.print(src);
		else if (parentMap.get(dest) == null)
			System.out.println("No path from " + src + " to " + dest);
		else {
			printPath(src, parentMap.get(dest));
			System.out.print(" -- > " + dest);
		}
	}

	public void dfs() {
		init();
		time = 0;
		for (E e : vertices) {
			if (colorMap.get(e).equals(WHITE))
				dfsVisit(e);
		}
	}

	/**
	 * DFS visit from index u
	 * 
	 * @param u
	 */
	private void dfsVisit(E u) {
		colorMap.put(u, GRAY);
		time++;
		discoveryMap.put(u, time);
		for (E v : adjMap.get(u)) {
			if (colorMap.get(v).equals(WHITE)) {
				parentMap.put(v, u);
				dfsVisit(v);
			}
		}
		colorMap.put(u, BLACK);
		time++;
		finishingMap.put(u, time);
		for (Visitor<E> v : dfsFinishVisitors) {
			v.visit(u);
		}
	}

	/**
	 * Adds Visitor to DFS Finishing stage.
	 * 
	 * @param v
	 */
	public void dfsFinishVisitor(Visitor<E> v) {
		dfsFinishVisitors.add(v);
	}

	/**
	 * Returns list of Vertices Topologically Sorted.
	 * 
	 * @return
	 */
	public List<E> topologicalSort() {
		class TopologicalSortVisitor<E> implements Visitor<E> {

			private LinkedList<E> list = new LinkedList<E>();

			@Override
			public void visit(E vertex) {
				list.addFirst(vertex);
			}

			public LinkedList<E> getList() {
				return list;
			}
		}

		TopologicalSortVisitor<E> v = new TopologicalSortVisitor<E>();
		dfsFinishVisitor(v);
		dfs();
		return v.getList();
	}

	@Override
	public String toString() {
		String result = "";
		for (E e : vertices) {
			result += e + " --> " + adjMap.get(e) + "\n";
		}
		return result;
	}

	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;

	public static interface Visitor<T> {
		void visit(T vertex);
	}

}
