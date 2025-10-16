fun main() {
    // Graph represented as adjacency list
    val graph = mapOf(
        "A" to listOf("B", "C"),
        "B" to listOf("A", "D"),
        "C" to listOf("A", "E"),
        "D" to listOf("B"),
        "E" to listOf("C")
    )

    val startNode = "A"

    println("BFS starting from $startNode:")
    bfs(graph, startNode)
}

// 🚧 Your turn: implement this function
fun bfs(graph: Map<String, List<String>>, start: String) {
    if (start !in graph) return
    val queue = ArrayDeque<String>()
    val visitedNodes = mutableSetOf<String>()
    queue.add(start)
    visitedNodes.add(start)
    while (queue.isNotEmpty()){
        val node = queue.removeFirst()
        print(node)
        for(neighbour in graph[node]!!){
            if(!visitedNodes.contains(neighbour)) {
                visitedNodes.add(neighbour)
                queue.addLast(neighbour)
            }
        }
    }
}
