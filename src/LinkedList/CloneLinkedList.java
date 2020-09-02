package LinkedList;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CloneLinkedList {
    static class Node{
        int data;
        Node next,random;
        Node(int d){
            data=d;
            next=null;
            random=null;
        }
    }
    static int length(Node start){
        Node p=start;
        int l=0;
        while (p!=null){
            l++;
            p=p.next;
        }
        return l;
    }

    static Node clone(Node start){

        Node p=start,temp;
        //Inserting a node in between
        while(p!=null){
            temp=p.next;
            Node n=new Node(p.data);
            p.next=n;
            p.next.next=temp;
            p=temp;
        }
        p=start;
        //Moving the pointer and assigning values to the random pointer
        while(p!=null){
            if(p.next!=null )
                if(p.random!=null)
                    p.next.random=p.random.next;
                else
                    p.next.random=null;
            if(p.next!=null)
                p=p.next.next;
            else
                p=null;//reach the last node
        }
        //seperating the original and copied linked list
        Node o=start,c=start.next;
        Node copied=c;
        while (o!=null && c!=null){
            if(o.next!=null)
                o.next=o.next.next;
            if(c.next!=null)
                c.next=c.next.next;
            o=o.next;
            c=c.next;
        }
        return copied;
    }

    static void print(Node start)
    {
        Node p=start;
        while (p!= null)
        {
            if (p.random!= null) {
                System.out.print(p.data + "(" + p.random.data + ") -> ");
            } else {
                System.out.print(p.data + "(" + "Null" + ") -> ");
            }
            p = p.next;
        }
        System.out.println("null");
    }
    static Node assignRandomPointer(Node start,int i){
        switch (i){
            case 1:return start;
            case 2:return start.next;
            case 3:return start.next.next;
            case 4:return start.next.next.next;
            case 5:return start.next.next.next.next;
        }
        return null;
    }
    public static void main(String[] args) {

        Node start=new Node(1);
        start.next=new Node(2);
        start.next.next=new Node(3);
        start.next.next.next=new Node(4);
        start.next.next.next.next=new Node(5);
        List<Integer> range = IntStream.range(1,length(start)+1).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(range);
        int i=1;
        for(int j=0;j<length(start);j++) {
            switch (i) {
                case 1:
                    start.random = assignRandomPointer(start, range.get(j));
                    break;
                case 2:
                    start.next.random = assignRandomPointer(start, range.get(j));
                    break;
                case 3:
                    start.next.next.random = assignRandomPointer(start, range.get(j));
                    break;
                case 4:
                    start.next.next.next.random = assignRandomPointer(start, range.get(j));
                    break;
                case 5:
                    start.next.next.next.next.random = assignRandomPointer(start, range.get(j));
                    break;
            }
            i++;
        }
        System.out.println("Original Linked List");
        print(start);
        System.out.println("Cloned Linked List");
        Node copied=clone(start);
        print(copied);
    }
}