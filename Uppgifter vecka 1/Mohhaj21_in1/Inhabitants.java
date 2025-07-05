import java.util.Comparator;

public class Inhabitants implements Comparator<Land>{
    
    public int compare(Land Land1, Land Land2){
        return Integer.compare(Land1.getInhabitants(), Land2.getInhabitants());
    }
  
}