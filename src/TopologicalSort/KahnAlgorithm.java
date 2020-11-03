package TopologicalSort;

import java.util.*;

public class KahnAlgorithm {

    public  static  int n,e;
    public static List<Integer> adjacencyList[];

    public static void input(){
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        n=scan.nextInt();
        adjacencyList = new ArrayList[n];
        for(int i=0;i<n;i++){
            adjacencyList[i]=new ArrayList<>();
        }
        System.out.println("Enter no of edges");
        e=scan.nextInt();
        for (int i=0;i<e;i++){
            System.out.println("Enter Src and destination for edge "+(i+1));
            int src=scan.nextInt();
            int dest=scan.nextInt();
            edge(src,dest);
        }

    }
    public static void edge(int src,int dest){
        adjacencyList[src].add(dest);
    }

    public static void topologicalSort(){
        int incomingDegree[]=new int[n];
        int count=0;//processed nodes
        List<Integer> result=new ArrayList<>();
        for (int i=0;i<n;i++){
            for (int vertex:adjacencyList[i]) {
                incomingDegree[vertex]++;
            }

        }

        Queue<Integer> queue=new LinkedList<>();
        for (int i=0;i<n;i++){
            if (incomingDegree[i]==0){
                queue.add(i);
            }
        }

        while (!queue.isEmpty()){
            int front=queue.poll();
            result.add(front);
            for (int i = 0; i< adjacencyList[front].size(); i++){
                int vertex= adjacencyList[front].get(i);
                incomingDegree[vertex]--;
                if (incomingDegree[vertex]==0){
                    queue.add(vertex);
                }
            }
            count++;
        }
        if (count!=n){
            System.out.print("Cycle in the graph ");
            return;
        }

        System.out.print("Topological Sort ");
        for (int r:result){
            System.out.print(r+" ");
        }
    }


    public static void main(String[] args) {
        input();
        topologicalSort();

    }
}
