import java.util.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
public class DoublyLinkedList<T extends Comparable<T>> implements Iterable<T>{
    private Node<T> Head;
    private int size;
    private Node<T> Tail;

    public class Node<T>{
        private T Value;
        private Node next;
        private Node previous;

        public Node(T Value){
            this.Value = Value;
        }

        public T getValue(){
            return Value;
        }
    }

    public DoublyLinkedList(){
        this.Head = null;
        this.Tail = null;
        this.size = 0;
    }

    
    public void add(T t){
        Node<T> newNode = new Node(t);
        if(isEmpty()){
            Head = newNode;
        }
        else{
            Tail.next = newNode;
            newNode.previous = Tail;
        }
        Tail = newNode;
        size++;
    }

    public void add(int index, T t){
        Node<T> newNode = new Node(t);
        newNode.Value = t;
        newNode.next = null;
        newNode.previous = null;
        if(index < 0){
            throw new IllegalArgumentException("invaild index");
        }
        else if(index == 0){
            newNode.next = Head;
            newNode.previous = newNode;
            Head = newNode;
        }
        else{
            Node<T> temp = new Node(t);
            temp = Head;
            for(int i = 0; i < index-1; i++){
                if(temp != null){
                    temp = temp.next;
                }
            }
            if(temp != null){
                newNode.next = temp.next;
                newNode.previous = temp;
                temp.next = newNode;
                if(newNode.next != null){
                    newNode.next.previous = newNode;
                }
            }
        }
        size++;
    }

    public T get(int index){                       
        if(isEmpty() || index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        else{
            Node<T> temp = Head;
            for(int i = 0; i < index; i++){
                temp = temp.next;
            }
            return temp.getValue();
        }
    }

    public T getFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        else{
            return Head.getValue();
        }
    }

    public T getLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        else{
            return Tail.getValue();
        }
    }

    public int remove(T t){                             
        int counter = 0;
        Node<T> temp = Head;
        while(temp != null){
            if(temp.getValue() == (t)){
                if(temp == Head){
                    Head = temp.next;
                }
                else if(temp == Tail){
                    Tail = temp.previous;
                }
                else{
                    temp.previous.next = temp.next;
                    temp.next.previous = temp.previous;
                }
                counter++;
            }
            temp = temp.next;
        }
        size -= counter;
        return counter;
    }

    public T remove(int index){                             
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Invaild index");
        }
        Node<T> temp = Head;
        for(int i = 0; i<index; i++){
            temp = temp.next;
        }
        if(temp == Head){
            Head = temp.next;
        }
        else if(temp == Tail){
            Tail = temp.previous;
        }
        else{
            temp.previous.next = temp.next;
            temp.next.previous = temp.previous;
        }
        size--;
        return temp.getValue();
    }

    public T removeLast(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Node<T> temp = Tail;
        if(Head == Tail){
            Head = null;
        }
        else {
            Tail.previous.next = null;
        }
        Tail = Tail.previous;
        temp.previous = null;
        size--;
        return temp.getValue();
    }
    
    public T removeFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Node<T> temp = Head;
        if(Head == Tail){
            Tail = null;
        }
        else{
            Head.next.previous = null;
        }
        Head = Head.next;
        temp.next=null;
        size--;
        return temp.getValue();
    }

    public boolean isEmpty(){
        //return size == 0; 
        return Head == null;
    }

    public int size(){
        return size;
    }

    public Iterator<T> iterator(){
        return new DoublyLinkedListIterator();
    }
    public class DoublyLinkedListIterator implements Iterator<T>{
        Node<T> temp = Head;
        public boolean hasNext(){
            return temp != null;
        }
        public T next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T Value = temp.getValue();
            temp = temp.next;
            return Value;
        }
    }

    public void clear(){
        Node<T> temp = new Node();
        while(Head != null){
            temp = Head;
            Head = Head.next;
            temp = null;
        }
    }

    
    public String toString(){
        if (Head == null){
            return "[]";
        }
        else{
            String s = "[" + Head.getValue();
            Node current = Head.next;
            while(current != null){
                s += ", " +current.getValue() ;
                current = current.next;
            }
            s += "]";
            return s;
        }
    }

    public void reverse(){
        MyStack<T> ST = new MyStack<T>();
        for(T t : this){
            ST.push(t);
        }
        for(T t : this){
            this.remove(t);
        }
        while(!ST.isEmpty()){
            this.addLast(ST.pop());
        }
    }

    public void addAtFirstSmaller(T t){
        Node<T> node = Tail;
        Node<T> newNode = new Node<T>(t);
        while(node != null && node.getValue().compareTo(t) >= 0){
            node = node.previous;
        }
        if(node == null){
            addFirst(t);
        }
        else if(node == Tail){
            addLast(t);
        }
        else{
            Node<T> temp = node.next;
            node.next = newNode;
            newNode.next = temp;
            newNode.previous = node;
            temp.previous = newNode;
            size++;
        }
    }

    public void InsertionSort(T[] a){
        DoublyLinkedList<T> lista = new DoublyLinkedList<T>();
        for(T t : a){
            lista.addAtFirstSmaller(t);
        }
        Iterator<T> iterator = lista.iterator();
        int i = 0;
        while(iterator.hasNext()){
            a[i] = iterator.next();
            i++;
        }
        System.out.println(Arrays.toString(a));
    }


    public static void main(String[] args){
        DoublyLinkedList <Integer> lista = new DoublyLinkedList <Integer>(); 
       
        
       lista.add(40);
       lista.add(10);
       lista.add(20);
       lista.add(100);
       lista.add(20);
       lista.add(40);
       lista.add(20);
       lista.add(70);
       lista.add(0, 200);
       



        //System.out.println(lista.toString());
        //System.out.println(lista.isEmpty());
        //System.out.println(lista.get(2)); 
        //System.out.println(lista.getFirst()); 
        //System.out.println(lista.getLast()); 
        //System.out.println(lista.remove(20));             
        //System.out.println(lista.remove(0));                        
        //System.out.println(lista.removeLast());
        //System.out.println(lista.removeFirst());
       lista.clear();  

       // System.out.println(lista.size());  
        //System.out.println(lista.toString());
       



     /* System.out.println(lista);
        list.reverse();
        System.out.println(lista);
        Integer[] a = new Integer[4];
    	a[0] = new Integer(2);
    	a[1] = new Integer(1);
    	a[2] = new Integer(4);
    	a[3] = new Integer(3);
        long start = System.currentTimeMillis();
        list.InsertionSort(a);
        long finish = System.currentTimeMillis();
        int computationalTime = finish - start;
        System.out.println(computationalTime);
     */
    


    
     /*  for(int i = 5; i < 20; i++) {
            lista.add(i);
        }
        list.add(9);
        list.add(5);
        list.add(3);
        list.addAtFirstSmaller(4);
        list.addAtFirstSmaller(6);
        list.addAtFirstSmaller(7);
        list.addAtFirstSmaller(10);
        list.addAtFirstSmaller(4);
        System.out.println(lista);
     */
    
    }
}