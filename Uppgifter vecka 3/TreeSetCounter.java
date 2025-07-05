import org.w3c.dom.Node;
import java.util.Iterator;
import java.util.Stack;
import java.util.*;
import java.util.Iterator;

public class TreeSetCounter <T extends Comparable<T> >implements Iterable<T>{

    public class Node<T>{
        public Node<T> left;
        public Node<T> right;
        public T data;
        public int c;
        Node(T data) {
            this.data = data;
            this.c = 1;
        }
    }
    Node<T> root;
    int size;
    public TreeSetCounter(){
        root = null;
    }

    public void add(T t){
        root = add(root,t);
        size++;
    }

    private Node add(Node node, T value){
        if(node == null ){
            return new Node(value);
        }
        int compare = value.compareTo((T) node.data);
        if(compare < 0){
            node.left = add(node.left, value);
        }
        else if(compare > 0 ){
            node.right = add(node.right, value);
        }
        else{
            node.data = value;
            node.c++;
        }
        return node;
    }

    public void clear(){
        root = null;
        size=0;
    }

    public int getMaxFrequency(){
        int count = 0;
        TSCIterator c = new TSCIterator();
        if (count < c.next().c){
            count = c.next().c;
        }
        return count;
    }

    public boolean contains(T t){
        int x = counter(t);
        return x != 0 ;
    }

    public boolean isEmpty(){
        return root == null ;
    }

    public int size(){
        return size;
    }

    public int counter(T t){
        Node<T> node = root;
        if(node == null) return 0;
        while(true){
            if(t.compareTo(node.data) > 0 ){
                node = node.right;
                if(node == null)
                return 0;
            }
            if(t.compareTo(node.data) < 0 ){
                node = node.left;
                if (node == null)
                return 0;
            }
            if(t.compareTo(node.data) == 0 ){
                return node.c;
            }
        }
    }

    private class TSCIterator implements Iterator<Node>{
        Stack<Node> stack = new Stack<Node>();
        public TSCIterator(){
            pushLeft(root);
        }
        public boolean hasNext(){
            return !stack.isEmpty();
        }
        public Node next(){
            Node node = stack.pop();
            pushLeft(node.right);
            return node;
        }
        private void pushLeft(Node node){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
        }
    }

    public Iterator<T> iterator(){
        return new TSCIterator();
    }

    public String toString(){
        TSCIterator str = new TSCIterator();
        String s ="[ ";
        if(size == 0)
        return s = "[ ]";
        else{
            while(str.hasNext()){
                s +=  str.next().data + ", ";
            }
            s += " ]";
            return s;
        }
    }

    public void show(){
        show(root);
    }

    private void show(Node node){
        if(node == null) return;
        show(node.left);
        System.out.println(node.data + ", number of iterations in tree " + node.c);
        show(node.right);
    }

    public static void main(String[] args) {
        TreeSetCounter<Integer> tsc = new TreeSetCounter<Integer>();
        tsc.add(1);
    
        tsc.add(25);
        tsc.add(25);
        tsc.add(25);
        tsc.add(24);
        tsc.add(26);
        tsc.add(26);
        tsc.add(3);
        tsc.add(27);
        tsc.add(27);
        tsc.add(5);
        tsc.add(70);
        System.out.println(tsc.getMaxFrequency());
        System.out.println(tsc.contains(5));
        System.out.println(tsc.counter(2));
        System.out.println(tsc.toSt(tsc.root));
    }
}
