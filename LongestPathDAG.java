import java.util.*;

public class LongestPathDAG {
    private Map<Long, List<Long>> adjList;
    private Map<Long, List<List<Long>>> paths;
    private Map<Long, Integer> dp;

    public LongestPathDAG(List<Vertex> vertices, List<Edge> edges) {
        adjList = new HashMap<>();
        paths = new HashMap<>();
        dp = new HashMap<>();

        for (Vertex vertex : vertices) {
            adjList.put(vertex.id, new ArrayList<>());
            dp.put(vertex.id, Integer.MIN_VALUE);
            paths.put(vertex.id, new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjList.get(edge.from.id).add(edge.to.id);
        }
    }

    private void topologicalSort(long v, Set<Long> visited, Stack<Long> stack) {
        visited.add(v);
        for (long neighbor : adjList.get(v)) {
            if (!visited.contains(neighbor)) {
                topologicalSort(neighbor, visited, stack);
            }
        }
        stack.push(v);
    }

    public List<List<Long>> longestPathDAG(long startVertexId) {
        if (!adjList.containsKey(startVertexId)) {
            return Collections.emptyList();
        }

        Set<Long> visited = new HashSet<>();
        Stack<Long> stack = new Stack<>();

        for (long vertexId : adjList.keySet()) {
            if (!visited.contains(vertexId)) {
                topologicalSort(vertexId, visited, stack);
            }
        }

        dp.put(startVertexId, 0);
        paths.get(startVertexId).add(Arrays.asList(startVertexId));

        while (!stack.isEmpty()) {
            long u = stack.pop();
            if (dp.get(u) != Integer.MIN_VALUE) {
                for (long v : adjList.get(u)) {
                    if (dp.get(v) < dp.get(u) + 1) {
                        dp.put(v, dp.get(u) + 1);
                        paths.put(v, new ArrayList<>());
                        for (List<Long> path : paths.get(u)) {
                            List<Long> newPath = new ArrayList<>(path);
                            newPath.add(v);
                            paths.get(v).add(newPath);
                        }
                    } else if (dp.get(v) == dp.get(u) + 1) {
                        for (List<Long> path : paths.get(u)) {
                            List<Long> newPath = new ArrayList<>(path);
                            newPath.add(v);
                            paths.get(v).add(newPath);
                        }
                    }
                }
            }
        }

        int maxLength = Collections.max(dp.values());
        if (maxLength == Integer.MIN_VALUE) {
            return Collections.emptyList();
        }

        List<List<Long>> maxPaths = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : dp.entrySet()) {
            if (entry.getValue() == maxLength) {
                maxPaths.addAll(paths.get(entry.getKey()));
            }
        }

        return maxPaths;
    }

    public int getLongestPathLength(long startVertexId) {
        if (!adjList.containsKey(startVertexId) || adjList.get(startVertexId).isEmpty()) {
            return 0;
        }

        List<List<Long>> longestPaths = longestPathDAG(startVertexId);
        if (longestPaths.isEmpty()) {
            return 0;
        }

        return longestPaths.get(0).size() - 1;
    }

    public static void main(String[] args) {
        // Example 1
        List<Vertex> vertices1 = Arrays.asList(new Vertex(1), new Vertex(2), new Vertex(3), new Vertex(4), new Vertex(5));
        List<Edge> edges1 = Arrays.asList(
                new Edge(new Vertex(1), new Vertex(2)),
                new Edge(new Vertex(1), new Vertex(3)),
                new Edge(new Vertex(3), new Vertex(4)),
                new Edge(new Vertex(2), new Vertex(4)),
                new Edge(new Vertex(4), new Vertex(5))
        );
        long startVertexId1 = 1;
        LongestPathDAG dag1 = new LongestPathDAG(vertices1, edges1);
        List<List<Long>> paths1 = dag1.longestPathDAG(startVertexId1);
        System.out.println("Example 1 - Longest paths: " + paths1 + " with length: " + dag1.getLongestPathLength(startVertexId1));

        // Example 2
        List<Vertex> vertices2 = Arrays.asList(new Vertex(1), new Vertex(2), new Vertex(3), new Vertex(4));
        List<Edge> edges2 = Arrays.asList(
                new Edge(new Vertex(1), new Vertex(2)),
                new Edge(new Vertex(1), new Vertex(3)),
                new Edge(new Vertex(3), new Vertex(4))
        );
        long startVertexId2 = 1;
        LongestPathDAG dag2 = new LongestPathDAG(vertices2, edges2);
        List<List<Long>> paths2 = dag2.longestPathDAG(startVertexId2);
        System.out.println("Example 2 - Longest paths: " + paths2 + " with length: " + dag2.getLongestPathLength(startVertexId2));

        // Example 3
        List<Vertex> vertices3 = Arrays.asList(new Vertex(1), new Vertex(2), new Vertex(3));
        List<Edge> edges3 = Arrays.asList(
                new Edge(new Vertex(1), new Vertex(2)),
                new Edge(new Vertex(2), new Vertex(3))
        );
        long startVertexId3 = 1;
        LongestPathDAG dag3 = new LongestPathDAG(vertices3, edges3);
        List<List<Long>> paths3 = dag3.longestPathDAG(startVertexId3);
        System.out.println("Example 3 - Longest paths: " + paths3 + " with length: " + dag3.getLongestPathLength(startVertexId3));

        // Example 4 - Graph with no outgoing edges from start vertex
        List<Vertex> vertices4 = Arrays.asList(new Vertex(1), new Vertex(2), new Vertex(3));
        List<Edge> edges4 = Arrays.asList(
                new Edge(new Vertex(2), new Vertex(3))
        );
        long startVertexId4 = 1;
        LongestPathDAG dag4 = new LongestPathDAG(vertices4, edges4);
        List<List<Long>> paths4 = dag4.longestPathDAG(startVertexId4);
        System.out.println("Example 4 - Longest paths: " + paths4 + " with length: " + dag4.getLongestPathLength(startVertexId4));

        // Example 5 - Empty graph
        List<Vertex> vertices5 = new ArrayList<>();
        List<Edge> edges5 = new ArrayList<>();
        long startVertexId5 = 1;
        LongestPathDAG dag5 = new LongestPathDAG(vertices5, edges5);
        List<List<Long>> paths5 = dag5.longestPathDAG(startVertexId5);
        System.out.println("Example 5 - Longest paths: " + paths5 + " with length: " + dag5.getLongestPathLength(startVertexId5));
    }
}
