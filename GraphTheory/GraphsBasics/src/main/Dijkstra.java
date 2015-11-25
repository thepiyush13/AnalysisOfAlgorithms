package main;

import java.util.*;

;

public class Dijkstra {
	public Dijkstra(Graph G, Vertex source, Vertex destination) {
		int s = source.value;
		int t = destination.value;
		int count = 0;
		int V = G.getV();
		if (!isValid(s, t, V)) {
			return;
		}
		Map<Integer, String> vertexStatus = new HashMap<Integer, String>(V);
		Map<Integer, Integer> distance = new HashMap<Integer, Integer>();
		Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
		count = 0;
		while (count < V) {
			// current status is unmarked
			vertexStatus.put(count, "Unmarked");
			// add negative infinity
			distance.put(count, Integer.MIN_VALUE);
			count++;
		}

		vertexStatus.put(s, "Marked");
		SortedSet<Vertex> externalVertex = new TreeSet<Vertex>();
		parent.put(s, -1);
		distance.put(s, 0);
		count = 0;
		while (count < V) {
			if (G.getEdgeWeight(s, count) > 0) {
				parent.put(count, s);
				vertexStatus.put(count, "Out");
				distance.put(count, G.getEdgeWeight(s, count));
				externalVertex.add(new Vertex(count, count));
			}
			count++;
		}

		while (externalVertex.size() > 0) {

			Vertex v = externalVertex.first();
			externalVertex.remove(v);
			int vNumber = v.name;
			vertexStatus.put(vNumber, "Marked");
			count = 0;
			while (count < V) {
				if (G.getEdgeWeight(vNumber, count) > 0) {
					int length = Math.min(distance.get(vNumber), G.getEdgeWeight(vNumber, count));
					if (vertexStatus.get(count).equals("Unmarked")) {
						vertexStatus.put(count, "Out");
						distance.put(count, length);
						parent.put(count, vNumber);
						externalVertex.add(new Vertex(count, distance.get(count)));
					} else if (distance.get(count) < length) {
						if (vertexStatus.get(count).equals("Out")) {
							Vertex tempVertex = null;
							Iterator<Vertex> it = externalVertex.iterator();
							while (it.hasNext()) {
								tempVertex = it.next();
								if (tempVertex.value == count) {
									externalVertex.remove(tempVertex);
									break;
								}
							}
							distance.put(count, length);
							parent.put(count, vNumber);
							externalVertex.add(new Vertex(count, distance.get(count)));
						}
						

					}

				}
				count++;
			}

		}
		count = 0;
		return;

	}

	private boolean isValid(int s, int t, int V) {
		boolean valid = true;
		if (s > V - 1) {
			valid = false;
		} else if (t > V - 1) {
			valid = false;
		}

		return valid;
	}

}
