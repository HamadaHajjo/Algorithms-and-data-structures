import java.util.Arrays;

public class HashElement<Key> implements Comparable<HashElement<Key>>{

    public Key key ;
    public int cntr;

    public HashElement(Key key){
        this.key = key;
        cntr = 1;
    }

    public HashElement(Key key, int counter){
        this.key = key;
        this.cntr = counter;
    }

    public void increment(){
        cntr++;
    }

    public void decrement(){
        cntr--;
    }

    public int getFrequencey(){
        return cntr;
    }

    public Key getKey(){
        return key;
    }

    public void setKey(Key key){
        this.key = key;
    }

    public int compareTo(HashElement that){
        if(this.cntr > that.cntr)
            return 1;

        else if(this.cntr < that.cntr)
            return -1;

        else return 0;
    }

    public String toString(){
        return "data : "+ key + ", Frequency : " + cntr;
    }

}