import java.util.Comparator;

public class Name implements Comparator<Land>{
    
    public int compare(Land Land1, Land Land2){
        return Integer.compare(Land1.getName(), Land2.getName());
    }

}