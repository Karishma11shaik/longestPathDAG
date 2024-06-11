# longestPathDAG
# Longest Path in a DAG

This solution calculates the longest directed path from a given vertex in a Directed Acyclic Graph (DAG). The solution uses topological sorting and dynamic programming to efficiently find the longest path(s) and their length.

## Classes

- Vertex: Represents a vertex in the graph.
- Edge: Represents a directed edge in the graph, from one vertex to another.

## Functions

- LongestPathDAG(List<Vertex> vertices, List<Edge> edges): Constructor to initialize the adjacency list, paths, and dynamic programming structures.
- List<List<Long>> longestPathDAG(long startVertexId): Given the starting vertex ID, this function returns a list of lists representing all the longest paths starting from the given vertex.
- int getLongestPathLength(long startVertexId): Given the starting vertex ID, this function returns the length of the longest path starting from the given vertex.

## Example Usage

The script includes several example scenarios to demonstrate the functionality of the LongestPathDAG class. You can run the script to see the output for each example.

### Example 1
*Vertices*: 1, 2, 3, 4, 5  
*Edges*: (1 → 2), (1 → 3), (3 → 4), (2 → 4), (4 → 5)  
*Start Vertex*: 1

Expected Output:
- Longest Paths: [[1, 2, 4, 5], [1, 3, 4, 5]]
- Length: 3

### Example 2
*Vertices*: 1, 2, 3, 4  
*Edges*: (1 → 2), (1 → 3), (3 → 4)  
*Start Vertex*: 1

Expected Output:
- Longest Paths: [[1, 3, 4]]
- Length: 2

### Example 3
*Vertices*: 1, 2, 3  
*Edges*: (1 → 2), (2 → 3)  
*Start Vertex*: 1

Expected Output:
- Longest Paths: [[1, 2, 3]]
- Length: 2

### Example 4
*Vertices*: 1, 2, 3  
*Edges*: (2 → 3)  
*Start Vertex*: 1

Expected Output:
- Longest Paths: [[1]]
- Length: 0

### Example 5
*Vertices*: (none)  
*Edges*: (none)  
*Start Vertex*: 1

Expected Output:
- Longest Paths: []
- Length: 0

## How to Run

1. Ensure Java is installed on your machine. You can check this by running java -version in your terminal or command prompt.
2. Save the script LongestPathDAG.java to your local machine.
3. Open a terminal or command prompt and navigate to the directory where the script is saved.
4. Compile the program using the command javac LongestPathDAG.java.
5. Run the compiled program using the command java LongestPathDAG.
6. The script will print the longest paths and their length for each example.

## Questions Answered

1. *Does the solution work for larger graphs?*
   Yes, the solution is efficient and works for larger graphs due to its O(V + E) complexity.

2. *Can you think of any optimizations?*
   The solution is optimized for the given problem. Memoization could be added if the graph is to be traversed multiple times.

3. *What’s the computational complexity of your solution?*
   The computational complexity is O(V + E), where V is the number of vertices and E is the number of edges.

4. *Are there any unusual cases that aren't handled?*
    - Empty graphs are handled by returning an empty path and a length of 0.
    - Graphs with no outgoing edges from the starting vertex return a path containing just the start vertex and a length of 0.
    - Cyclic graphs are not handled as the problem guarantees a DAG. If cycles were present, the solution would need to be extended to detect and handle them.
