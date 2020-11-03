package TopologicalSort;

import java.util.Scanner;
import java.util.Stack;

public class TopologicalSort {
    static int G[][],n;
    public static void input(){
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        n= scan.nextInt();
        System.out.println("Enter the Adjacency matrix");
        G=new int[n][n];
        for(int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                G[i][j]=scan.nextInt();
            }
        }
    }
    public  static void topologicalSortHelper(int vertex, boolean visited[], Stack<Integer> stack) {
        visited[vertex] = true;

        for(int i = 0; i<n; i++) {
            if(G[vertex][i]==1) {
                if(!visited[i])
                    topologicalSortHelper(i,visited,stack);
            }
        }
        stack.push(vertex);
    }
    public static void topologicalSorting(){
        Stack<Integer> stack=new Stack<>();
        boolean visited[]=new boolean[n];
        for (int i=0;i<n;i++){
            visited[i]=false;
        }
        for(int i=0;i<n;i++){
            if(!visited[i]){
                topologicalSortHelper(i,visited,stack);
            }
        }
        System.out.print("Topological Sorting Order is ");
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
    }
    public static void main(String[] args) {
        input();
        topologicalSorting();

    }
}
