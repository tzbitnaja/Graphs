import java.io.*;

public class Tester {
    public static void main(String[] args) throws IOException {
        File graph;
        graph = new File("graphs/smallgraph");

        if(args.length > 0)
            graph = new File(args[0]);

        BufferedReader br = new BufferedReader(new FileReader(graph)); //read file

        String line;
        int lines = 0;
        while((line = br.readLine()) != null) //count lines
            if(!line.isEmpty())
                lines++;
        lines--;
        br.close();

        Graph myGraph = new Graph(graph, lines);

        File result = new File("output/result");

        if (args.length > 1)
            result = new File(args[1]);

        FileOutputStream fos = new FileOutputStream(result); //print output to file
        System.setOut(new PrintStream(fos));

        System.out.print("Can color: ");
        Coloring check = new Coloring(myGraph);
        if(check.canColor()){
            System.out.println("yes.");
            check.validGraph(myGraph);
        }
        else {
            System.out.println("no.");
            check.oddCycle(myGraph);
        }
    }

}
