import java.util.Iterator;
public class MyStack<T extends Comparable<T>> implements Iterable<T>{
    public DoublyLinkedList<T> lista;
    public MyStack(){
        lista = new DoublyLinkedList<T>();
    }

    public boolean isEmpty(){
        return lista.isEmpty();
    }
    
    public T peek(){
        return lista.getFirst();
    }

    public T pop(){
        return lista.removeFirst();
    }

    public T push(){
        lista.addFirst(t);
    }
    public Iterator<T> iterator(){
        return lista.iterator();
    }

}