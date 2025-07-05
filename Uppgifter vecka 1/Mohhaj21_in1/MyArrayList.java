import java.util.*;
import java.util.Arrays; 
import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T>{
    T[] arr; 
    int size;
    
    public MyArrayList(){
        arr = (T[]) new Object[5];
        size = 0;
    }

    public boolean add(T t){
        if(size == arr.length-1){
            T[] arr2 = (T[]) new Object [2*arr.length];
            for(int i=0; i<arr.length; i++){
                arr2[i] = arr[i];
            }
            arr = arr2;
        }
        arr[size++] = t;
        return true;
    }


    public void add(int index, T t){
        if(size == arr.length){
        T[] arr2 = (T[]) new Object [2*arr.length];
            for(int i=0; i<arr.length; i++){
                arr2[i] = arr[i];
            }
        }
        for(int i=size; i>=index; i--){
            arr[i+1] = arr[i];
        }
        arr[index] = t;
        size++;
    }

    public boolean contains (T t){
        for(int i=0; i<arr.length; i++){
            if(t.equals(arr[i])){
                return true;
            }
        
        }
        return false;
    }

    public T get(int index){
        if(null != arr[index]){
            return arr[index];
        }
        else
        {
        return null;
        }
    }

    public int indexOf(T t){
        for(int i=0; i<arr.length; i++){
            if(arr[i].equals(t)){
                return i;
            }
        } 
        return -1;  
    }



    public T Remove(int index){                   
        if(0>index && index>arr.length){
            throw new IndexOutOfBoundsException();
        }    
        T x = arr[index];
        for(int i=index; i<size; i++){
            arr[i]=arr[i+1];
        }
        size--;
        return x;
    }


    public boolean remove(T t) {
        int temp = indexOf(t);
        if (temp > 0) {
            Remove(temp);
            return true;
        }
        return false;
    }
    
    public int removeAll(T t){
        int counter = 0;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == t){
                arr[i] = null;
                counter++;
            }
        }
        return counter;
    }

    public T set(int index,T t){
        T temp = arr[index];
        arr[index] = t;
        return temp;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public Iterator<T> iterator(){
        return new Iterator<T>(){
            int index = 0;
            public boolean hasNext(){
                return index < size;
            }
            public T next(){
            return arr[index++];
            }
        };
    }

    public void clear(){
        for(int i=0; i<arr.length; i++){
            arr[i] = null;
        }
        size = 0;
    }

    public String toString(){
        String st = "[";
        for(int i=0; i<size; i++){
            st += " " + arr[i] + " "; 
        }
        return st + "]";
    }

public static void main(String[] CMDln){
    MyArrayList<Integer> test = new MyArrayList<Integer>();
    test.add(20);
    test.add(30);
    test.add(30);
    test.add(50);
    test.add(60);
    test.add(70);
    test.add(80);
    
    
    
System.out.println("Before :\n"+ test.toString()); 
//System.out.println(test.add(90));  
//test.add(3,500);                                                       
//System.out.println("Contains: "+ test.contains(20));
//System.out.println("Get: "+ test.get(7));
//System.out.println("indexOf :"+ test.indexOf(50));
//System.out.println("Remove: "+ test.Remove(1)); 
//System.out.println("remove: "+ test.remove(30));                       
//System.out.println("removeAll: "+ test.removeAll(30));
//System.out.println("set: "+ test.set(1, 5));
//System.out.println("isEmpty? :"+ test.isEmpty());
//System.out.println("size :"+ test.size());

/*for (Integer integer : test) {
  System.out.println(integer);
}
*/
//test.clear();
System.out.println("After :\n"+ test.toString());
}


}