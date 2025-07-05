import java.util.*;
import java.util.Comparator;
public class MinMax {
    puplic MinMax(){

    }
    public static <T extends Comparable> MyArrayList findMinMax(MyArrayList<T>arr){
        MyArrayList<T> pair = new MyArrayList<>();
        T min = arr.get(0);
        T max = arr.get(0);
        for(T t : arr){
            if(t.compareTo(max)>0) {
                max = t;
            }
            else{
                if(t.compareTo(min)<0){
                    min = t;
                }
            }
        }
        pair.add(0,min);
        pair.add(1,max);
        return pair;
    }

    public static <T> MyArrayList findMinMax(MyArrayList<T> arr, Comparator<T> c){
        MyArrayList<T> pair = new MyArrayList<>();
        T max = arr.get(0);
        T min = arr.get(0);
        for(T t : arr){
            if(c.compare(t, max) > 0){
                max = t;
            }
        }
        for(T t : arr){
            if(c.compare(t, min) < 0){
                min = t;
            }
        }
        pair.add(0, min);
        pair.add(1, max);
        return pair;
    }

    /*
    public static void main(String[] args) {
        Myarraylist<Land> list = new MyArrarList<>();
        list.add(new Land("Sweden", "Stockholm",100));
        list.add(new Land("Denmark", "Copenhagen",60));
        list.add(new Land("Germany", "Berlin",180));
        list.add(new Land("France", "Paris",80));
        
        Name n0 = new Name();
        Capital n1 = new Capital();
        Inhabitants n2 = new Inhabitants();

        System.out.println(MinMax.findMinMax(list));
        System.out.println(MinMax.findMinMax(list, n0));
        System.out.println(MinMax.findMinMax(list, n1));
        System.out.println(MinMax.findMinMax(list, n2));
    }
    */
}
