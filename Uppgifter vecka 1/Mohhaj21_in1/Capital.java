import java.util.Comparator;

public class Capital implements Comparator<Land>{
    
    public int compare(Land Land1, Land Land2){
        return Integer.compare(Land1.getCapital(), Land2.getCapital());
    }
  
}
 
   