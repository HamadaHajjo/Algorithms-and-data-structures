import java.net.SocketPermission;

public class Land implements Comparable<Land>{

    String Name;
    String Capital;
    int Inhabitants;
    
    public Land(String Name, String Capital, int Inhabitants){
      this.Name = Name;
      this.Capital = Capital;
      this.Inhabitants = Inhabitants;
    }
    
    public int getName(){
      return Name.length();
    }

    public int getCapital(){
      return Capital.length();
    }

    public int getInhabitants(){
      return Inhabitants;
    }

    public int compareTo(Land Land){
        if(this.Inhabitants < Land.Inhabitants){
         return -1;   
        } 
        if(this.Inhabitants > Land.Inhabitants){
            return 1;
        }
         else
         {
         return 0;    
         } 
    
    }
    
    public String toString(){
      return "Name :" + Name + "Stad :" + Capital + "Inhabitants :" + Inhabitants;
    }
     public static void main(String[] args) {
        Land Sweden = new Land("Sweden", "Stockholm", 100);
        Land Denmark = new Land("Denmark", "Copenhagen", 60);
        System.out.println(Sweden.compareTo(Denmark));
    }
}