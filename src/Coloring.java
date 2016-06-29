import java.util.LinkedList;
import java.util.Stack;

public class Coloring {
    private boolean canColor; //whether can color the graph or no

    class Colored<V, B>{ //class that will help output the valid coloring of a graph
        private Integer vertex;
        private Boolean color;
        public Colored(int v, boolean c){
            vertex = v;
            color = c;
        }
    }

    private LinkedList<Colored> validGraph; //linked list that will hold the vertices when a graph has a valid coloring
    private Stack<Integer> oddCycle; //stack that will hold vertices on the odd cycle

    public Coloring(Graph G){ //constructor
        canColor = true;
        validGraph = new LinkedList<Colored>();
        dfs(G); //call depth first search on the graph
    }

    public void dfs(Graph G){
        for(int v = 1; v < G.V() + 1; v++){ //for every vertex on the list
            if(G.vertices[v].vertex != -1 && G.vertices[v].visited == 0) //as long as vertex exists and haven't been visited
                visit(G, v); //visit
        }
    }

    private void visit(Graph G, int v){
        G.vertices[v].visited = 1; //color 'grey'

        for(Integer w : G.vertices[v].adj) { //for every vertex on v's adj list
            if(oddCycle != null) //if odd cycle exists we already know graph can't be colored, so return
                return;

            if (G.vertices[w].visited == 0){ //if vertex haven't been visited
                G.vertices[w].parent = G.vertices[v].vertex; //set vertex's parent
                G.vertices[w].color = !G.vertices[v].color; //color the vertex the opposite color of the parent. boolean true/false represents blue/red coloring
                visit(G, w); //visit the vertex
            }
            else if(G.vertices[w].color == G.vertices[v].color){ //however, if vertex and its parent have the same coloring
                if(oddCycle == null){
                    canColor = false; //can't color, so set to false
                    oddCycle = new Stack<>(); //initialize odd cycle stack
                    for (int i = v; i != w; i = G.vertices[i].parent) //go back up the parent list while the current vertex's parent does no equal to the parent during iteration
                        if(G.vertices[i].vertex != -1)
                            oddCycle.push(G.vertices[i].vertex); //push vertex that is part of odd length cycle on the stack
                    oddCycle.push(G.vertices[w].vertex); //push the last vertex on the cycle on the stack
                }
            }
        }
        validGraph.add(new Colored(v, G.vertices[v].color)); //add to valid graph coloring
        G.vertices[v].visited = 2; //set to visited/'black'
    }

    public boolean canColor(){
        return canColor;
    }

    public void oddCycle(Graph G){ //prints the odd length cycle
        while(!oddCycle.isEmpty())
            System.out.print(oddCycle.pop() + " "); //by continuously popping off the stack
    }

    public void validGraph(Graph G){ //print valid coloring
        while(!validGraph.isEmpty())
            System.out.println(validGraph.getLast().vertex + " " + ((validGraph.removeLast().color) ? "blue" : "red"));
    }
}
