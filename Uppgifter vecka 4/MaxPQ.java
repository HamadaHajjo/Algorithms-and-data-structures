import java.util.*;
import java.util.Arrays;
import java.util.Iterator;
public class MaxPQ <Key extends Comparable<Key>>{

    Key[] arr;
    int size = 0;

    public MaxPQ(){
        arr = (Key[]) new Comparable[10];
        size = 0;
    }

    public MaxPQ(Key[] arr){

        this.arr = (Key[]) new Comparable[arr.length+1];
        size = arr.length;

        for (int i = 0; i < arr.length; i++){
            this.arr[i+1] = arr[i];
        }
        for (int k = arr.length/2; k >= 1; k--){
            sink(k);
        }

    }

    public MaxPQ(int max){
        arr = (Key[]) new Comparable[max];
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void insert(Key t){
        if (size == arr.length - 2){
            arr = Arrays.copyOf(arr, 2*arr.length);
        }
        arr[++size] = t;
        swim(size);
    }

    private void swim(int k){
        while (k/2 > 0 && less(k/2, k)){
            exchange(k/2, k);
            k /= 2;
        }
    }

    private boolean less(int i, int j){
        return arr[i].compareTo(arr[j]) < 0;
    }

    private void exchange(int i, int j){
        Key temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    public Key delMax(){
        if(size == 0){ 
            return null;
        }
        Key maxElement = arr[1];
        exchange(1, size);
        arr[size--] = null;

        if(size > 1){ 
            sink(1);
        }
        return maxElement;
    }

    public void sink(int k){
        while (2*k <= size){
            int j = 2*k;
            if (j < size && less(j, j+1)) j++;
            if (less(j,k)) break;
            exchange(j, k);
            k = j;
        }
    }

    public String toString(){
        return Arrays.toString(arr);
    }

    public Key max(){
        if (size == 0){
            return null;
        }
        return arr[1];
    }

    public static void main(String [] CMDLN){
        MaxPQ<Integer> pq = new MaxPQ<Integer>();
        pq.insert(8);
        pq.insert(7);
        pq.insert(5);
        pq.insert(1);
        pq.insert(3);
        pq.insert(5);
        System.out.println(pq);
        pq.delMax();
        System.out.println(pq);
        pq.insert(11);
        System.out.println(pq);
        pq.insert(12);
        System.out.println(pq);
        System.out.println(pq);

    }
    
}
