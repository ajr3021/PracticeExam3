package graphs;

import java.util.*;


public class TopologicalOrdering<E> {
    Set<Vertex<E>> visited = new HashSet<>();
    AdjacencyGraph<E> graph;

    public TopologicalOrdering(AdjacencyGraph<E> graph) {
        this.graph = graph;
    }

    public List<E> topologicalOrdering(){
        for(Vertex<E> vertex : graph.getVertices().values()){
            if(!visited.contains(vertex)){
                graph.visitDFS(vertex, visited);
            }
        }

        List<E> list = new ArrayList<>();

        while(graph.getOrder().size() != 0){
            list.add(graph.getOrder().pop());
        }

        return list;
    }

    public static void main(String[] args) {
        AdjacencyGraph<String> graph = new AdjacencyGraph<>();

        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.add("D");
        graph.add("E");
        graph.add("F");

        graph.connectDirected("A", "B");
        graph.connectDirected("B", "C");
        graph.connectDirected("C", "F");

        graph.connectDirected("A", "D");
        graph.connectDirected("D", "E");
        graph.connectDirected("E", "F");

        TopologicalOrdering<String> to = new TopologicalOrdering<>(graph);
        System.out.println(Arrays.toString(to.topologicalOrdering().toArray()));
    }
}
