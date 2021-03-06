package mst;

import java.util.Arrays;

import basic.Point;
import disjointset.DisjointSet;
import disjointset.Node;

public class Kruskall {

	public static Node[] convertToNodes(Point[] points) {
		int n = points.length;
		Node[] nodes = new Node[n];
		for(int i=0;i<n;i++) {
			nodes[i] = new Node(points[i]);
		}
		return nodes;
	}

	
	public static MinimalSpanningTree constructMinimalSpanningTree(Node[] nodes) {
		Edge[] edges = getAllEdgesBeta(nodes);
		Edge[] treeEdges = new Edge[nodes.length - 1];
		int nbOfTreeEdges = 0;
		
		for(Edge edge : edges) {
			if(noloops(edge)) {
				union(edge);
				treeEdges[nbOfTreeEdges++] = edge;
				if(nbOfTreeEdges >= nodes.length - 1) {
					break;
				}
			}
		}
		DisjointSet.reset(nodes);
		return new MinimalSpanningTree(nodes,treeEdges);
	}
	
	
	public static MinimalSpanningTree extendMinimalSpanningTree(
			MinimalSpanningTree mst, Node node) {
		return extendMinimalSpanningTree(mst, new Node[] {node});
	}
	
	
	public static MinimalSpanningTree extendMinimalSpanningTree(
			MinimalSpanningTree mst, Node[] nodes) {
		Edge[] extra = getExtraMSTEdges(mst,nodes);
		Edge[] edges =  merge(extra,mst.getEdges());
		Arrays.sort(edges);
		
		int n = mst.getNodes().length + nodes.length; // nb of nodes
		Edge[] treeEdges = new Edge[n-1];
		int i = 0;
		for(Edge edge : edges) {
			if(noloops(edge)) {
				union(edge);
				treeEdges[i++] = edge;
				if(i == n-1) {
					break;
				}
			}
		}
		Node[] newNodes = merge(mst.getNodes(),nodes);
		DisjointSet.reset(newNodes);
		return new MinimalSpanningTree(newNodes,treeEdges);
	}
	
	private static Edge[] getExtraMSTEdges(MinimalSpanningTree mst, Node[] nodes) {
		Edge[] edges = new Edge[nodes.length*UsableEdges.MAX];
		int cnt = 0;
		for(Node node : nodes) {
			UsableEdges lst = new UsableEdges(node);
			lst.add(nodes);
			lst.add(mst.getNodes());
			for(Edge edge : lst.getEdges()) edges[cnt++] = edge;
		}
		return edges;
	}

	public static <T> T[] merge(T[] a, T[] b) {
		T[] c = Arrays.copyOf(a, a.length + b.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

	private static void union(Edge edge) {
		DisjointSet.union(edge.getA(), edge.getB());
		
	}

	private static boolean noloops(Edge edge) {
		return !DisjointSet.sameSet(edge.getA(), edge.getB());
	}

	
	private static Edge[] getAllEdgesBeta(Node[] nodes) {
		Edge[] edges = new Edge[ nodes.length * UsableEdges.MAX];
		int cnt = 0;
		for(Node node : nodes) {
			UsableEdges lst = new UsableEdges(node);
			lst.add(nodes);
			for(Edge edge : lst.getEdges()) edges[cnt++] = edge;
		}
		Arrays.sort(edges);
		return edges;
	}
	
	@SuppressWarnings("unused")
	private static Edge[] getAllEdges(Node[] nodes) {
		int n = nodes.length;
		// a complete graph has n nodes and n*(n-1)/2 edges
		int nbOfAllEdges = n * (n-1) / 2;
		Edge[] allEdges = new Edge[nbOfAllEdges];
		
		int nbOfEdges = 0;
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				allEdges[nbOfEdges++] = new Edge(nodes[i],nodes[j]);
			}
		}
		Arrays.sort(allEdges);
		return allEdges;
	}
	
	
}
