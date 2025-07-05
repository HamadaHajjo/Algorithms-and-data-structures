import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import javax.crypto.spec.GCMParameterSpec;

public class LinearProbingHashSet<Key>{

    public HashElement<Key>[] lpa;
    public int m;
    public int size;


    public LinearProbingHashSet(int m){
        this.m = m;
        lpa = new HashElement[m];
        size = 0;
    }

    public LinearProbingHashSet(){
        this(10);
    }

    public int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public int getCapacity(){
        return m;
    }

    public void resize(int newM){
        LinearProbingHashSet<Key> t = new LinearProbingHashSet<>(newM);
        for (int i = 0; i < m; i++){
            if(lpa[i] != null){
                t.insert(lpa[i].getKey());
                if(lpa[i].getFrequencey() > 1){
                    t.lpa[i].cntr = lpa[i].getFrequencey();
                }
            }
        }
        this.lpa = t.lpa;
        this.m = t.m;
    }

    public void insert(Key key){
        double LoadFactor = (size / m);
        if(LoadFactor >= 0.5)
            resize(2 * m);
        int index = hash(key);
        while(lpa[index] != null){
            if(lpa[index].getKey().equals(key)){
                lpa[index].increment();
                return;
            }
            index = (index+1) % m;
        }
        lpa[index] = new HashElement<>(key);
        size++;
    }

    public boolean contains(Key key){
        int index = hash(key);
        while(lpa[index] != null){
            if(lpa[index].getKey().equals(key)){
                return true;
            }
            index = (index+1) % m;
        }
        return false;
    }

    public void decrease(Key key){
        HashElement<Key> tmp = lpa[hash(key)];
        if(key == null){
            throw new NoSuchElementException("");
        }
        tmp.decrement();
        if(tmp.cntr == 0){
            delete(tmp.getKey());
        }
    }

    public void delete(Key key){
        double LoadFactor = size/m ;
        if(!contains(key)){
            throw new NoSuchElementException("Value doesnt exist ! ");
        }
        if(LoadFactor <= 1 / 8)
        resize(m/2);
        int i;
        for(i = hash(key); lpa[i].getKey() != key ; i =((i+1)%m));
        lpa[i] = null;
        size--;
        i = (i + 1) % m;
        while (lpa[i] != null){
            Key tmpNyckel = lpa[i].getKey();
            lpa[i] = null;
            size--;
            insert(tmpNyckel);
            i = ((i+1)%m);
        }
    }

    public Iterable<Key> keys(){
        ArrayList<Key> x = new ArrayList<>(m);
        MaxPQ<HashElement<Key>> Keys = new MaxPQ<>(m);
        for(int i = 0; i < m; i++){
            if(lpa[i] != null){
                Keys.insert(lpa[i]);
            }
        }
        while(!Keys.isEmpty()){
            x.add(Keys.delMax().getKey());
        }
        return x;
    }
}