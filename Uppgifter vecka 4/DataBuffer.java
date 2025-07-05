import java.util.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DataBuffer<T> implements Iterable<T>{
    int back;
    int front;
    int bufferSize = 10;
    int size = 0;
    T[] arr;


    public DataBuffer(int bufferSize){
        if(bufferSize < 1){
            throw new IndexOutOfBoundsException("invaild value");
        }
        this.back = this.front = -1;
        this.bufferSize = bufferSize;
        this.arr = (T[]) new Comparable[bufferSize];
            
    }
    
    public void enqueue(T t){
        if(isFull()) {
            throw new IndexOutOfBoundsException();
        }

        else if(isEmpty()){
            front++;
        }
        back = (back+1) % arr.length;
        arr[back] = t;
        size++;
    }

    public T dequeue(){
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        T temp = arr[front];
            front = (front + 1 ) % arr.length;
            size--;

        return temp;
    }

    public void changeBufferSize(int newBufferSize){
        if(isFull())
        {
            arr = Arrays.copyOf(arr, newBufferSize);
        }
    }

    public boolean isFull(){
        return front == (back +1)% bufferSize;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }


    public int bufferSize(){
        return bufferSize;
    }


    public String toString() {
        return Arrays.toString(arr);
    }

    public Iterator<T> iterator(){
        return new Iterator<T>(){
            int index = 0;

            public boolean hasNext(){
                return index < size;
            }

            public T next(){
                T temp = arr[index];
                index++;
                return temp;
            }
        };
    }

    public static void main(String[] args) {
        DataBuffer<Integer> buffer = new DataBuffer<Integer>(10);
        buffer.enqueue(1);
        buffer.enqueue(2);
        buffer.enqueue(3);
        buffer.enqueue(4);
        buffer.enqueue(5);
        buffer.enqueue(5);
        buffer.enqueue(5);
        buffer.enqueue(5);
        buffer.enqueue(5);
        buffer.enqueue(5);
        System.out.println(buffer.toString());
        System.out.println(buffer.isEmpty());
        System.out.println(buffer.isFull());
        buffer.changeBufferSize(5);
        System.out.println(buffer.dequeue());
        System.out.println(buffer.dequeue());
        System.out.println(buffer.dequeue());
        System.out.println(buffer.dequeue());
        buffer.enqueue(5);
        buffer.enqueue(5);
        System.out.println(buffer.toString());
    }
}