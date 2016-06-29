import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    class Vertex{
        public int visited = 0; //never been visited at the time of initialization
        public boolean color; //boolean that will represent two-coloring
        public int parent = -1; //no parent at time of initialization

        public int vertex = -1; //vertex at time of initialization
        public ArrayList<Integer> adj = new ArrayList<Integer>(); //edge adjacency list
    }
    private int V; //number of vertices
    public Vertex[] vertices; //array of vertices

    public Graph(File graph, int edges) throws IOException { //construct a graph from a file
        Scanner sc = new Scanner(graph);
        V = sc.nextInt(); //number of vertices
        vertices = new Vertex[V + 1]; //give vertex array a size V+1, vertices will be stored at indexes 1...V

        for(int i = 0; i < vertices.length; i++) //initialize vertices
            vertices[i] = new Vertex();

        sc.nextLine();
        for(int i = 0; i < edges; i++){ //for every newly encountered edge, add vertices to each other's adjacency lists
            String[] edge = sc.nextLine().split(" ");
            int v = Integer.parseInt(edge[0]);
            int w = Integer.parseInt(edge[1]);
            addEdge(v,w); //method that creates an edge between 2 vertices
        }
    }

    public void addEdge(int v, int w){ //add vertices to each other's adj list, creating an edge between them
        vertices[v].vertex = v;
        vertices[v].adj.add(w);

        vertices[w].vertex = w;
        vertices[w].adj.add(v);
    }

    public int V(){
        return V;
    }
}

